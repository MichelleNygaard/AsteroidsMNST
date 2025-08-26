package dk.sdu.cbse.player;

import dk.sdu.cbse.common.bullet.Bullets;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.weapon.WeaponSPI;
import dk.sdu.cbse.common.weapon.WeaponPlug;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Component
public class PlayerControl implements IEntityProcessingService {

    private int bulletCooldown = 0;

    @Override
    public void process(GameData gameData, World world) {



        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 2);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 2);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                if (bulletCooldown == 0) {
                    System.out.println(getWeaponSPIs());
                    WeaponPlug weaponsPlugin = WeaponPlug.getInstance();
                    if (weaponsPlugin != null && weaponsPlugin.getCurrentWeapon() != null) {
                        for (Entity bullet : weaponsPlugin.getCurrentWeapon().createShot(player, world)) {
                            world.addEntity(bullet);
                        }
                    } else {
                        getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> world.addEntity(spi.createBullet(player, gameData))
                        );
                    }
                    bulletCooldown = 10;
                }
                if (bulletCooldown > 0) {
                    bulletCooldown--;
                }
            }

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth()-1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight()-1);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends WeaponSPI> getWeaponSPIs() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}