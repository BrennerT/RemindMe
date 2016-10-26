package blau.team.remindme.db;

import android.util.Log;

import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Termin;
import io.realm.Realm;
import io.realm.annotations.RealmClass;

/**
 * Created by Torben on 24.10.2016.
 */

public class IDGenerator {

    private long count;

    /**
     * An Generator for ids for a given Class
     * @param realm current Realm Instance
     * @param classIdentifier   List -> the generator will search for ids in Reminderlist, Element, Termin
     */
    public IDGenerator(Realm realm, String classIdentifier){
        count = getHighestIdDb(realm, classIdentifier)+1;
    }

    private void countUp(){
        this.count += 1;
    }

    /**
     * Returns the next id of type long and counts 1 up for the next id
     * @return a new id of type long
     */
    public long getID(){
        long rCount = this.count;
        countUp();
        return rCount;
    }

    /**
     * finds Highest id in  the Database for a given Table.
     * @param realm current Realm Instance
     * @param classIdentifier List -> the generator will search for ids in Reminderlist, Element, Termin
     * @return the id
     */
    private long getHighestIdDb(Realm realm, String classIdentifier){
        switch(classIdentifier){
            case "List": {
                Number maxId = realm.where(ReminderList.class).max("id");
                if(maxId==null){
                    return 0;
                }else{
                    return maxId.longValue();
                }
            }
            case "Element" : {
                Number maxId = realm.where(ReminderElement.class).max("id");
                if(maxId==null){
                    return 0;
                }else{
                    return maxId.longValue();
                }
            }
            case "Termin" : {
                Number maxId = realm.where(Termin.class).max("id");
                if(maxId==null){
                    return 0;
                }else{
                    return maxId.longValue();
                }
            }
        }
        return 0;
    }

}
