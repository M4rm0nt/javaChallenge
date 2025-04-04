package lsm;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    <T> void testFindCommonNOJDK(Collection<T> collection1, Collection<T> collection2, Set<T> expected) {
        Set<T> result = Main.findCommonNoJdk(collection1, collection2);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    <T> void testFindCommonJDK(Collection<T> collection1, Collection<T> collection2, Set<T> expected) {
        Set<T> result = Main.findCommon(collection1, collection2);
        assertEquals(expected, result);
    }

    Stream<Arguments> provideTestCases() {
        return Stream.of(
                // Testfall 1: Strings
                Arguments.of(
                        Arrays.asList("Apfel", "Banane", "Kirsche", "Erdbeere"),
                        Arrays.asList("Banane", "Erdbeere", "Kiwi", "Pfirsich"),
                        new HashSet<>(Arrays.asList("Banane", "Erdbeere"))
                ),

                // Testfall 2: Integers
                Arguments.of(
                        Arrays.asList(1, 2, 4, 7, 8),
                        Arrays.asList(2, 3, 7, 9),
                        new HashSet<>(Arrays.asList(2, 7))
                ),

                // Testfall 3: Leere Schnittmenge
                Arguments.of(
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(40, 50, 60),
                        Collections.emptySet()
                ),

                // Testfall 4: Gleiche Collections
                Arguments.of(
                        Arrays.asList("a", "b", "c"),
                        Arrays.asList("a", "b", "c"),
                        new HashSet<>(Arrays.asList("a", "b", "c"))
                )
        );
    }
}
