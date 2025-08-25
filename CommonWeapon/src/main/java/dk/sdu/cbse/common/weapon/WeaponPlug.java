package dk.sdu.cbse.common.weapon;

import java.util.List;
import java.util.ServiceLoader;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static java.util.stream.Collectors.toList;

public class WeaponPlug implements IGamePluginService {
    private static WeaponPlug instance;
    public WeaponPlug() {instance = this;}
    public static WeaponPlug getInstance() { return instance; }
    private WeaponSPI currentWeapon = getAllWeapons().getFirst();

    @Override
    public void start(GameData gameData, World world) {
        Text weaponText = new Text(10, gameData.getDisplayHeight() - 10,
                "Current Weapon: " + currentWeapon.getClass().getSimpleName());
        weaponText.setFill(Color.WHITE);
        weaponText.setId("currentWeaponText");
        gameData.getGameWindow().getChildren().add(weaponText);
        weaponText.toFront();
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

    public List<WeaponSPI> getAllWeapons() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public WeaponSPI getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(WeaponSPI currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
}
