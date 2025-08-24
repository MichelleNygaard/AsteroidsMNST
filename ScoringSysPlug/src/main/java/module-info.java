import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.scoringsysplug.IScoringSystem;
import dk.sdu.cbse.scoringsysplug.ScoringSysPlug;

module ScoringSysPlug {
    exports dk.sdu.cbse.scoringsysplug;
    requires Common;
    requires javafx.graphics;
    requires java.net.http;
    provides IGamePluginService with ScoringSysPlug;
    provides IScoringSystem with ScoringSysPlug;
}