package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EventManager {
    private long currentDate; // The current date of the manager. It will be increased by one each time next() is called
    private PriorityQueue<Event> eventList; // The events are stored in a priority queue
    private List<Event> initialEvents; // The initial events at the start of the simulation

    /**
     * Create a new EventManager with currentDate = 0 and an empty list of Events
     */
    public EventManager() {
        this.initialEvents = new ArrayList<>();
        // create a priorityqueue with a custom comparator comparing the dates
        this.eventList = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event event, Event t1) {
                if (event.getDate() > t1.getDate()) {
                    return 1;
                }
                if (event.getDate() == t1.getDate()) {
                    return 0;
                }
                return -1;
            }
        });
    }

    public void addEvent(Event e) {
        eventList.add(e);
    }

    public void setInitialEvents(List<Event> initialEvents) {
        this.initialEvents = initialEvents;
        eventList.addAll(initialEvents);
    }

    /**
     * Execute all the events whose date are less or equal than the current date
     */
    public void next() {
        boolean finished = false;
        currentDate += 1; // increase the date

        while (eventList.size() > 0 && !finished) { // While (there are events in eventList and we did not finish executing the events we want)
            Event currentEvent = eventList.poll();
            if (currentEvent.getDate() <= currentDate) {
                // execute the event
                currentEvent.execute();
            }
            else {
                // all the next events and the currentEvent should not be executed, we put back currentEvent in eventList
                finished = true;
                eventList.add(currentEvent);
            }
        }
    }


    /**
     *
     * @return true if there is no events left
     */
    public boolean isFinished() {
        return eventList.size() == 0; // TODO : utiliser ca à un moment donné
    }


    /**
     * Rollback to the initial state of the manager.
     */
    public void restart() {
        currentDate = 0; // Reset the date

        // Remove all the waiting events
        eventList.clear();

        eventList.addAll(initialEvents);
    }
}