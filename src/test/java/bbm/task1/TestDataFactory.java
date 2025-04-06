package bbm.task1;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestDataFactory {

    public static Stream<Arguments> provideTraversalTestCases() {
        return Stream.of(
                new TraversalTestCase("inorder", Arrays.asList("a1", "b2", "c3", "d4", "e5", "f6", "g7"), "Standard Inorder-Traversierung"),
                new TraversalTestCase("preorder", Arrays.asList("d4", "b2", "a1", "c3", "f6", "e5", "g7"), "Standard Preorder-Traversierung"),
                new TraversalTestCase("postorder", Arrays.asList("a1", "c3", "b2", "e5", "g7", "f6", "d4"), "Standard Postorder-Traversierung")
        ).map(Arguments::of);
    }
}
