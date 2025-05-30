package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.event.Event;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    // delta is used to ensure constant movement speed
    // no matter the speed of the game loop
    private float delta;
    private List<Event> events = new CopyOnWriteArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }
    public void removeEvent(Event e) {
        events.remove(e);
    }
    public List<Event> getEvent() {
        return events;
    }
    public void setDelta(float delta) {
        this.delta = delta;
    }
    public float getDelta() {
        return delta;
    }
    public GameKeys getKeys() {
        return keys;
    }
    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }
    public int getDisplayWidth() {
        return displayWidth;
    }
    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }
    public int getDisplayHeight() {
        return displayHeight;
    }

    public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
        List<Event> r = new ArrayList();
        for (Event event : events) {
            if (event.getClass().equals(type) && event.getSource().getID().equals(sourceID)) {
                r.add(event);
            }
        }
        return r;
    }

}
