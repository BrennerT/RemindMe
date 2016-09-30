package blau.team.remindme.db.model;

import java.sql.Time;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Torben on 30.09.2016.
 */

public class Termin extends RealmObject {
    public Date date;
    public Time beginTime, endTime;
}
