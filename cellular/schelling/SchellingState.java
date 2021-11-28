package cellular.schelling;

import cellular.ICellType;

import java.awt.*;

public class SchellingState implements ICellType {
    public static int FREE = 0;

    private static final Color[] COLOR_RANGE = {
        Color.blue,
        Color.red,
        Color.yellow,
        Color.magenta,
        Color.green,
        Color.orange,
        Color.darkGray,
        Color.cyan
    };

    @Override
    public Color getColor(int state) {
        if (state == 0) {
            return Color.white;
        }

        return COLOR_RANGE[state - 1];
    }
}
