package blau.team.remindme.db.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

import static android.R.attr.elegantTextHeight;
import static android.R.attr.fragmentSharedElementEnterTransition;
import static android.R.attr.id;

/**
 * Created by Torben on 30.09.2016.
 */

public class ReminderElement extends RealmObject {
    @PrimaryKey
    private String elementId;
    private String name;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String element_id) {
        this.elementId = element_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
