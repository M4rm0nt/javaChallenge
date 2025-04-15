package aufgabe8;

import java.util.Date;

public class Main {
    static class Tageskurs {
        private Date datum;
        private double aktienkurs;
        private double daxWert;
        private double prozVAktie;
        private double prozVDAX;

        public Tageskurs(Date datum, double aktienkurs, double daxWert, double prozVAktie, double prozVDAX) {
            this.datum = datum;
            this.aktienkurs = aktienkurs;
            this.daxWert = daxWert;
            this.prozVAktie = prozVAktie;
            this.prozVDAX = prozVDAX;
        }

        public double getProzVAktie() {
            return prozVAktie;
        }

        public double getProzVDAX() {
            return prozVDAX;
        }
    }

    public static void main(String[] args) {

        Tageskurs[] kurse = new Tageskurs[] {
                new Tageskurs(new Date(), 110.0, 15000.0, 0.00300, 0.0500),  // Aktie +0.3%, DAX +5.0% → nicht besser
                new Tageskurs(new Date(), 105.0, 15100.0, 0.00500, 0.0067),  // Aktie +0.5%, DAX +0.67% → nicht besser
                new Tageskurs(new Date(), 100.0, 14900.0, 0.00476, -0.0132), // Aktie +0.476%, DAX -1.32% → besser
                new Tageskurs(new Date(), 102.0, 14800.0, 0.02000, 0.0100),  // Aktie +2.0%, DAX +1.0% → besser
                new Tageskurs(new Date(), 101.0, 14950.0, -0.00980, -0.0200) // Aktie -0.98%, DAX -2.0% → besser (weil weniger negativ)
        };

        int anzahlBessereTage = notierungPlus(kurse);
        System.out.println("Anzahl Tage mit besserer Notierung: " + anzahlBessereTage);
    }

    public static int notierungPlus(Tageskurs[] kurse) {
        int count = 0;

        for (Tageskurs kurs : kurse) {
            if (kurs.getProzVAktie() > kurs.getProzVDAX()) {
                count++;
            }
        }

        return count;
    }
}