package agentsystem;

import gui.GUISimulator;
import util.Event;
import util.Range;

import java.util.ArrayList;

/**
 * An agent system with only one rule. The rule is applied to all agents.
 */
public class SingleRuleSystem<T> extends AgentSystem<T> {
    public SingleRuleSystem(
            GUISimulator gui, IGeneration<T> generation, IRule<T> rule, IDisplay<T> display) {
        super(gui, generation, display);
        Event e = new AgentEvent<>(
                0, 1, rule,
                new Range(0, generation.nbAgents()),this);
        ArrayList<Event> el = new ArrayList<>();
        el.add(e);
        setInitialEvents(el);
    }
}
