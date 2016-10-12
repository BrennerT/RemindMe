package blau.team.remindme.db.model;

import android.location.Location;

import blau.team.remindme.notifier.GPSSquare;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Settings extends RealmObject {
    private boolean vibration;
    private boolean sound;
    private RealmList<GPSPoint> corners;

    public Settings(boolean sound, boolean vibration, Location[] corners ) {
        this.sound = sound;
        RealmList<GPSPoint> realmCorners = new RealmList<>();
        for(int i=0;i<4;i++){
            GPSPoint corner = new GPSPoint(corners[i].getLatitude(), corners[i].getLongitude());
            realmCorners.add(corner);
        }
        this.corners = realmCorners;
        this.vibration = vibration;
    }

    public Settings(){

    }

    public boolean isVibration() {
        return vibration;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public RealmList<GPSPoint> getCorners() {
        return corners;
    }

    public void setCorners(RealmList<GPSPoint> corners) {
        this.corners = corners;
    }
}
