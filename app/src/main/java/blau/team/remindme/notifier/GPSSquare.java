package blau.team.remindme.notifier;

import android.location.Location;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.GPSPoint;
import blau.team.remindme.db.model.Settings;

/**
 * Created by Torben on 28.09.2016.
 * Getts the 4 GPS Corners and provides them for GPSLocator
 */
public class GPSSquare {
    private Location corner1, corner2, corner3, corner4;

    /**
     * Constructor
     * Loads current Setting from Model
     */
    public GPSSquare(){
        // Load current Setting
        Settings currentSetting = Model.getInstance().getSettings();

        // Save the corners from Settings
        //TODO find better solution
        corner1 = new Location("gps");
        corner1.setLatitude(currentSetting.getCorners().get(0).getLatitude());
        corner1.setLongitude(currentSetting.getCorners().get(0).getLongitude());

        corner2 = new Location("gps");
        corner2.setLatitude(currentSetting.getCorners().get(1).getLatitude());
        corner2.setLongitude(currentSetting.getCorners().get(1).getLongitude());

        corner3 = new Location("gps");
        corner3.setLatitude(currentSetting.getCorners().get(2).getLatitude());
        corner3.setLongitude(currentSetting.getCorners().get(2).getLongitude());

        corner4 = new Location("gps");
        corner4.setLatitude(currentSetting.getCorners().get(3).getLatitude());
        corner4.setLongitude(currentSetting.getCorners().get(3).getLongitude());
    }

    public Location getCorner1() {
        return corner1;
    }

    public void setCorner1(Location corner1) {
        this.corner1 = corner1;
    }

    public Location getCorner2() {
        return corner2;
    }

    public void setCorner2(Location corner2) {
        this.corner2 = corner2;
    }

    public Location getCorner3() {
        return corner3;
    }

    public void setCorner3(Location corner3) {
        this.corner3 = corner3;
    }

    public Location getCorner4() {
        return corner4;
    }

    public void setCorner4(Location corner4) {
        this.corner4 = corner4;
    }
}
