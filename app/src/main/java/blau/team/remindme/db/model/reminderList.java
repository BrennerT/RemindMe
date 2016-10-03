package blau.team.remindme.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.R.id.primary;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderList extends RealmObject {
    @PrimaryKey
    public int list_id;
    public int interval;
    public boolean active;
    public String name;

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
