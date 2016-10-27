package blau.team.remindme.notifier;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import blau.team.remindme.MainActivity;
import blau.team.remindme.R;
import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;
import io.realm.Realm;

import static android.R.attr.start;

/**
 * This Service is responsible for pushing Notifications when the user leafs the setted GPSSquare
 */
public class Notifier extends IntentService {

    private static Notifier instance;

    private Model model;

    private Date actualTime;
    private Location actualLocation;

    private GPSLocator gps;

    /**
     * Constructor
     * Will create a new GPSLocator
     */
    public Notifier() {
        super("NotifierRemindMe");
        gps = new GPSLocator();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<ReminderList> todayLists = getListsByDay(getActualTime());
        try {
            while(true) {
                for (ReminderList l : todayLists) {
                    check(l);
                }
                Thread.sleep(100);
            }
        }catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Searches all Lists for the Current Day
     * @param d should be current Data
     * @return list of ReminderList
     */
    public List<ReminderList> getListsByDay(Date d){
        Realm realm = Realm.getDefaultInstance();
        List<ReminderList> lists = realm.where(ReminderList.class).findAll();
        List<ReminderList> returnLists = new ArrayList<>();
        for (ReminderList l: lists) {
            if(l.getTermins().getBeginDate().getTime()<= d.getTime()){
                if(l.getTermins().getEndDate().getTime()>=d.getTime()){
                    returnLists.add(l);
                }
            }
        }

//        List<ReminderList> reminderLists = getLists();
//        List<ReminderList> returnLists = new ArrayList<>();
//        for (ReminderList l: reminderLists) {
//            if(l.getTermins().getBeginDate().compareTo(d)==0){
//                returnLists.add(l);
//            }
//        }
        return returnLists;
    }

    /**
     * Pushes Notification for the User
     * @param r List to push
     */
    public void pushMessage(ReminderList r){
//        getMainLooper().
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle("RemindMe" + r.getName())
//                        .setContentText(r.getElements().toString());
//        Notification n = mBuilder.build();
//        n.notify();

        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("GCM Notification")
                        .setContentText(r.getName());
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(1, mBuilder.build());
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

//        this.getActualTime();

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

            if(gps.isOutOfSquare(r)){
                pushMessage(r);
            }
            // only gps
    }

    // Getter and Setter section

    public Date getActualTime() {
        actualTime = Calendar.getInstance(Locale.GERMANY).getTime();
        return actualTime;
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
