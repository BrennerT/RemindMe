package blau.team.remindme.notifier;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import blau.team.remindme.MainActivity;
import blau.team.remindme.db.model.ReminderList;

/**
 * Created by Torben on 28.09.2016.
 */

public class GPSLocator extends Service implements LocationListener {
    private GPSSquare actual;
    private LocationManager locationManager;
    private LocationProvider locationProvider;
    private Location currentLocation;
    private static Context mContext;

    public GPSLocator() {
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        try {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationProvider = locationManager.getProvider("gps");
            locationManager.requestLocationUpdates("gps",
                    15000, // 1min
                    1,   // 10m
                    this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        currentLocation = new Location("gps");

    }

    public void setCornerPoints() {

    }

    public boolean isOutOfSquare(ReminderList r) {
        // One              Two
        // Three            Four

        Double cornerOneLatitude = this.getActual().getCorner1().getLatitude();
        Double cornerTwoLatitude = this.getActual().getCorner2().getLatitude();
        Double cornerThreeLatitude = this.getActual().getCorner3().getLatitude();
        Double cornerFourLatitude = this.getActual().getCorner4().getLatitude();

        Double cornerOneLongitude = this.getActual().getCorner1().getLongitude();
        Double cornerTwoLongitude = this.getActual().getCorner2().getLongitude();
        Double cornerThreeLongitude = this.getActual().getCorner3().getLongitude();
        Double cornerFourLongitude = this.getActual().getCorner4().getLongitude();

        Double actualLatitude = this.currentLocation.getLatitude();
        Double actuaLongitude = this.currentLocation.getLongitude();

        // check if the person is lower then the top line
        Boolean threeToFour = (cornerFourLatitude + (cornerFourLatitude - cornerThreeLatitude) /
                (cornerFourLongitude - cornerThreeLongitude) * actuaLongitude)
                <= actualLatitude;

        // check if the person is higher then the bottom line
        Boolean oneToTwo = (cornerOneLatitude + (cornerOneLatitude - cornerTwoLatitude) /
                (cornerOneLongitude - cornerTwoLongitude) * actuaLongitude)
                >= actualLatitude;

        // check if the person are right from the left line
        Boolean oneToThree = (cornerOneLongitude + (cornerOneLongitude - cornerThreeLongitude) /
                (cornerOneLatitude - cornerThreeLatitude) * actualLatitude)
                <= actuaLongitude;

        // check if the person are left from the right line
        Boolean twoToFour = (cornerTwoLongitude + (cornerTwoLongitude - cornerFourLongitude) /
                (cornerTwoLatitude - cornerFourLatitude) * actualLatitude)
                >= actuaLongitude;

        if (!threeToFour || !oneToTwo || !oneToThree || !twoToFour) {
            // if leave
            return true;
        }
        // if not left
        return false;
    }

    public Location getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            // should never run here!
            return null;
        }
        currentLocation = locationManager.getLastKnownLocation("gps");
        return currentLocation;
    }

    public GPSSquare getActual() {
        return actual;
    }

    public void setActual(GPSSquare actual) {
        this.actual = actual;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO search what to do here
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation.setLongitude(location.getLongitude());
        currentLocation.setLatitude(location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO search what to do here
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO search what to do here
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO search what to do here
    }

    // Getters and Setters Section
    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context context) {
        mContext = context;
    }
}
