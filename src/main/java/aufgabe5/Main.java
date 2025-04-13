package aufgabe5;

import java.util.Scanner;

public class Main {

    private static final double SAEURE_GRENZWERT = 10.0;
    private static final double FAKTOR_RECHNUNG = 1.2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie den Säuregehalt der Trauben ein: ");
        double saeuregehalt = scanner.nextDouble();

        if (saeuregehalt > SAEURE_GRENZWERT) {
            System.out.println("Säuregehalt zu hoch");
        } else {
            System.out.print("Bitte geben Sie den Oechslegrad ein: ");
            double oechslegrad = scanner.nextDouble();

            double reifegrad = berechneReifegrad(oechslegrad);

            if (reifegrad >= 100) {
                System.out.println("hoher Reifegrad");
            } else if (reifegrad >= 80) {
                System.out.println("mittlerer Reifegrad");
            } else if (reifegrad >= 70) {
                System.out.println("niedriger Reifegrad");
            } else {
                System.out.println("Reifegrad zu niedrig");
            }
        }

        scanner.close();
    }
    private static double berechneReifegrad(double oechslegrad) {
        return oechslegrad * FAKTOR_RECHNUNG;
    }
}