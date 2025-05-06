import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AstPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AstProcessor;
}