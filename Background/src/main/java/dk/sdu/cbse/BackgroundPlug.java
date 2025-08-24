package dk.sdu.cbse;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import javafx.scene.image.Image;
import dk.sdu.cbse.BackgroundPlug;
import org.springframework.stereotype.Component;


@Component
public class BackgroundPlug implements IGamePluginService, BackgroundSPI {

    ImageView view;
    @Override
    public void start(GameData gameData, World world){

    }

    @Override
    public void stop(GameData gameData, World world){

    }

    @Override
    public ImageView getBackground(GameData gameData) {
        view = new ImageView((Element) new Image(this.getClass().getResource("/images/spacebackground.png").toString()));
        view.setFitHeight(gameData.getDisplayHeight());
        view.setFitWidth(gameData.getDisplayWidth());
        return view;
    }
}