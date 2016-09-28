package blau.team.remindme.notifier;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import java.sql.Time;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Notifier extends Service {

    private Time actualTime;
    private Location actualLocation;
//    private Model model;
    private GPSLocator gps;

    public Notifier() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    public List<ReminderList> getListsByDay(Date d){
//        return null;
//    }

//    public void pushMessage(ReminderList r){
//
//    }

//    public void check(ReminderList r){
//
//    }

}
