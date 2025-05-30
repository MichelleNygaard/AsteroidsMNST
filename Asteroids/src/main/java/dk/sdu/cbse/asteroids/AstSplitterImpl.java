package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.entattributes.EntMovement;
import dk.sdu.cbse.common.data.entattributes.EntPosition;
import dk.sdu.cbse.common.data.entattributes.HPAttribute;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class AstSplitterImpl implements IAsteroidsSplitter {


    @Override
    public void createSplitAsteroid(Entity e, World world) {
        EntPosition otherPosi = e.getPart(EntPosition.class);
        HPAttribute otherHP = e.getPart(HPAttribute.class);
        float radians = otherPosi.getRadians();
        int radius = 0;
        float speed = 5;
        int hp = otherHP.getHp() - 1;
        if (hp == 1 ) {
            radius = 6;
            speed = (float) Math.random() * 30f + 70f;
        } else if (hp == 2) {
            radius = 10;
            speed = (float) Math.random() * 10f + 50f;
        }else if (hp <= 0) {
            world.removeEntity(e);
            return;
        }

        // Definition of the two new smaller asteroids
        // 1st new asteroid
        Entity ast1 = new Asteroids();
        ast1.setRadius(radius);
        float radians1 = radians * 0.5f;

        float ay1 = (float) sin(radians1) * e.getRadius() * ast1.getRadius();
        float ax1 = (float) cos(radians1) * e.getRadius() * ast1.getRadius();

        EntPosition astPos1st = new EntPosition(otherPosi.getX() + ax1, otherPosi.getY()
                + ay1, radians1);
        ast1.add(new EntMovement(0, 5000, speed, 0));
        ast1.add(astPos1st);
        ast1.add(new HPAttribute(hp));

        world.addEntity(ast1);

        // 2nd new asteroid
        Entity ast2 = new Asteroids();
        ast2.setRadius(radius);
        float radians2 = radians * 0.5f;

        float ay2 = (float) cos(radians2) * e.getRadius() * ast2.getRadius();
        float ax2 = (float) cos(radians2) * e.getRadius() * ast2.getRadius();

        EntPosition astPos2nd = new EntPosition(otherPosi.getX() + ax2, otherPosi.getY()
                + ay2, radians2);
        ast2.add(new EntMovement(0, 5000, speed, 0));
        ast2.add(astPos2nd);
        ast2.add(new HPAttribute(hp));

        world.addEntity(ast2);

        world.removeEntity(e);
    }

}
