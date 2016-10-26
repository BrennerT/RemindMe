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
    @PrimaryKey
    private int id;
    private boolean vibration;
    private boolean sound;
    private RealmList<GPSPoint> corners;

    /**
     * Constructor with parameters.
     * @param sound     true -> sound turned on     false -> sound turned off
     * @param vibration  true -> vibration is on     false -> vibration is off
     * @param corners   contains 4 GPSPoints which are the standard positions to check if user left
     */
    public Settings(boolean sound, boolean vibration, RealmList<GPSPoint> corners ) {
        this.sound = sound;
        RealmList<GPSPoint> realmCorners = corners;
        this.corners = realmCorners;
        this.vibration = vibration;
    }

    /**
     * Constructor with no parameters
     * Needed by Realm
     */
    public Settings(){

    }

    // Getters and Setters section

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
