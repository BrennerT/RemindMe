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
import blau.team.remindme.db.model.ReminderElement;
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
                getActualLocation();
                getListsByDay(getActualTime());
                for (ReminderList l : todayLists) {
                    check(l);
                }
                Thread.sleep(2000);
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
                    if(l.isActive()) {
                        returnLists.add(l);
                    }
                }
            }
        }
        return returnLists;
    }

    /**
     * Pushes Notification for the User
     * @param r List to push
     */
    public void pushMessage(ReminderList r){
        if(!r.isNotificationFlag()) {
            NotificationManager mNotificationManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);

            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, MainActivity.class), 0);
            String msg = "";

            for (ReminderElement e : r.getElements()) {
                msg += e.getName() + "\n";
            }

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("RemindMe: " + r.getName())
                            .setContentText(msg);
            mBuilder.setContentIntent(contentIntent);
            mNotificationManager.notify(1, mBuilder.build());

            // Write to Realm
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            r.setNotificationFlag(true);
            realm.commitTransaction();

        }
    }

    /**
     * Checks if a Notification should be pushed and starts the push process
     * @param r a List which should be checked
     */
    public void check(ReminderList r) {
        if(gps.isOutOfSquare(r)){
            pushMessage(r);
        }

    }

    // Getter and Setter section

    /**
     * Gets the current Time von calendar instance
     * @return the current time
     */
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
