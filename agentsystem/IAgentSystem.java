package agentsystem;

import gui.GUISimulator;
import gui.Simulable;
import util.Event;

import java.util.List;

/**
 * An simulator has a {@link IGeneration} and one or multiple {@link IRule}.
 * It applies those rules to the generation to get the next state.
 */
public interface IAgentSystem<T> extends Simulable {
    /**
     * Get the current generation.
     * @return The generation.
     */
    IGeneration<T> getGeneration();

    /**
     * Get the next generation.
     * @return The next generation.
     */
    IGeneration<T> getNextGeneration();

    /**
     * Get the gui for displaying purposes.
     * @return The gui.
     */
    GUISimulator getGui();

    /**
     * Set the initial events of the start of this simulation..
     * @param e The list of initial events.
     */
    void setInitialEvents(List<Event> e);

    /**
     * Add an event to the agent's system queue.
     * @param e The event.
     */
    void addEvent(Event e);
}
