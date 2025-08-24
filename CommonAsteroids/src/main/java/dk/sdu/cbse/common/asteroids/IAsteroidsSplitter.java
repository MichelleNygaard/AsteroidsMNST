package dk.sdu.cbse.common.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IAsteroidsSplitter {
    void createSplitAsteroid(Entity e, World w) throws IOException, URISyntaxException, InterruptedException;

    void createSplitAsteroid(Entity e, World world, GameData gameData) throws IOException, URISyntaxException, InterruptedException;
}
