package dk.sdu.core;

import dk.sdu.background.BackgroundSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGameDataProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class Logic extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<BackgroundSPI> backgroundService;
    private final List<IGameDataProcessingService> gameDataProcessingServices;

    public Logic(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServices,
                 List<IPostEntityProcessingService> postEntityProcessingServices, List<BackgroundSPI> backgroundService,
                 List<IGameDataProcessingService> gameDataProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.backgroundService = backgroundService;
        this.gameDataProcessingServices = gameDataProcessingServices;
    }

    @Override
    public void start(Stage window) throws Exception {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        //gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                System.out.println("Q is pressed!");
                gameData.getKeys().setKey(GameKeys.Q, true);
            }
            if (event.getCode().equals(KeyCode.E)) {
                System.out.println("E is pressed!");
                gameData.getKeys().setKey(GameKeys.E, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                gameData.getKeys().setKey(GameKeys.Q, false);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.E, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }

        if (backgroundService.stream().findFirst().isPresent()) {
            System.out.println("Background service is started");
            ImageView background = backgroundService.stream().findAny().get().getBackground(gameData);
            gameData.getGameWindow().getChildren().add(background);
            background.toBack();
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            if (entity.getColor() != null) {
                polygon.setFill(Color.valueOf(entity.getColor()));
            }
            polygons.put(entity, polygon);
            gameData.getGameWindow().getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

//    public void render() {
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                try {
//                    update();
//                } catch (IOException | InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//                draw();
//                gameData.getKeys().update();
//            }
//
//        }.start();
//    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            try {
                postEntityProcessorService.process(gameData, world);
            } catch (IOException | URISyntaxException | InterruptedException e) {
                System.err.println("Error in post entity processing: " + e.getMessage());
                e.printStackTrace();
            }
        }
        for (IGameDataProcessingService gameDataProcessingService : gameDataProcessingServices) {
            gameDataProcessingService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

