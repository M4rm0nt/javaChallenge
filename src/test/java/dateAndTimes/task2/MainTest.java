package dateAndTimes.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.TestReporter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @ParameterizedTest(name = "adjustToPayday({0}) => {1}, {2}")
    @CsvFileSource(resources = "/dateAndTimes/task2/payday_testcases.csv")
    void testAdjustInto(LocalDate startDay, LocalDate expected, String info) {
        Main adjuster = new Main();
        LocalDate result = LocalDate.from(adjuster.adjustInto(startDay));

        TestReporter.logTestCase("Payday-Test: " + startDay, startDay, info, expected, result);

        assertEquals(expected, result);
    }
}