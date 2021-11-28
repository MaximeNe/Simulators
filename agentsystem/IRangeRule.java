package agentsystem;

import util.Range;

/**
 * A rule that only applies to a range of agents.
 */
public interface IRangeRule<T> extends IRule<T> {
    /**
     * Get the range of agents this rule will apply to.
     * @return The range of agents.
     */
    Range agentRange();
}
