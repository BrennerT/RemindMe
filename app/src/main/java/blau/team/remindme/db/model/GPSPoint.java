package blau.team.remindme.db.model;

import io.realm.RealmObject;

/**
 * Created by Torben on 05.10.2016.
 */

public class GPSPoint extends RealmObject {
    private double latitude;
    private double longitude;

    public GPSPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPSPoint(){

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
