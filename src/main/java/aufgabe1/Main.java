package aufgabe1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Main {

    static class Abfahrtszeit {
        private Date datum;
        private Integer haltestellenNr;
        private Integer planAbfahrt;
        private Integer istAbfahrt;

        public Abfahrtszeit(Date datum, Integer haltestellenNr, Integer planAbfahrt, Integer istAbfahrt) {
            this.datum = datum;
            this.haltestellenNr = haltestellenNr;
            this.planAbfahrt = planAbfahrt;
            this.istAbfahrt = istAbfahrt;
        }

        public Integer getPlanAbfahrt() {
            return planAbfahrt;
        }

        public Integer getIstAbfahrt() {
            return istAbfahrt;
        }
    }

    public static void main(String[] args) {

        Abfahrtszeit[] abfahrtszeiten = new Abfahrtszeit[15];
        abfahrtszeiten[0] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 0, 480, 483);
        abfahrtszeiten[1] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 1, 485, 487);
        abfahrtszeiten[2] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 2, 490, 490);
        abfahrtszeiten[3] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 3, 495, 496);
        abfahrtszeiten[4] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 4, 500, 502);
        abfahrtszeiten[5] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 5, 505, 505);
        abfahrtszeiten[6] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 6, 510, 512);
        abfahrtszeiten[7] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 7, 515, 518);
        abfahrtszeiten[8] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 8, 520, 522);
        abfahrtszeiten[9] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 9, 525, 525);
        abfahrtszeiten[10] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 10, 530, 532);
        abfahrtszeiten[11] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 11, 535, 535);
        abfahrtszeiten[12] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 12, 540, 540);
        abfahrtszeiten[13] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 13, 545, 548);
        abfahrtszeiten[14] = new Abfahrtszeit(new Date(2024, Calendar.OCTOBER, 1), 14, 550, 550);

        System.out.println(Arrays.toString(ermittle_Fahrzeiten(abfahrtszeiten)));

    }

    public static Integer[] ermittle_Fahrzeiten(Abfahrtszeit[] zeiten) {
        Integer[] result = new Integer[15];
        for (int i = 0; i <= zeiten.length - 1; i++) {
            int diff = zeiten[i].getIstAbfahrt() - zeiten[i].getPlanAbfahrt();
            if (diff > 2) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }
}
