package util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TestReporter {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";

    // Statistik Variablen
    private static final AtomicInteger totalTests = new AtomicInteger(0);
    private static final AtomicInteger passedTests = new AtomicInteger(0);
    private static final AtomicLong totalDuration = new AtomicLong(0);
    private static final LocalDateTime startTime = LocalDateTime.now();

    public static void logTestStart(String testId, List<String> tags) {
        System.out.println(CYAN + "\n➤➤➤ TESTFALL " + testId + " " + formatTags(tags) + RESET);
    }

    public static void logTestCaseDetails(int year, String hint, boolean expected, boolean actual, double durationMs) {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long memUsage = memoryBean.getHeapMemoryUsage().getUsed() / 1024 / 1024;

        System.out.println("═══════════════════════════════════════════");
        System.out.printf("%s%-18s%s%n", BLUE + "⚙️ Eingabeparameter:", RESET, "");
        System.out.printf("   - Jahr: %s%d%s%n", YELLOW, year, RESET);
        System.out.printf("   - Hinweis: %s\"%s\"%s%n", YELLOW, hint, RESET);
        System.out.printf("%s%-18s%s%b%n", GREEN + "✅ Erwartet:", RESET, expected);
        System.out.printf("%s%-18s%s%b%n", GREEN + "🔄 Ergebnis:", RESET, actual);
        System.out.printf("%s%-18s%s%.2f ms%n", YELLOW + "⏱️ Laufzeit:", RESET, durationMs);
        System.out.printf("%s%-18s%s%d MB%n", BLUE + "📊 Ressourcen:", RESET, memUsage);
        logSystemInfo();
    }

    public static void logFinalStatus(boolean passed) {
        if (passed) {
            passedTests.incrementAndGet();
            System.out.printf("%s%-18s%s%n%n", GREEN + "🟢 STATUS:", RESET, "PASS");
        } else {
            System.out.printf("%s%-18s%s%n%n", RED + "🔴 STATUS:", RESET, "FAIL");
        }
        totalTests.incrementAndGet();
    }

    public static void printSummary() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        double avgDuration = totalTests.get() > 0 ?
                totalDuration.get() / (double) totalTests.get() : 0;

        System.out.println(PURPLE + "\n████████████ ZUSAMMENFASSUNG ████████████" + RESET);
        System.out.printf("%s%d/%d Tests erfolgreisch%s | ",
                GREEN, passedTests.get(), totalTests.get(), RESET);
        System.out.printf("%s⏱️ Gesamtzeit: %d s | Ø: %.2f ms%s%n",
                YELLOW, duration.getSeconds(), avgDuration, RESET);
        System.out.printf("%s🗓️ Testlauf gestartet: %s%s%n",
                CYAN, startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), RESET);
    }

    private static void logSystemInfo() {
        Properties props = System.getProperties();
        System.out.printf("%s%-18s%s%n", CYAN + "📂 Umgebung:", RESET,
                String.format("JDK %s | %s %s | %s",
                        props.getProperty("java.version"),
                        props.getProperty("os.name"),
                        props.getProperty("os.version"),
                        props.getProperty("os.arch")
                ));
    }

    public static void logSuiteMetadata(String testSuite, String version, String environment) {
        System.out.println(PURPLE + "\n████████████ TESTSUITE: " + testSuite.toUpperCase() + " ████████████" + RESET);
        System.out.printf("%s%-12s%s%s%n",
                CYAN + "📅 Erstellt am:" + RESET,
                "",  // Leerstring als Trennzeichen
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.printf("%s%-12s%s%s%n",
                CYAN + "🏷️ Version:" + RESET,
                "",  // Leerstring als Trennzeichen
                version);
        System.out.printf("%s%-12s%s%s%n%n",
                CYAN + "🌍 Umgebung:" + RESET,
                "",  // Leerstring als Trennzeichen
                environment);
    }

    private static String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) return "";
        return tags.stream()
                .map(t -> PURPLE + "#" + t + RESET)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    public static <T> void logTestCase(String testName, Collection<T> input1, Collection<T> input2, Collection<T> expected, Collection<T> actual) {
        long startTime = System.nanoTime();

        Set<T> expectedSet = new HashSet<>(expected);
        Set<T> actualSet = new HashSet<>(actual);

        boolean passed = expectedSet.equals(actualSet);
        double durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("===========================================");
        System.out.printf("%s🧪 %s%s%n", BLUE, testName, RESET);
        System.out.println("-------------------------------------------");
        System.out.printf("%s🔹 Input 1:%s %s%n", YELLOW, RESET, input1);
        System.out.printf("%s🔹 Input 2:%s %s%n", YELLOW, RESET, input2);
        System.out.printf("%s🔸 Expected:%s %s%n", GREEN, RESET, expected);
        System.out.printf("%s🔸 Actual:%s   %s%n", GREEN, RESET, actual);
        System.out.printf("%s⏱️ Laufzeit:%s %.3f ms%n", YELLOW, RESET, durationMs);

        if (passed) {
            System.out.println(GREEN + "✓ PASS" + RESET);
        } else {
            System.out.println(RED + "✗ FAIL" + RESET);
        }

        System.out.println("===========================================");
        System.out.println();
    }

    public static void logTestCase(String testName, int input, String hint, boolean expected, boolean actual) {
        long startTime = System.nanoTime();
        boolean passed = expected == actual;
        double durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("===========================================");
        System.out.printf("%s🧪 %s%s%n", BLUE, testName, RESET);
        System.out.println("-------------------------------------------");
        System.out.printf("%s🔹 Jahr:%s %d%n", YELLOW, RESET, input);
        System.out.printf("%s🔹 Hinweis:%s %s%n", YELLOW, RESET, hint);
        System.out.printf("%s🔸 Erwartet:%s %s%n", GREEN, RESET, expected);
        System.out.printf("%s🔸 Ergebnis:%s %s%n", GREEN, RESET, actual);
        System.out.printf("%s⏱️ Laufzeit:%s %.3f ms%n", YELLOW, RESET, durationMs);

        if (passed) {
            System.out.println(GREEN + "✓ PASS" + RESET);
        } else {
            System.out.println(RED + "✗ FAIL" + RESET);
        }

        System.out.println("===========================================");
        System.out.println();
    }

    public static void logTestCase(String testName, LocalDate inputDate, String description, LocalDate expected, LocalDate actual) {
        long startTime = System.nanoTime();
        boolean passed = expected.equals(actual);
        double durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("===========================================");
        System.out.printf("%s🧪 %s%s%n", BLUE, testName, RESET);
        System.out.println("-------------------------------------------");
        System.out.printf("%s🔹 Eingabedatum:%s %s%n", YELLOW, RESET, inputDate);
        System.out.printf("%s🔹 Regel:%s %s%n", YELLOW, RESET, description);
        System.out.printf("%s🔸 Erwartet:%s %s%n", GREEN, RESET, expected);
        System.out.printf("%s🔸 Ergebnis:%s %s%n", GREEN, RESET, actual);
        System.out.printf("%s⏱️ Laufzeit:%s %.3f ms%n", YELLOW, RESET, durationMs);

        if (passed) {
            System.out.println(GREEN + "✓ PASS" + RESET);
        } else {
            System.out.println(RED + "✗ FAIL" + RESET);
            System.out.printf("%sDifferenz:%s %d Tage%n", RED, RESET, Math.abs(expected.until(actual).getDays()));
        }

        System.out.println("===========================================");
        System.out.println();
    }

    public static <T> void logTestCase(String testName, String traversalType, String description, List<T> expected, List<T> actual) {
        long startTime = System.nanoTime();
        boolean passed = expected.equals(actual);
        double durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("===========================================");
        System.out.printf("%s🧪 %s%s%n", BLUE, testName, RESET);
        System.out.println("-------------------------------------------");
        System.out.printf("%s🔹 Traversal-Typ:%s %s%n", YELLOW, RESET, traversalType);
        System.out.printf("%s🔹 Beschreibung:%s %s%n", YELLOW, RESET, description);
        System.out.printf("%s🔸 Erwartete Reihenfolge:%s %s%n", GREEN, RESET, expected);
        System.out.printf("%s🔸 Tatsächliche Reihenfolge:%s %s%n", GREEN, RESET, actual);
        System.out.printf("%s⏱️ Laufzeit:%s %.3f ms%n", YELLOW, RESET, durationMs);

        if (passed) {
            System.out.println(GREEN + "✓ PASS" + RESET);
        } else {
            System.out.println(RED + "✗ FAIL" + RESET);
            System.out.printf("%sDifferenz:%s %s%n", RED, RESET, findDifference(expected, actual));
        }

        System.out.println("===========================================");
        System.out.println();
    }

    private static <T> String findDifference(List<T> expected, List<T> actual) {
        List<T> missing = new ArrayList<>(expected);
        missing.removeAll(actual);

        List<T> extra = new ArrayList<>(actual);
        extra.removeAll(expected);

        return String.format("Fehlende Elemente: %s, Zusätzliche Elemente: %s", missing, extra);
    }
}