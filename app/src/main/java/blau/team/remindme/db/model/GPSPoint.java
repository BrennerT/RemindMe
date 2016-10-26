package blau.team.remindme.db.model;

import io.realm.RealmObject;

/**
 * Created by Torben on 05.10.2016.
 * RealmObject for GPSPoint. Used to safe the 4 Points from the Settings.
 * Contains information about latitude and longitude
 */

public class GPSPoint extends RealmObject {
    private double latitude;
    private double longitude;

    /**
     * Constructor with Parameters
     * @param latitude
     * @param longitude
     */
    public GPSPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor with non-parameters
     * Needed by Realm
     */
    public GPSPoint(){

    }

    // Getters and Setters section

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
