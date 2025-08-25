package dk.sdu.cbse.common.weapon;

public abstract class Weapon implements WeaponSPI {
    private String weaponName;
    public String getWeaponName() {
        return weaponName;
    }
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}