package dateAndTimes.task1;

import java.time.Year;

public class Main {

    public static void main(String[] args) {

    }

    static boolean isLeap(final int year) {
        final boolean everyFourthYear = year % 4 == 0;
        // Jahre, die durch 100 teilbar sind, werden Säkularjahre genannt und sind keine Schaltjahre.
        final boolean isSecular = year % 100 == 0;
        // Allerdings sind Säkularjahre, die auch durch 400 teilbar sind, doch wieder Schaltjahre.
        final boolean isSecularSpecial = year % 400 == 0;
        return everyFourthYear && (!isSecular || isSecularSpecial);
    }

    static boolean isLeap_JDK(final int year) {
        return Year.of(year).isLeap();
    }
}
