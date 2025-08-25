import dk.sdu.cbse.common.weapon.WeaponSPI;

module Canon {
    requires Common;
    requires CommonWeapon;
    requires CommonBullet;
    provides WeaponSPI with dk.sdu.cbse.canon.Canon;
}