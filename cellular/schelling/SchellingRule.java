package cellular.schelling;

import agentsystem.IGeneration;
import cellular.IGridRule;

import java.util.List;

/**
 * A rule for Schelling.
 */
public class SchellingRule implements IGridRule {
    private final int k;
    public SchellingRule(int k) {
        this.k = k;
    }

    @Override
    public void apply(int i, IGeneration<Integer> currentGeneration, IGeneration<Integer> nextGeneration) {
        SchellingGeneration g = (SchellingGeneration) currentGeneration;
        SchellingGeneration nextG = (SchellingGeneration) nextGeneration;
        int currentFamily = g.get(i);
        List<Integer> neighbors = g.getNeighbors(i);

        int differentNeighbors = 0;

        for (int neighbor : neighbors) {
            if (neighbor != SchellingState.FREE && neighbor != currentFamily) {
                differentNeighbors++;
            }
        }

        if (differentNeighbors >= k) {
            nextG.move(i);
        }
    }
}
