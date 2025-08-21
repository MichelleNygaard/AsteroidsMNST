package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.GameData;

import java.util.Random;


public class AstSplitterImpl implements IAsteroidsSplitter {


    @Override
    public void createSplitAsteroid(Entity e, World world) {
        System.out.println(e.getHealthPoints());

        if (e.getHealthpoints() == 1) {
            if (e.getPolygonCoordinates()[0] <= 8) {
                world.removeEntity(e);
                // Updates the score if the ScorePlugin exists
                if (getScoreImpl() != null) {
                    getScoreImpl().addScore(5);
                    getScoreImpl().updateScore(gameData);
                }
            } else {
                Asteroids splitAsteroid1 = initializeSplitAsteroid(e);
                splitAsteroid1.setX(e.getX() - splitAsteroid1.getRadius() - 1);
                splitAsteroid1.setY(e.getY() - splitAsteroid1.getRadius() - 1);

                Asteroid splitAsteroid2 = initializeSplitAsteroid(e);
                splitAsteroid2.setX(e.getX() - splitAsteroid2.getRadius() + 1);
                splitAsteroid2.setY(e.getY() - splitAsteroid2.getRadius() + 1);

                world.addEntity(splitAsteroid1);
                world.addEntity(splitAsteroid2);

                world.removeEntity(e);
                // Update score if ScorePlugin exists
                if (getScoreImpl() != null) {
                    getScoreImpl().addScore(10);
                    getScoreImpl().updateScore(gameData);
                }
            }
        } else {
            e.setHealthPoints(e.getHealthPoints() - 1);
        }
    }

    private Asteroids initializeSplitAsteroid(Entity e) {
        Asteroids asteroid = new Asteroids();
        Random rnd = new Random();

        double[] splitAsteroidPolygonCoords = e.getPolygonCoordinates();
        for (int i = 0; i < e.getPolygonCoordinates().length; i++) {
            e.getPolygonCoordinates()[i] = e.getPolygonCoordinates()[i] * 0.8;
        }

        asteroid.setPolygonCoordinates(splitAsteroidPolygonCoords);
        asteroid.setRadius((float) splitAsteroidPolygonCoords[0]);

        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHealthPoints(rnd.nextInt(2, 4));

        return asteroid;
    }

    public IScoringSystem getScoreImpl() {
        if (ServiceLoader.load(IScoringSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().isPresent()) {
            return ServiceLoader.load(IScoringSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().get();
        } else {
            return null;
        }
    }
}

