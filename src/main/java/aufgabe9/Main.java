package aufgabe9;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    static class Cometeave {
        private Date date;
        private int comehrOut;
        private int noPeople;

        public Cometeave(Date date, int comehrOut, int noPeople) {
            this.date = date;
            this.comehrOut = comehrOut;
            this.noPeople = noPeople;
        }

        public Date getDate() {
            return date;
        }

        public int getComehrOut() {
            return comehrOut;
        }

        public int getNoPeople() {
            return noPeople;
        }
    }

    public static void main(String[] args) {
        List<Cometeave> entry = new ArrayList<>();
        entry.add(new Cometeave(createDate(2023, 5, 22, 9, 0), 0, 1));   // 1 Person betritt um 9:00
        entry.add(new Cometeave(createDate(2023, 5, 22, 9, 0), 0, 2));   // 2 Personen betreten um 9:00
        entry.add(new Cometeave(createDate(2023, 5, 22, 9, 1), 0, 30));  // 30 Personen betreten um 9:01
        entry.add(new Cometeave(createDate(2023, 5, 22, 12, 0), 1, 1));  // 1 Person verlässt um 12:00
        entry.add(new Cometeave(createDate(2023, 5, 22, 12, 1), 0, 2)); // 2 Personen betreten um 12:01
        entry.add(new Cometeave(createDate(2023, 5, 22, 12, 2), 1, 1));  // 1 Person verlässt um 12:02

        int[][] result = countVisitors(entry);

        printResult(result);
    }

    private static Date createDate(int year, int month, int day, int hour, int minute) {
        return new Date(year - 1900, month - 1, day, hour, minute);
    }

    public static int[][] countVisitors(List<Cometeave> entry) {
        if (entry.isEmpty()) {
            return new int[0][0];
        }

        Date firstDate = entry.getFirst().getDate();
        int daysInMonth = getDaysOfMonth(firstDate);
        int hoursInDay = 10; // 9:00-18:00 (10 Stunden)

        int[][] visitors = new int[daysInMonth][hoursInDay];

        for (int day = 0; day < daysInMonth; day++) {
            for (int hour = 0; hour < hoursInDay; hour++) {
                visitors[day][hour] = 0;
            }
        }

        for (Cometeave record : entry) {
            Date date = record.getDate();
            int day = date.getDate() - 1; // Tag im Monat (0-basiert)
            int hour = date.getHours();
            int comehrOut = record.getComehrOut();
            int noPeople = record.getNoPeople();

            // Nur Öffnungszeiten berücksichtigen (9:00-18:59)
            if (hour < 9 || hour > 18) {
                continue;
            }

            int hourIndex = hour - 9;

            if (comehrOut == 0) {
                for (int h = hourIndex; h < hoursInDay; h++) {
                    visitors[day][h] += noPeople;
                }
            } else {
                for (int h = hourIndex; h < hoursInDay; h++) {
                    visitors[day][h] -= noPeople;
                }
            }
        }

        return visitors;
    }

    private static void printResult(int[][] result) {
        System.out.println("Tag/Stunde | 9:00-9:59 | 10:00-10:59 | 11:00-11:59 | 12:00-12:59 | 13:00-13:59 | 14:00-14:59 | 15:00-15:59 | 16:00-16:59 | 17:00-17:59 | 18:00-18:59");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int day = 0; day < result.length; day++) {
            System.out.printf("%-11d", day + 1);
            for (int hour = 0; hour < result[day].length; hour++) {
                System.out.printf("| %-10d ", result[day][hour]);
            }
            System.out.println();
        }
    }

    private static int getDaysOfMonth(Date date) {
        return 31;
    }
}