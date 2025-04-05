package util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestReporter {

    // RESET wird benutzt um die Terminalfarbe nach der Ausgabe eines bestimmten Abschnittes wieder auf die default farbe zu bringen.
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static <T> void logTestCase(String testName,
                                       Collection<T> input1,
                                       Collection<T> input2,
                                       Collection<T> expected,
                                       Collection<T> actual) {
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
            Set<T> missing = new HashSet<>(expected);
            missing.removeAll(actual);
            Set<T> extra = new HashSet<>(actual);
            extra.removeAll(expected);
            if (!missing.isEmpty()) {
                System.out.printf("%sFehlende Elemente:%s %s%n", RED, RESET, missing);
            }
            if (!extra.isEmpty()) {
                System.out.printf("%sZusätzliche Elemente:%s %s%n", RED, RESET, extra);
            }
        }
        System.out.println("===========================================");
        System.out.println();
    }
}