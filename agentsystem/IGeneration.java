package agentsystem;

/**
 * A generation is the state of the world at a given time.
 *
 * It will contain data for each Agent, but does not contain the
 * Agents themselves. It also exposes accessors to get the data given an agent id.
 *
 * @param <T> The data type, or the component, associated with each agent of this generation.
 */
public interface IGeneration<T> {
    /**
     * Return a copy of this object.
     * @return The copy.
     */
    IGeneration<T> clone();

    /**
     * How many agents does this generation contain?
     * @return The number of agents that are in this generation.
     */
    int nbAgents();

    /**
     * Get the data associated with the agent at index i.
     * @return The data of this agent.
     */
    T get(int i);


    /**
     * Set data for an agent at index i.
     * @param value The value to set.
     */
    void set(int i, T value);
}
