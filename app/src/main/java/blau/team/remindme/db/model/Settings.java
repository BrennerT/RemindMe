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
    private String settings_id;
    private boolean vibration;
    private boolean sound;
    private RealmList<GPSPoint> corners;

    public String getSettings_id() {
        return settings_id;
    }

    public void setSettings_id(String settings_id) {
        this.settings_id = settings_id;
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
