package aufgabe2;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Main {

    static class Abfahrt {
        private int verbindungsId;
        private int haltestellenId;
        private Date abfahrtsZeit;

        public Abfahrt(int verbindungsId, int haltestellenId, Date abfahrtsZeit) {
            this.verbindungsId = verbindungsId;
            this.haltestellenId = haltestellenId;
            this.abfahrtsZeit = abfahrtsZeit;
        }

        public int getHaltestellenId() {
            return haltestellenId;
        }

        public Date getAbfahrtsZeit() {
            return abfahrtsZeit;
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy:HH:mm:ss");
            return "\n\tAbfahrt: " + "\t{ " + haltestellenId + ", " + sdf.format(abfahrtsZeit) + " }";
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Abfahrt abfahrt = (Abfahrt) o;
            return verbindungsId == abfahrt.verbindungsId && haltestellenId == abfahrt.haltestellenId && Objects.equals(abfahrtsZeit, abfahrt.abfahrtsZeit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(verbindungsId, haltestellenId, abfahrtsZeit);
        }
    }

    static class FahrplanService {

        private Abfahrt[] abfahrten;

        public FahrplanService() {
            this.abfahrten = new Abfahrt[6];
            Date startDatum = new Date();

            abfahrten[0] = new Abfahrt(1, 100, startDatum);
            abfahrten[1] = new Abfahrt(2, 200, new Date(startDatum.getTime() + 24 * 60 * 60 * 1000));
            abfahrten[2] = new Abfahrt(3, 300, new Date(startDatum.getTime() + 2 * 24 * 60 * 60 * 1000));
            abfahrten[3] = new Abfahrt(4, 400, new Date(startDatum.getTime() + 3 * 24 * 60 * 60 * 1000));
            abfahrten[4] = new Abfahrt(5, 500, new Date(startDatum.getTime() + 4 * 24 * 60 * 60 * 1000));
            abfahrten[5] = new Abfahrt(6, 600, new Date(startDatum.getTime() + 5 * 24 * 60 * 60 * 1000));
        }

        public Abfahrt[] getAbfahrten() {
            return abfahrten;
        }

        public int compareDate(Date d1, Date d2) {
            if (d1.equals(d2)) {
                return 0;
            } else if (d1.before(d2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        Abfahrt[] naechsteAbfahrten = getNaechsteAbfahrt(101, 3);
        System.out.println(Arrays.toString(naechsteAbfahrten));
    }

    public static Abfahrt[] getNaechsteAbfahrt(int haltestellenId, int maxAbfahrten) {
        FahrplanService fahrplanService = new FahrplanService();
        Abfahrt[] alleAbfahrten = fahrplanService.getAbfahrten();

        if (alleAbfahrten == null || alleAbfahrten.length == 0) {
            return new Abfahrt[0];
        }

        Date now = new Date();
        int startIndex = -1;

        for (int i = 0; i < alleAbfahrten.length; i++) {
            if (alleAbfahrten[i].getAbfahrtsZeit() == null || alleAbfahrten[i].getHaltestellenId() == 0) {
                continue;
            }
            int diff = fahrplanService.compareDate(alleAbfahrten[i].getAbfahrtsZeit(), now);
            if (diff >= 0 && alleAbfahrten[i].getHaltestellenId() >= haltestellenId) {
                startIndex = i;
                break;
            }
        }

        if (startIndex == -1) {
            return new Abfahrt[0];
        }

        int endIndex = Math.min(startIndex + maxAbfahrten, alleAbfahrten.length);

        return Arrays.copyOfRange(alleAbfahrten, startIndex, endIndex);
    }
}
