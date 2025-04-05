package util;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestReporter {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

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
}