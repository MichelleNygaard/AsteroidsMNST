package dk.sdu.cbse.common.event;

import java.io.Serializable;
import dk.sdu.cbse.common.data.Entity;

public class Event implements Serializable {
    private final Entity source;
    public Event(Entity source) {
        this.source = source;
    }
    public Entity getSource() {
        return source;
    }
}
