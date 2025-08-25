import dk.sdu.cbse.common.weapon.WeaponSPI;

module SingleShot {
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    provides WeaponSPI with dk.sdu.cbse.singleshot.SingleShot;
}