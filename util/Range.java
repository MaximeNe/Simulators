package util;

/**
 * A range of numbers: [start; end[
 * The range is exclusive and does not include end.
 */
public record Range(int start, int end) {
    public int size() {
        return end - start;
    }
}
