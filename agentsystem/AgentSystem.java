package agentsystem;

import gui.GUISimulator;
import util.Event;
import util.EventManager;

import java.util.List;

/**
 * An agent system with only one rule. The rule is applied uniformly for all agents.
 */
public abstract class AgentSystem<T> implements IAgentSystem<T> {
    private final GUISimulator gui;
    private final EventManager eventManager;
    private final IDisplay<T> displayDelegate; // An delegate class responsible for displaying the generation
    private final IGeneration<T> initialGeneration; // Backup of the initial generation
    private IGeneration<T> generation; // Copy of the current generation
    private IGeneration<T> nextGeneration; // The next generation

    public AgentSystem(
            GUISimulator gui, IGeneration<T> generation, IDisplay<T> displayDelegate) {
        this.generation = generation;
        this.initialGeneration = generation;
        this.nextGeneration = generation.clone();
        this.eventManager = new EventManager();
        this.displayDelegate = displayDelegate;
        this.gui = gui;
    }

    private void display() {
        this.displayDelegate.display(gui, this.generation);
    }

    @Override
    public void addEvent(Event e) {
        eventManager.addEvent(e);
    }

    @Override
    public void setInitialEvents(List<Event> e) {
        eventManager.setInitialEvents(e);
    }

    @Override
    public void next() {
        gui.reset();
        nextGeneration = generation.clone();

        eventManager.next();

        generation = nextGeneration;
        display();
    }

    @Override
    public void restart() {
        gui.reset();
        eventManager.restart();
        generation = initialGeneration;
        nextGeneration = initialGeneration.clone();
        display();
    }

    @Override
    public IGeneration<T> getGeneration() {
        return generation;
    }

    @Override
    public IGeneration<T> getNextGeneration() {
        return nextGeneration;
    }

    @Override
    public GUISimulator getGui() {
        return gui;
    }
}
