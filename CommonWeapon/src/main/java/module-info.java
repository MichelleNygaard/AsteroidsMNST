import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IGameDataProcessingService;

module CommonWeapon {
    uses dk.sdu.cbse.common.weapon.WeaponSPI;
    exports dk.sdu.cbse.common.weapon;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    provides IGamePluginService with dk.sdu.cbse.common.weapon.WeaponPlug;
    provides IGameDataProcessingService with dk.sdu.cbse.common.weapon.WeaponControl;
}