package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Bitte geben Sie den Wochentag und das Datum ein.");
            System.out.println("Beispiel: java Main freitag 05.05.2005");
            return;
        }

        String inputDay = args[0].toLowerCase();
        String inputDate = args[1];

        List<String> validDays = Arrays.asList("montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag");

        if (!validDays.contains(inputDay)) {
            System.out.println("Fehler: '" + args[0] + "' ist kein gültiger Wochentag.");
            System.out.println("Gültige Wochentage sind: Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            String actualDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN).toLowerCase();

            if (inputDay.equals(actualDay)) {
                System.out.println("Ja, der " + inputDate + " war ein " + actualDay + ".");
            } else {
                System.out.println("Nein, der " + inputDate + " war kein " + inputDay + ", sondern ein " + actualDay + ".");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen des Datums. Bitte verwenden Sie das Format dd.MM.yyyy");
        }
    }
}