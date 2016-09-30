package blau.team.remindme.db.model;

import io.realm.RealmObject;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderList extends RealmObject {
    public int interval;
    public boolean active;
    public String name;

}
