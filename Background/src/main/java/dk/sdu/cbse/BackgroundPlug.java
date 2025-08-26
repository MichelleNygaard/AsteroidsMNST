package dk.sdu.cbse;

import dk.sdu.background.BackgroundSPI;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import dk.sdu.cbse.BackgroundPlug;
import org.springframework.stereotype.Component;


@Component
public class BackgroundPlug implements IGamePluginService, BackgroundSPI {

    ImageView view;
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    @Override
    public ImageView getBackground(GameData gameData) {

        view = new ImageView(new Image(this.getClass().getResource("/images/pillars.png").toString()));

        view.setFitHeight(gameData.getDisplayHeight());
        view.setFitWidth(gameData.getDisplayWidth());
        return view;
    }
}