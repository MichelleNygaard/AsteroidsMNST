package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class PlayerPlugin implements IGamePluginService {

    private Player player;

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = (Player) createPlayerShip(gameData);
        player.setColor("GREEN");
        world.addEntity(player);
    }

    private Player createPlayerShip(GameData gameData) {

        Player playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setRadius(8);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}