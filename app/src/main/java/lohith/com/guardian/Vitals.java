package lohith.com.guardian;

public class Vitals {
    String HeartBeat , Temperature;

    public Vitals(){}

    public Vitals(String heartBeat, String temperature) {
        HeartBeat = heartBeat;
        Temperature = temperature;
    }

    public String getHeartBeat() {
        return HeartBeat;
    }

    public String getTemperature() {
        return Temperature;
    }
}
