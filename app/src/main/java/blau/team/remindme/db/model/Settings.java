package blau.team.remindme.db.model;

import android.location.Location;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Settings extends RealmObject {
    @PrimaryKey
    public int settings_id;
    public boolean vibration;
    public boolean sound;
    // Ist das an dieser Stelle so in Ordnung? Es sollten Maximal 4 Locations abgespeichert werden.
    public Location[] locations;

    public int getSettings_id() {
        return settings_id;
    }

    public void setSettings_id(int settings_id) {
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

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }
}
