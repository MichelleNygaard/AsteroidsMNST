package dk.sdu.background;

import dk.sdu.cbse.common.data.GameData;

import javax.swing.text.html.ImageView;

public interface BackgroundSPI {
    ImageView getBackground(GameData gamedata);
}
