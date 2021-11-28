package agentsystem;

import gui.GUISimulator;
import util.Event;


import java.util.List;
import java.util.stream.Collectors;

/**
 * An agent system with multiple rule. Each rule is applied to each range of agents.
 */
public class MultiRuleSystem<T> extends AgentSystem<T> {
    public MultiRuleSystem(
            GUISimulator gui, IGeneration<T> generation, List<MultiRuleEntry<T>> ruleEntries, IDisplay<T> display) {
        super(gui, generation, display);

        List<Event> initialEvents = ruleEntries
                .stream()
                .map(r -> new AgentEvent<>(0, r.eventIncrement(), r.rule(), r.rule().agentRange(), this))
                .collect(Collectors.toList());

        setInitialEvents(initialEvents);
    }
}
