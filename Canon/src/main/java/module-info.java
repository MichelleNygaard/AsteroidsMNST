module Canon {
    requires Common;
    requires CommonWeapon;
    requires CommonBullet;
    provides WeaponSPI with dk.sdu.cbse.canon.Canon;
}