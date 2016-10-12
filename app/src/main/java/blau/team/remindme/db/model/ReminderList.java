package blau.team.remindme.db.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderList extends RealmObject {
    @PrimaryKey
    private String listId;
    private int interval;
    private boolean active;
    private String name;
    // 1 to n relations to ReminderElement and Termin
    private RealmList<ReminderElement> elements;
    private Termin termin;

    public ReminderList(int interval, boolean active, String name, Termin termin, List<ReminderElement> elements) {
        this.interval = interval;
        this.active = active;
        this.name = name;
        this.termin = termin;
        RealmList<ReminderElement> realmElementList = new RealmList<>();
        for (ReminderElement e: elements) {
            realmElementList.add(e);
        }
        this.elements = realmElementList;
    }

    public ReminderList(){

    }

    public RealmList<ReminderElement> getElements() {
        return elements;
    }

    public void setElements(RealmList<ReminderElement> elements) {
        this.elements = elements;
    }

    public Termin getTermins() {
        return termin;
    }

    public void setTermins(Termin termin) {
        this.termin = termin;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
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
