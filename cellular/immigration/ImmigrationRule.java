package cellular.immigration;

import agentsystem.IGeneration;
import cellular.IGridGeneration;
import cellular.IGridRule;

import java.util.List;

/**
 * A rule for the immigration game.
 *
 * The subject is not clear with the behaviour of max state cell
 * We consider that a maxstate cell can become a cell of state 0 (circular)
 * We used circular grid (not specified in the subject)
 *
*/
public class ImmigrationRule implements IGridRule {
    private final int maxState;

    public ImmigrationRule(int maxState) {
        this.maxState = maxState;
    }

    @Override
    public void apply(int i, IGeneration<Integer> currentGeneration, IGeneration<Integer> nextGeneration) {
        IGridGeneration g = (IGridGeneration) currentGeneration;
        IGridGeneration nextG = (IGridGeneration) nextGeneration;

        int state = g.get(i);

        List<Integer> neighbors = g.getNeighbors(i);
        int nextStateNeighbors = 0;
        for (int neighbor : neighbors) {
            if (neighbor == (state + 1) % (maxState + 1)) {
                nextStateNeighbors++;
            }
        }

        if (nextStateNeighbors >= 3) {
            nextG.set(i, (state + 1) % (maxState + 1));
        }
    }
}
