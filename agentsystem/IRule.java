package agentsystem;

/**
 * Defines a behavior for an agent.
 */
public interface IRule<T> {
    /**
     * Apply the rule for one agent to a given {@link IGeneration}.
     * Code in this method should modify nextGeneration. A copy of the currentGeneration
     * is provided to read from. Do not read from nextGeneration, as the values there may have
     * been mutated by other agents;
     *
     * @param i The id of the agent.
     * @param currentGeneration The current generation. This is useful for reading values that may already
     *                          have been mutated by other agents.
     * @param nextGeneration The next generation. This is the one you should modify.
     */
    void apply(int i, IGeneration<T> currentGeneration, IGeneration<T> nextGeneration);
}
