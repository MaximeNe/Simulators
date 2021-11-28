package agentsystem;

import util.Event;
import util.Range;

/**
 * Apply an event to a given range of agents.
 */
public class AgentEvent<T> extends Event {
    private final IRule<T> rule;
    private final Range range;
    private final long increment;
    private final AgentSystem<T> agentSystem;

    public AgentEvent(long date, long increment, IRule<T> rule, Range range, AgentSystem<T> agentSystem) {
        super(date);
        this.increment = increment;
        this.rule = rule;
        this.range = range;
        this.agentSystem = agentSystem;
    }

    public AgentEvent(AgentEvent<T> other, long date) {
        super(date);
        this.increment = other.increment;
        this.rule = other.rule;
        this.range = other.range;
        this.agentSystem = other.agentSystem;
    }

    @Override
    public void execute() {
        for (int i = range.start(); i < range.end(); i++) {
            rule.apply(i, agentSystem.getGeneration(), agentSystem.getNextGeneration());
        }
        agentSystem.addEvent(new AgentEvent<>(this, getDate() + increment));
    }
}
