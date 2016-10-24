package blau.team.remindme.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderElement extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;

    public ReminderElement(String name) {
        this.name = name;
    }

    public ReminderElement(){

    }

    public long getId() {
        return id;
    }

    public void setId(long element_id) {
        this.id = element_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
