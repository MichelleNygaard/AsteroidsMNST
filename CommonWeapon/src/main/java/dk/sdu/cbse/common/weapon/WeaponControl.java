package dk.sdu.cbse.common.weapon;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGameDataProcessingService;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class WeaponControl implements IGameDataProcessingService {
    WeaponPlug weaponsPlugin = new WeaponPlug();
    private int currentWeaponIndex = 0;
    @Override
    public void process(GameData gameData, World world) {
        if (gameData.getKeys().isPressed(GameKeys.Q)) {
            System.out.println("CurrentIndex: " + currentWeaponIndex);
            System.out.println("All Weapons size: " + weaponsPlugin.getAllWeapons().size());
            System.out.println("Q Pressed");
            if (this.currentWeaponIndex == 0) {
                this.currentWeaponIndex = weaponsPlugin.getAllWeapons().size()-1;
            }

            this.currentWeaponIndex = this.currentWeaponIndex-1;

            if (weaponsPlugin.getAllWeapons().size() > 1) {
                weaponsPlugin.setCurrentWeapon(weaponsPlugin.getAllWeapons().get(this.currentWeaponIndex));
                updateText(gameData);
            }

        }
        if (gameData.getKeys().isPressed(GameKeys.E)) {
            System.out.println("CurrentIndex: " + currentWeaponIndex);
            System.out.println("All Weapons size: " + weaponsPlugin.getAllWeapons().size());
            System.out.println("E Pressed");
            if (this.currentWeaponIndex == weaponsPlugin.getAllWeapons().size() - 1) {
                this.currentWeaponIndex = 0;
            }

            this.currentWeaponIndex = this.currentWeaponIndex+1;

            if (weaponsPlugin.getAllWeapons().size() > 1) {
                weaponsPlugin.setCurrentWeapon(weaponsPlugin.getAllWeapons().get(this.currentWeaponIndex));
                updateText(gameData);
            }
        }
    }

    private void updateText(GameData gameData) {
        for (Node node : gameData.getGameWindow().getChildren()) {
            String nodeId = node.getId();
            if ("currentWeaponText".equals(nodeId)) {
                Text currentWeaponText = (Text) node;
                currentWeaponText.setText("Current Weapon: " + weaponsPlugin.getCurrentWeapon().getClass().getSimpleName());
            }
        }
    }
}
