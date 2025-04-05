package dateAndTimes.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.TestReporter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @ParameterizedTest(name = "isLeap({0}) => {2}, Hinweis: {1}")
    @CsvFileSource(resources = "/dateAndTimes/task1/leap_years.csv", numLinesToSkip = 1)
    void testIsLeap(int year, String hint, boolean expected) {
        boolean result = Main.isLeap_JDK(year);
        TestReporter.logTestCase("Schaltjahr-Test für " + year, year, hint, expected, result);
        assertEquals(expected, result);
    }
}