package blau.team.remindme.db.model;

import io.realm.RealmObject;

/**
 * Created by Torben on 05.10.2016.
 */

public class GPSPoint extends RealmObject {
    private long latitude;
    private long longitude;

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
