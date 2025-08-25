package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IPostEntityProcessingService {

    void process(GameData gameData, World world) throws IOException, URISyntaxException, InterruptedException;
}