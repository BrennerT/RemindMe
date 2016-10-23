package blau.team.remindme.db;

import java.util.List;
import java.util.UUID;

import blau.team.remindme.db.model.GPSPoint;
import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.db.model.Termin;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.media.CamcorderProfile.get;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    private Realm realm;

    public DBAdapter(){
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();

//        realm = Realm.getInstance(config);

        realm = Realm.getDefaultInstance();
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
    public String addList(ReminderList list){
        for (ReminderElement e: list.getElements()) {
            createIdElement(e);
        }
        createIdTermin(list.getTermins());
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        }while(realm.where(ReminderList.class).equalTo("listId", uuid).count()!=0);
        list.setListId(uuid);
        realm.beginTransaction();
        realm.copyToRealm(list);
        realm.commitTransaction();

        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy ReminderElement into Database and return UUID as String
     */
    public String createIdElement(ReminderElement element){
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        }while(realm.where(ReminderElement.class).equalTo("elementId", uuid).count()!=0);
        element.setElementId(uuid);
        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy Termin into Dabase and returns UUID
     */
    public String createIdTermin(Termin termin){
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        }while(realm.where(Termin.class).equalTo("terminId", uuid).count()!=0);
        termin.setTerminId(uuid);
        return uuid;
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
    public void deleteList(String id){
        realm.beginTransaction();
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("listId",id).findAll();
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
