package lsm.task2;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    // ----------- Generische Testmethode für alle Operationen -----------
    private <T> void testOperation(String testName, BiFunction<Collection<T>, Collection<T>, Set<T>> operation, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = operation.apply(c1, c2);
        logTestResult(testName, c1, c2, expected, result);

        Set<T> missing = new HashSet<>(expected);
        missing.removeAll(result);
        Set<T> extra = new HashSet<>(result);
        extra.removeAll(expected);

        assertAll(() -> assertTrue(missing.isEmpty(), String.format("Falsches Elemente: %s", missing)), () -> assertTrue(extra.isEmpty(), String.format("Zusätzliche Elemente: %s", extra)));
    }

    // ----------- Difference Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Difference)")
    @MethodSource("provideDifferenceTestCases")
    <T> void difference(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::difference, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (DifferenceStream)")
    @MethodSource("provideDifferenceTestCases")
    <T> void differenceStream(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::differenceStream, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (DifferenceNOJDK)")
    @MethodSource("provideDifferenceTestCases")
    <T> void differenceNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::differenceNOJDK, c1, c2, expected);
    }

    // ----------- Union Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Union)")
    @MethodSource("provideUnionTestCases")
    <T> void union(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::union, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (UnionStream)")
    @MethodSource("provideUnionTestCases")
    <T> void unionStream(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::unionStream, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (UnionNOJDK)")
    @MethodSource("provideUnionTestCases")
    <T> void unionNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::unionNOJDK, c1, c2, expected);
    }

    // ----------- Intersection Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Intersection)")
    @MethodSource("provideIntersectionTestCases")
    <T> void intersection(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::intersection, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (IntersectionStream)")
    @MethodSource("provideIntersectionTestCases")
    <T> void intersectionStream(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::intersectionStream, c1, c2, expected);
    }

    @ParameterizedTest(name = "[{index}] {0} (IntersectionNOJDK)")
    @MethodSource("provideIntersectionTestCases")
    <T> void intersectionNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        testOperation(testName, Main::intersectionNOJDK, c1, c2, expected);
    }

    // ----------- Hilfsmethode für TestReporter -----------
    private <T> void logTestResult(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected, Set<T> actual) {
        TestReporter.logTestCase(testName, c1, c2, expected, actual);
    }

    // ----------- Datenquellen -----------
    static Stream<Arguments> provideDifferenceTestCases() {
        return TestDataFactory.provideDifferenceTestCases();
    }

    static Stream<Arguments> provideUnionTestCases() {
        return TestDataFactory.provideUnionTestCases();
    }

    static Stream<Arguments> provideIntersectionTestCases() {
        return TestDataFactory.provideIntersectionTestCases();
    }
}