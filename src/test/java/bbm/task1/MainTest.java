package bbm.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("bbm.task1.TestCaseProvider#provideTraversalTestCases")
    void testTreeTraversal(TraversalTestCase testCase) {
        Supplier<List<String>> traversalSupplier = TraversalFactory.getTraversal(testCase.traversalType().toLowerCase());
        List<String> result = traversalSupplier.get();

        TestReporter.logTestCase("Baum-Traversierungstest", testCase.traversalType(), testCase.description(), testCase.expected(), result);

        assertEquals(testCase.expected(), result);
    }
}
