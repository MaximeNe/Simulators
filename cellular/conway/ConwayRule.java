package cellular.conway;

import agentsystem.IGeneration;
import cellular.IGridGeneration;
import cellular.IGridRule;

import java.util.List;

/**
 * A rule for Conway's game of life.
 *
 * <p>
 * A {@link ConwayCell cell} is alive if:
 * <ul>
 *     <li>three neighbors are alive</li>
 *     <li>two neighbors and the cell itself is alive</li>
 * </ul>
 * Otherwise, the cell is dead.
 * </p>
 */
public class ConwayRule implements IGridRule {
    @Override
    public void apply(int i, IGeneration<Integer> currentGeneration, IGeneration<Integer> nextGeneration) {
        IGridGeneration g = (IGridGeneration) currentGeneration;
        IGridGeneration nextG = (IGridGeneration) nextGeneration;

        List<Integer> neighbors = g.getNeighbors(i);

        int aliveNeighbors = 0;
        for (int neighbor : neighbors) {
            if (neighbor == ConwayCell.ALIVE) {
                aliveNeighbors++;
            }
        }

        if (aliveNeighbors == 3 ||
                aliveNeighbors == 2 && g.get(i) == ConwayCell.ALIVE) {
            nextG.set(i, ConwayCell.ALIVE);
        } else {
            nextG.set(i, ConwayCell.DEAD);
        }
    }
}
