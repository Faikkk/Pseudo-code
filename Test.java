import java.util.*;

class Bluetooth_and_WIFI_Connection {

    public void sendNotification(String message) {
    }
}

class Buzzer {
    public Map<String, Double> buzz() {
        // return statement should be modified
        return null;

    }
}

class AirQualitySensor {
    public Map<String, Double> readData() {
        // return statement should be modified
        return null;
    }

}

public class Test {
    static final Map<String, Double> POLLUTANT_THRESHOLDS = new HashMap<String, Double>() {
        {
            put("CO", 9.0); // in ppm
            put("NO2", 0.053); // in ppm
            put("O3", 0.070); // in ppm
            put("PM2.5", 35.4);// in µg/m3
            put("PM10", 154.0);// in µg/m3
        }
    };

    static AirQualitySensor sensor = new AirQualitySensor();
    static Bluetooth_and_WIFI_Connection bluetooth = new Bluetooth_and_WIFI_Connection();

    public static Map<String, Double> analyzePollutants() {
        Map<String, Double> pollutantData = sensor.readData();
        return pollutantData;
    }

    public static List<String> checkThresholds(Map<String, Double> pollutantData) {
        List<String> pollutantsExceeded = new ArrayList<>();

        for (Map.Entry<String, Double> entry : pollutantData.entrySet()) {
            String pollutant = entry.getKey();
            Double amount = entry.getValue();
            if (amount > POLLUTANT_THRESHOLDS.get(pollutant)) {
                pollutantsExceeded.add(pollutant);
            }
        }

        return pollutantsExceeded;
    }

    public static void sendNotification(List<String> pollutantsExceeded) {
        if (pollutantsExceeded.size() > 0) {
            String message = "Warning! High levels of pollutants detected: " + String.join(", ", pollutantsExceeded);
            bluetooth.sendNotification(message);
        }
    }

    public static void main(String[] args) {
        while (true) {
            Map<String, Double> pollutantData = analyzePollutants();
            List<String> pollutantsExceeded = checkThresholds(pollutantData);
            sendNotification(pollutantsExceeded);
        }
    }
}
