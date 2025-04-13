package aufgabe3;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Random;

public class Main {

    public static String getMacAddress(String ip) {
        // Simuliert den Abruf der MAC durch IP
        Random random = new Random();
        if (random.nextDouble() > 0.7) {
            return null;
        }
        String[] macParts = new String[6];
        for (int i = 0; i < 6; i++) {
            macParts[i] = String.format("%02X", random.nextInt(256));
        }
        return String.join(":", macParts);
    }

    public static void main(String[] args) {
        JSONArray resultArray = new JSONArray();

        for (int i = 1; i <= 254; i++) {
            String ip = "192.168.0." + i;
            String mac = getMacAddress(ip);

            if (mac != null) {
                JSONObject device = new JSONObject();
                device.put("IP-Adresse", ip);
                device.put("MAC-Adresse", mac);
                resultArray.put(device);
            }
        }

        System.out.println(resultArray.toString(2));
    }
}