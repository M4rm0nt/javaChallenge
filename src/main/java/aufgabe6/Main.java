package aufgabe6;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GeoPos[] positions = {
                new GeoPos(48.137154, 11.576124, 500),  // München
                new GeoPos(52.520008, 13.404954, 50),    // Berlin
                new GeoPos(50.937531, 6.960279, 60),     // Köln
                new GeoPos(53.551086, 9.993682, 20),     // Hamburg
                new GeoPos(51.227741, 6.773456, 45)      // Düsseldorf
        };

        System.out.println("Ursprüngliche Route:");
        printPositions(positions);

        GeoPos[] optimizedRoute = calculateFlight(positions);

        System.out.println("\nOptimierte Flugroute:");
        printPositions(optimizedRoute);
    }

    public static GeoPos[] calculateFlight(GeoPos[] geoPositions) {
        if (geoPositions == null || geoPositions.length == 0) {
            return new GeoPos[0];
        }

        GeoPos[] result = new GeoPos[geoPositions.length];

        List<GeoPos> remainingPositions = new ArrayList<>(List.of(geoPositions));

        GeoPos currentPos = remainingPositions.getFirst();
        result[0] = currentPos;
        remainingPositions.removeFirst();

        int index = 1;

        while (!remainingPositions.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            GeoPos nearestPos = null;
            int nearestIndex = -1;

            for (int i = 0; i < remainingPositions.size(); i++) {
                GeoPos pos = remainingPositions.get(i);
                double distance = GeoCalculator.getDistance(currentPos, pos);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPos = pos;
                    nearestIndex = i;
                }
            }

            if (nearestPos != null) {
                result[index++] = nearestPos;
                currentPos = nearestPos;
                remainingPositions.remove(nearestIndex);
            }
        }

        return result;
    }

    private static void printPositions(GeoPos[] positions) {
        for (GeoPos pos : positions) {
            System.out.printf("Lat: %.6f, Lon: %.6f, Alt: %.2fm%n",
                    pos.getLatitude(), pos.getLongitude(), pos.getAltitude());
        }
    }
}

class GeoPos {
    private double latitude;
    private double longitude;
    private double altitude;

    public GeoPos(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }
}

class GeoCalculator {
    public static double getDistance(GeoPos pos1, GeoPos pos2) {
        final int R = 6371;

        double latDistance = Math.toRadians(pos2.getLatitude() - pos1.getLatitude());
        double lonDistance = Math.toRadians(pos2.getLongitude() - pos1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(pos1.getLatitude())) * Math.cos(Math.toRadians(pos2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = pos1.getAltitude() - pos2.getAltitude();
        distance = Math.sqrt(Math.pow(distance, 2) + Math.pow(height, 2));

        return distance;
    }
}