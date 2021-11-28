/**
 * Simulates the behavior of independent agents, according to some behavior rules.
 *
 * <p>
 *     Two agent systems are offered: a {@link agentsystem.SingleRuleSystem} and a
 *     {@link agentsystem.MultiRuleSystem}. Both implement {@link agentsystem.IAgentSystem}.
 * </p>
 *
 * <p>
 *     Data and business logic are separate, like in an entity-component system.
 *     The data is stored in an {@link agentsystem.IGeneration}. Access the data by passing
 *     an agent id to methods of an Generation.
 * </p>
 *
 * <p>
 *     {@link agentsystem.IRule} contain the business logic. Each rule represents the behavior
 *     of a single agent. In practice, this rule is applied to one or many agents in the Simulator.
 * </p>
 */

package agentsystem;