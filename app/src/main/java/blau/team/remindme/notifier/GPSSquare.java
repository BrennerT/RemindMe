package blau.team.remindme.notifier;

import android.location.Location;

/**
 * Created by Torben on 28.09.2016.
 * Getts the 4 GPS Corners and provides them for GPSLocator
 */
// TODO: Add funktionality of GPSSquare
public class GPSSquare {
    private Location corner1, corner2, corner3, corner4;

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
