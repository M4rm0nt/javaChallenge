package aufgabe7;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

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

        public Date getDatum() {
            return datum;
        }

        public double getAktienkurs() {
            return aktienkurs;
        }

        public double getDaxWert() {
            return daxWert;
        }

        public double getProzVAktie() {
            return prozVAktie;
        }

        public double getProzVDAX() {
            return prozVDAX;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Tageskurs tageskurs = (Tageskurs) o;
            return Double.compare(aktienkurs, tageskurs.aktienkurs) == 0 && Double.compare(daxWert, tageskurs.daxWert) == 0 && Double.compare(prozVAktie, tageskurs.prozVAktie) == 0 && Double.compare(prozVDAX, tageskurs.prozVDAX) == 0 && Objects.equals(datum, tageskurs.datum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(datum, aktienkurs, daxWert, prozVAktie, prozVDAX);
        }
    }

    public static void main(String[] args) {
        Tageskurs[] kurse = new Tageskurs[3];
        kurse[0] = new Tageskurs(new Date(), 110, 15000, 0.00300, 0.0500);
        kurse[1] = new Tageskurs(new Date(), 105, 15100, 0.00500, 0.0067);
        kurse[2] = new Tageskurs(new Date(), 100, 14900, 0.00476, -0.0132);

        Function<Tageskurs, Function<Tageskurs, Integer>> vergleicheAktienkurs =
                a -> b -> Double.compare(a.getAktienkurs(), b.getAktienkurs());

        sort(kurse, vergleicheAktienkurs);

        for (Tageskurs kurs : kurse) {
            System.out.println(kurs.getAktienkurs());
        }
    }

    public static void sort(Tageskurs[] kurse, Function<Tageskurs, Function<Tageskurs, Integer>> vergleiche) {

        for (int i = 1; i < kurse.length; i++) {

            Tageskurs current = kurse[i];
            int j = i - 1;

            while (j >= 0 && vergleiche.apply(kurse[j]).apply(current) > 0) {
                kurse[j + 1] = kurse[j];
                j--;
            }

            kurse[j + 1] = current;
        }

    }
}