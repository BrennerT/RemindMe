package blau.team.remindme.db.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 * RealmObject for saving a List in Database.
 * Contains following information:
 *      interval:
 *      active:     is list in archive or not?
 *      name:       name of the list
 *      elements:   list of elements contained in the list
 *      termin:     contains a start and end date for the ReminderList
 */


public class ReminderList extends RealmObject {
    @PrimaryKey
    private long id;
    private int interval;
    private boolean active;
    private String name;
    // 1 to n relations to ReminderElement and Termin
    private RealmList<ReminderElement> elements;
    private Termin termin;

    /**
     * Constructor with parameters
     * @param interval  0 -> only Reminds between start and end date 7 -> reminds every 7 days
     * @param active    false -> list is in archive
     * @param name   name of the ReminderList
     * @param termin     Termin which contains start and end date of a List
     * @param elements    List of ReminderElements
     */
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

    /*
     * Constructor with non-parameters.
     * Needed by Realm
     */
    public ReminderList(){

    }

    // Getters and Setters Section

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
