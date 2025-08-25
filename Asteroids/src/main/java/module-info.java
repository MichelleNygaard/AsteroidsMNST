import dk.sdu.cbse.common.asteroids.IAsteroidsSplitter;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroids {
    uses IGamePluginService;
    uses dk.sdu.cbse.scoringsysplug.IScoringSystem;
    requires Common;
    requires CommonAsteroids;
    requires ScoringSysPlug;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AstPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AstProcessor;
    provides IAsteroidsSplitter with dk.sdu.cbse.asteroids.AstSplitterImpl;

}