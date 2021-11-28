package agentsystem;

import util.Range;

public record MultiRuleEntry<T>(IRangeRule<T> rule, int eventIncrement) {
}
