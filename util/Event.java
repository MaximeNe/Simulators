package util;


/**
 * Abstract class representing an Event.
 * The programmer should create a subclass implementing "execute"
 */
public abstract class Event {
    private long date;

    public Event(long date) {
        this.date = date;
    }

    /**
     * @return the date the event is supposed to be executed.
     */
    public long getDate() {
        return date;
    }

    /**
     * Execute the event
     */
    public abstract void execute();
}
