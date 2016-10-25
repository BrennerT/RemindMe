package blau.team.remindme.notifier;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;

public class Notifier extends Service {

    private Time actualTime;
    private Location actualLocation;
    private Model model;
    private GPSLocator gps;

    public Notifier() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<ReminderList> getListsByDay(Date d){
        return null;
    }

    public void pushMessage(ReminderList r){

    }

    public void check(ReminderList r){

    }

    public Time getActualTime() {
        return actualTime;
    }

    public void setActualTime(Time actualTime) {
        this.actualTime = actualTime;
    }

    public Location getActualLocation() {
        return actualLocation;
    }

    public void setActualLocation(Location actualLocation) {
        this.actualLocation = actualLocation;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public GPSLocator getGps() {
        return gps;
    }

    public void setGps(GPSLocator gps) {
        this.gps = gps;
    }
}
