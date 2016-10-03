package blau.team.remindme.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.R.attr.name;
import static android.R.id.primary;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderList extends RealmObject {
    @PrimaryKey
    private int list_id;
    private int interval;
    private boolean active;
    private String name;
    // 1 to n relations to ReminderElement and Termin
    private RealmList<ReminderElement> elements;
    private RealmList<Termin> termins;
    // 1 to 1 relation to settings
    private Settings setting;

    public RealmList<ReminderElement> getElements() {
        return elements;
    }

    public void setElements(RealmList<ReminderElement> elements) {
        this.elements = elements;
    }

    public RealmList<Termin> getTermins() {
        return termins;
    }

    public void setTermins(RealmList<Termin> termins) {
        this.termins = termins;
    }

    public Settings getSetting() {
        return setting;
    }

    public void setSetting(Settings setting) {
        this.setting = setting;
    }

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
