package cellular;

import agentsystem.IGeneration;
import agentsystem.IRule;

/**
 * A rule for grids.
 *
 * The instances of {@link IGeneration} received by this method are guaranteed to
 * be {@link IGridGeneration}s.
 */
//TODO: this solution is messy. An alternative would be using generics, but it seems
// to add too much complexity. Maybe we weren't successful the first time we tried?
public interface IGridRule extends IRule<Integer> {}
