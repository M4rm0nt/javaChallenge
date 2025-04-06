package dateAndTimes.task1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @BeforeAll
    static void setup() {
        TestSuiteMetadata suiteMetadata = loadMetadata();
        TestReporter.logSuiteMetadata(
                suiteMetadata.getTestSuite(),
                suiteMetadata.getVersion(),
                suiteMetadata.getEnvironment()
        );
    }

    @AfterAll
    static void tearDown() {
        TestReporter.printSummary();
    }

    private static TestSuiteMetadata loadMetadata() {
        try (InputStream is = MainTest.class.getResourceAsStream("/dateAndTimes/task1/leap_years.json")) {
            if (is == null) {
                throw new IllegalStateException("Testdatei nicht gefunden");
            }
            ObjectMapper mapper = new ObjectMapper();
            LeapYearTestWrapper wrapper = mapper.readValue(is, LeapYearTestWrapper.class);
            return wrapper.getMetadata();
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Metadaten", e);
        }
    }

    @ParameterizedTest
    @MethodSource("provideLeapYearTestData")
    void testIsLeap(String testId, int year, String hint, boolean expected, List<String> tags) {
        long start = System.nanoTime();
        TestReporter.logTestStart(testId, tags);

        boolean result = Main.isLeap(year);
        double duration = (System.nanoTime() - start) / 1_000_000.0;

        TestReporter.logTestCaseDetails(year, hint, expected, result, duration);
        TestReporter.logFinalStatus(expected == result);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideLeapYearTestData() {
        InputStream is = MainTest.class.getResourceAsStream("/dateAndTimes/task1/leap_years.json");
        if (is == null) {
            System.err.println("[ERROR] Testdatei 'leap_years.json' nicht gefunden!");
            return Stream.empty();
        }

        try (is) {
            ObjectMapper mapper = new ObjectMapper();
            LeapYearTestWrapper wrapper = mapper.readValue(is, LeapYearTestWrapper.class);

            // Metadaten validieren und loggen
            if (wrapper.getTestCases() == null) {
                System.err.println("[WARN] Keine Testfälle gefunden");
                return Stream.empty();
            }

            if (wrapper.getMetadata() != null) {
                logMetadata(wrapper.getMetadata());
                validateTestCount(wrapper);
            }

            return wrapper.getTestCases().stream().flatMap(tc -> {
                try {
                    if (tc == null || !tc.isValid()) {
                        System.err.printf("[WARN] Ungültiger Testfall: %s%n", tc);
                        return Stream.empty();
                    }
                    return Stream.of(createArguments(tc));
                } catch (Exception e) {
                    System.err.printf("[ERROR] Fehler bei Testfall %s: %s%n", tc != null ? tc.getId() : "NULL", e.getMessage());
                    return Stream.empty();
                }
            });

        } catch (JsonProcessingException e) {
            System.err.println("[ERROR] JSON Fehler: " + e.getMessage());
            return Stream.empty();
        } catch (IOException e) {
            System.err.println("[ERROR] I/O Fehler: " + e.getMessage());
            return Stream.empty();
        }
    }

    private static void logMetadata(TestSuiteMetadata meta) {
        System.out.println("\n=== Testsuite Metadaten ===");
        System.out.println("Erstellungsdatum: " + meta.getTimestamp());
        System.out.println("Name: " + meta.getTestSuite());
        System.out.println("Version: " + meta.getVersion());
        System.out.println("Umgebung: " + meta.getEnvironment());
        System.out.println("Erwartete Tests: " + meta.getTotalTests());
    }

    private static void validateTestCount(LeapYearTestWrapper wrapper) {
        if (wrapper.getMetadata() != null && wrapper.getTestCases() != null) {
            int expected = wrapper.getMetadata().getTotalTests();
            int actual = wrapper.getTestCases().size();
            if (expected != actual) {
                System.err.printf("[WARN] Testanzahl mismatch: Erwartet %d, Gefunden %d%n", expected, actual);
            }
        }
    }

    private static Arguments createArguments(LeapYearTestCase tc) {
        return Arguments.of(tc.getId(), tc.getYear(), tc.getHint() != null ? tc.getHint() : "Kein Hinweis", tc.isExpected(), tc.getTags() != null ? tc.getTags() : Collections.emptyList());
    }

    // Region: JSON Model Classes
    private static class LeapYearTestWrapper {
        private TestSuiteMetadata metadata;
        private List<LeapYearTestCase> testCases;

        public TestSuiteMetadata getMetadata() {
            return metadata;
        }

        public void setMetadata(TestSuiteMetadata metadata) {
            this.metadata = metadata;
        }

        public List<LeapYearTestCase> getTestCases() {
            return testCases;
        }

        public void setTestCases(List<LeapYearTestCase> testCases) {
            this.testCases = testCases;
        }
    }
    private static class TestSuiteMetadata {
        private String testSuite;
        private String version;
        private String environment;
        private String timestamp;
        private int totalTests;

        // Getter & Setter
        public String getTestSuite() {
            return testSuite;
        }

        public void setTestSuite(String testSuite) {
            this.testSuite = testSuite;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public int getTotalTests() {
            return totalTests;
        }

        public void setTotalTests(int totalTests) {
            this.totalTests = totalTests;
        }
    }
    private static class LeapYearTestCase {
        private String id;
        private int year;
        private String hint;
        private boolean expected;
        private List<String> tags;
        private String description;

        public boolean isValid() {
            if (id == null || id.isBlank()) {
                System.err.println("Testfall hat keine ID");
                return false;
            }
            if (year < 1583) {
                System.err.printf("Ungültiges Jahr %d in Testfall %s%n", year, id);
                return false;
            }
            return true;
        }

        // Getter & Setter
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public boolean isExpected() {
            return expected;
        }

        public void setExpected(boolean expected) {
            this.expected = expected;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}