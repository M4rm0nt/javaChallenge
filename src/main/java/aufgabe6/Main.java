package aufgabe6;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GeoPos[] positions = {
                new GeoPos("München",48.137154, 11.576124, 500),  // München
                new GeoPos("Berlin",52.520008, 13.404954, 50),    // Berlin
                new GeoPos("Köln",50.937531, 6.960279, 60),     // Köln
                new GeoPos("hamburg",53.551086, 9.993682, 20),     // Hamburg
                new GeoPos("Düsseldorf",51.227741, 6.773456, 45)      // Düsseldorf
        };

        GeoPos[] optimizedRoute = calculateFlight(positions);
}

    public static GeoPos[] calculateFlight(GeoPos[] geoPositions) {
        if (geoPositions == null || geoPositions.length == 0) {
            return new GeoPos[0];
        }

        GeoPos[] result = new GeoPos[geoPositions.length];
        List<GeoPos> remainingPositions = new LinkedList<>(List.of(geoPositions));

        GeoPos currentPos = remainingPositions.removeFirst();
        result[0] = currentPos;

        int index = 1;

        while (!remainingPositions.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            int nearestIndex = 0;

            for (int i = 0; i < remainingPositions.size(); i++) {
                double distance = GeoCalculator.getDistance(currentPos, remainingPositions.get(i));
                double distanceKm = distance / 1000;

                System.out.printf("Entfernung zwischen %s und %s: %.2f km%n", currentPos.getName(), remainingPositions.get(i).getName(), distanceKm);

                if (distance < minDistance) {
                    nearestIndex = i;
                    minDistance = distance;
                }

            }

            currentPos = remainingPositions.remove(nearestIndex);
            result[index++] = currentPos;

            System.out.println("\n");
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

final class GeoPos {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final double altitude;

    public GeoPos(String name, double latitude, double longitude, double altitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public String getName() {
        return name;
    }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getAltitude() { return altitude; }
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