package util;

import java.time.LocalDate;
import java.util.*;

public class TestReporter {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";

    public static void logTestStart(String testName) {
        System.out.println("\n" + CYAN + "➤➤➤ " + testName + RESET);
    }

    public static void logTestCaseDetails(String testName, int year, String hint, boolean expected, boolean actual, List<String> tags) {
        long startTime = System.nanoTime();
        boolean passed = expected == actual;
        double durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("═══════════════════════════════════════════");
        System.out.printf("%s%s%s%n", BLUE + "⚙️ Testname:", RESET, testName);
        System.out.printf("%s%s%d%n", YELLOW + "🔹 Jahr:", RESET, year);
        System.out.printf("%s%s%s%n", YELLOW + "🔹 Hinweis:", RESET, hint);
        System.out.printf("%s%s%b%n", GREEN + "✅ Erwartet:", RESET, expected);
        System.out.printf("%s%s%b%n", GREEN + "🔄 Ergebnis:", RESET, actual);
        System.out.printf("%s%s%.3f ms%n", YELLOW + "⏱️ Laufzeit:", RESET, durationMs);
        System.out.printf("%s%s%s%n%n", passed ? GREEN + "🟢 Status:" : RED + "🔴 Status:", RESET, passed ? "PASS" : "FAIL");
        System.out.printf("%s%s%s%n", PURPLE + "🏷️ Tags:", RESET, formatTags(tags));
    }

    public static void logMetadata(String testSuite, String version, String environment, int testCount) {
        System.out.println(PURPLE + "\n████████████ METADATEN ████████████" + RESET);
        System.out.printf("%s%-15s%s%s%n", CYAN + "📦 Testsuite:", RESET, testSuite);
        System.out.printf("%s%-15s%s%s%n", CYAN + "🏷️ Version:", RESET, version);
        System.out.printf("%s%-15s%s%s%n", CYAN + "🌐 Umgebung:", RESET, environment);
        System.out.printf("%s%-15s%s%d%n%n", CYAN + "🧪 Testfälle:", RESET, testCount);
    }

    private static String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return CYAN + "Keine Tags" + RESET;
        }
        return String.join(", ", tags.stream().map(t -> PURPLE + "#" + t + RESET).toList());
    }

    public static void logTestFailure(String testId, String expected, String actual) {
        System.out.printf("%s[FAIL]%s Test %s - Erwartet: %s, Bekommen: %s%n", RED, RESET, testId, GREEN + expected + RESET, RED + actual + RESET);
    }

    public static void logTestSuccess(String testId) {
        System.out.printf("%s[PASS]%s Test %s%n", GREEN, RESET, CYAN + testId + RESET);
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