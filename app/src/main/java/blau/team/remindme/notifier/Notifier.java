package blau.team.remindme.notifier;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import blau.team.remindme.R;
import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;

/**
 * This Service is responsible for pushing Notifications when the user leafs the setted GPSSquare
 */
public class Notifier extends Service {

    private static Notifier instance;

    private Date actualTime;
    private Location actualLocation;
    private Model model;
    private GPSLocator gps;

    /**
     * Constructor
     * Will create a new GPSLocator
     */
    public Notifier() {
        gps = new GPSLocator();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Searches all Lists for the Current Day
     * @param d should be current Data
     * @return list of ReminderList
     */
    public List<ReminderList> getListsByDay(Date d){
        return null;
    }

    /**
     * Pushes Notification for the User
     * @param r List to push
     */
    public void pushMessage(ReminderList r){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("RemindMe" + r.getName())
                        .setContentText(r.getElements().toString());
    }

    // TODO: Implement functionality for notifier to work in Background

    /**
     * Checks if a Notification should be pushed and starts the push process
     * @param r a List which should be checked
     */
    public void check(ReminderList r) {

        // get the data
        Date begin = r.getTermins().getBeginDate();
        Date end = r.getTermins().getEndDate();

        Date now = new Date();
        this.setActualTime(now);

        // TODO better check for Time currently deactivated
//        Date intervall = new Date(r.getInterval());
//
//        // if end and begin not null
//        if (end != null && begin != null) {
//            // check if between start and end date, also for future dates which get determined by intervall
//                if((this.getActualTime().getTime() % intervall >= begin.getTime() % intervall
//                        && this.getActualTime().getTime() % intervall <= end.getTime() % intervall
//                        && this.getActualTime().getTime() >= begin.getTime()){
//                    if(gps.isOutOfSquare(r)){
//                        pushMessage(r);
//                    }
//                // % because of the intervall and the last check because we have to look if the
//                // list ist active
//            }
//            // gps with time
//        }

//        if (end == null && begin != null) {
//            if (this.getActualTime() >= begin * 0.95 && this.getActualTime() <= begin * 1.05 ) {
//                pushMessage(r);
//                // 0.95 and 1.05 because we dont go in this function every second
//            }
//            // only time
//        }

        if (end == null && begin == null) {
            if(gps.isOutOfSquare(r)){
                pushMessage(r);
            }
            // only gps
        }
    }

    // Getter and Setter section

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    /**
     * Loads the current location from GPSLocator
     * @return the current Location
     */
    public Location getActualLocation() {
        actualLocation = gps.getCurrentLocation();
        return actualLocation;
    }

    public static Notifier getInstance() {
        if(instance == null){
            instance = new Notifier();
        }
        return instance;
    }

}
