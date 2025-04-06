package bbm.task1;

import java.util.List;

public class TraversalTestCase {
    private final String traversalType;
    private final List<String> expected;
    private final String description;

    public TraversalTestCase(String traversalType, List<String> expected, String description) {
        this.traversalType = traversalType;
        this.expected = expected;
        this.description = description;
    }

    public String getTraversalType() {
        return traversalType;
    }

    public List<String> getExpected() {
        return expected;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return traversalType + " – " + description;
    }
}
