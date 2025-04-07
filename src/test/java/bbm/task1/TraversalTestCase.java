package bbm.task1;

import java.util.List;

public record TraversalTestCase(String traversalType, List<String> expected, String description) {

    @Override
    public String toString() {
        return traversalType + " – " + description;
    }
}
