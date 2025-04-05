package lsm.task2;

import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

public class TestDataFactory {

    // ----------- Testfälle für Difference -----------
    public static Stream<Arguments> provideDifferenceTestCases() {
        return Stream.of(
                Arguments.of(
                        "Integers mit Schnittmengen",
                        Arrays.asList(1, 2, 3, 4, 5),
                        Arrays.asList(2, 4, 6, 7),
                        new HashSet<>(Arrays.asList(1, 3, 5))
                ),
                Arguments.of(
                        "Leere c1",
                        Collections.emptyList(),
                        Arrays.asList(2, 4),
                        Collections.emptySet()
                ),
                Arguments.of(
                        "Null in c1",
                        Arrays.asList(null, 1, 2),
                        Arrays.asList(2),
                        new HashSet<>(Arrays.asList(null, 1))
                )
        );
    }

    // ----------- Testfälle für Union -----------
    public static Stream<Arguments> provideUnionTestCases() {
        return Stream.of(
                Arguments.of(
                        "Integers mit Schnittmengen",
                        Arrays.asList(1, 2, 3, 4, 5),
                        Arrays.asList(2, 4, 6, 7),
                        new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
                ),
                Arguments.of(
                        "Leere c1",
                        Collections.emptyList(),
                        Arrays.asList(2, 4),
                        new HashSet<>(Arrays.asList(2, 4))
                )
        );
    }

    // ----------- Testfälle für Intersection -----------
    public static Stream<Arguments> provideIntersectionTestCases() {
        return Stream.of(
                Arguments.of(
                        "Integers mit Schnittmengen",
                        Arrays.asList(1, 2, 3, 4, 5),
                        Arrays.asList(2, 4, 6, 7),
                        new HashSet<>(Arrays.asList(2, 4))
                ),
                Arguments.of(
                        "Leere c1",
                        Collections.emptyList(),
                        Arrays.asList(2, 4),
                        Collections.emptySet()
                )
        );
    }
}