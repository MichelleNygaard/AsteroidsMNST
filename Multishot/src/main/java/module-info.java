import dk.sdu.cbse.common.weapon.WeaponSPI;

module Multishot {
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    provides WeaponSPI with dk.sdu.cbse.multishot.Multishot;
}