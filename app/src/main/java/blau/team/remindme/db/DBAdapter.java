package blau.team.remindme.db;

import java.util.List;

import blau.team.remindme.db.model.GPSPoint;
import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.db.model.Termin;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.R.attr.id;
import static android.media.CamcorderProfile.get;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    private Realm realm;
    private IDGenerator listIdGenerator,elementIdGenerator,terminIdGenerator;

    public DBAdapter(){
//RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();

  //     realm = Realm.getInstance(config);

        realm = Realm.getDefaultInstance();

        listIdGenerator = new IDGenerator(realm,"List");
        elementIdGenerator = new IDGenerator(realm,"Element");
        terminIdGenerator = new IDGenerator(realm,"Termin");
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Searches for List with same id in Database and returns the first found list.
     */
    public ReminderList getListById(String id){
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("listId", id).findAll();
        ReminderList resultList = result.get(0);
        return resultList;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy ReminderList into Database and return UUID as String
     */
    public long addList(ReminderList list){
        for (ReminderElement e: list.getElements()) {
            createIdElement(e);
        }
        createIdTermin(list.getTermins());
        long id = listIdGenerator.getID();
        list.setId(id);
        realm.beginTransaction();
        realm.copyToRealm(list);
        realm.commitTransaction();
        return id;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy ReminderElement into Database and return UUID as String
     */
    public long createIdElement(ReminderElement element){
        long id = elementIdGenerator.getID();
        element.setId(id);
        return id;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  creates an id for a termin object
     */
    public long createIdTermin(Termin termin){
        long id = terminIdGenerator.getID();
        termin.setId(id);
        return id;
    }

    /*
     *  Created by Torben on 05.10.2016
     *  Creates a new GPS Point
     */
    public void addGPSPoint(GPSPoint point){
        realm.beginTransaction();
        realm.copyToRealm(point);
        realm.commitTransaction();
    }


    /*
     *  Created by Torben on 04.10.2016
     *  Returns all Lists in Database
     */
    public List<ReminderList> getAllLists(){
        RealmResults<ReminderList> result = realm.where(ReminderList.class).findAll();
        return result;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Deletes the first List found for one ID
     */
    public void deleteList(long id){
        realm.beginTransaction();
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("id",id).findAll();
        result.deleteFromRealm(0);
        realm.commitTransaction();
    }


    /*
     *  Created by Torben on 04.10.2016
     *  Overrides the current Settings in Database
     */
    public void changeSetting(Settings setting){
        realm.beginTransaction();
        if(realm.where(Settings.class).findAll().isEmpty()){
            realm.copyToRealm(setting);
        }else{
            realm.copyToRealmOrUpdate(setting);
        }
        realm.commitTransaction();
    }

    /*
     * Created by Torben on 11.10.2016
     * Gets the Settings from Database
     */
    public Settings getSettings(){
        return realm.where(Settings.class).findFirst();
    }

    }
