package bbm.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("bbm.task1.TestDataFactory#provideTraversalTestCases")
    void testTreeTraversal(TraversalTestCase testCase) {
        Supplier<List<String>> traversalSupplier = TraversalFactory.getTraversal(testCase.getTraversalType().toLowerCase());
        List<String> result = traversalSupplier.get();

        TestReporter.logTestCase("Baum-Traversierungstest", testCase.getTraversalType(), testCase.getDescription(), testCase.getExpected(), result);

        assertEquals(testCase.getExpected(), result);
    }
}
