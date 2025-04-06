package bbm.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.TestReporter;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final BinaryTreeNode<String> exampleTree = ExampleTrees.createExampleTree();

    @ParameterizedTest(name = "{0} Traversal: {2}")
    @CsvFileSource(resources = "/bbm/task1/tree_traversal_testcases.csv", delimiter = ',', numLinesToSkip = 1)
    void testTreeTraversals(String traversalType, String expectedResult, String description) {
        String cleanedExpected = expectedResult.replaceAll("'", "");
        List<String> result = getResultForTraversalType(traversalType);
        List<String> expected = Arrays.asList(cleanedExpected.split(","));

        // Debug-Ausgabe
        TestReporter.logTestCase("Baum-Traversierungstest", traversalType, description, expected,
                result);

        assertEquals(expected, result);
    }

    private List<String> getResultForTraversalType(String traversalType) {
        return switch (traversalType.toLowerCase()) {
            case "inorder" -> TreeTraversal.toListInorder(exampleTree);
            case "preorder" -> TreeTraversal.toListPreorder(exampleTree);
            case "postorder" -> TreeTraversal.toListPostorder(exampleTree);
            default -> throw new IllegalArgumentException("Unbekannte Traversierungsart: " + traversalType);
        };
    }
}