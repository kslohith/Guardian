package lohith.com.guardian;

public class locationdetails {
   public double lat , lng;

    public locationdetails(){}

    public locationdetails(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
