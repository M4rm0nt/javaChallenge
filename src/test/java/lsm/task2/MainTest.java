package lsm.task2;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    // ----------- Difference Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Difference)")
    @MethodSource("provideDifferenceTestCases")
    <T> void difference(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.difference(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "[{index}] {0} (DifferenceNOJDK)")
    @MethodSource("provideDifferenceTestCases")
    <T> void differenceNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.differenceNOJDK(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    // ----------- Union Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Union)")
    @MethodSource("provideUnionTestCases")
    <T> void union(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.union(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "[{index}] {0} (UnionNOJDK)")
    @MethodSource("provideUnionTestCases")
    <T> void unionNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.unionNOJDK(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    // ----------- Intersection Tests -----------
    @ParameterizedTest(name = "[{index}] {0} (Intersection)")
    @MethodSource("provideIntersectionTestCases")
    <T> void intersection(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.intersection(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "[{index}] {0} (IntersectionNOJDK)")
    @MethodSource("provideIntersectionTestCases")
    <T> void intersectionNOJDK(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected) {
        Set<T> result = Main.intersectionNOJDK(c1, c2);
        logTestResult(testName, c1, c2, expected, result);
        assertEquals(expected, result);
    }

    // ----------- Hilfsmethode für TestReporter -----------
    private <T> void logTestResult(String testName, Collection<T> c1, Collection<T> c2, Set<T> expected, Set<T> actual) {
        TestReporter.logTestCase(
                testName,
                c1,
                c2,
                expected,
                actual
        );
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