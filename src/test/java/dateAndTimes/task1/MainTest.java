package dateAndTimes.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @ParameterizedTest(name = "isLeap({0}) => {2}, Hinweis: {1}")
    @MethodSource("provideLeapYearTestData")
    void testIsLeap(int year, String hint, boolean expected) {
        boolean result = Main.isLeap_JDK(year);
        TestReporter.logTestCase("Schaltjahr-Test für " + year, year, hint, expected, result);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideLeapYearTestData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = MainTest.class.getResourceAsStream("/dateAndTimes/task1/leap_years.json");
        LeapYearTestCase[] testCases = mapper.readValue(is, LeapYearTestCase[].class);

        return Stream.of(testCases).map(tc -> Arguments.of(tc.year, tc.hint, tc.expected));
    }

    // Hilfsklasse für JSON Deserialisierung
    private static class LeapYearTestCase {
        public int year;
        public String hint;
        public boolean expected;
    }

    /*
    @ParameterizedTest(name = "isLeap({0}) => {2}, Hinweis: {1}")
    @CsvFileSource(resources = "/dateAndTimes/task1/leap_years.csv", numLinesToSkip = 1)
    void testIsLeap(int year, String hint, boolean expected) {
        boolean result = Main.isLeap_JDK(year);
        TestReporter.logTestCase("Schaltjahr-Test für " + year, year, hint, expected, result);
        assertEquals(expected, result);
    }
     */
}