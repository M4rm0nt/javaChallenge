package dateAndTimes.task2;

import java.time.*;
import java.time.temporal.*;

public class Main implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(final Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);
        boolean isDecember = date.getMonth() == Month.DECEMBER;
        int paymentDay = isDecember ? 15 : 25;

        if (date.getDayOfMonth() > paymentDay) {
            date = date.plusMonths(1);
            isDecember = date.getMonth() == Month.DECEMBER;
            paymentDay = isDecember ? 15 : 25;
        }

        date = date.withDayOfMonth(paymentDay);

        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            if (isDecember) {
                date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            } else {
                date = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
            }
        }

        return temporal.with(date);
    }
}