package blau.team.remindme.db;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import blau.team.remindme.MainActivity;
import blau.team.remindme.db.model.GPSPoint;
import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.db.model.Termin;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.media.CamcorderProfile.get;
import static blau.team.remindme.R.id.sound;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    private Realm realm;

    public DBAdapter(){

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
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
        realm.beginTransaction();
        ReminderList listRealm = realm.copyToRealm(list);
        for (ReminderElement e: list.getElements()) {
            addElement(e);
        }
        addTermin(list.getTermins());
        String uuid = UUID.randomUUID().toString();
        listRealm.setListId(uuid);
        realm.commitTransaction();
        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy ReminderElement into Database and return UUID as String
     */
    public String addElement(ReminderElement element){
        realm.beginTransaction();
        ReminderElement realmElement = realm.copyToRealm(element);
        String uuid = UUID.randomUUID().toString();
        realmElement.setElementId(uuid);
        realm.commitTransaction();
        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Copy Termin into Dabase and returns UUID
     */
    public String addTermin(Termin termin){
        realm.beginTransaction();
        Termin realmTermin = realm.copyToRealm(termin);
        String uuid = UUID.randomUUID().toString();
        realmTermin.setTerminId(uuid);
        realm.commitTransaction();
        return uuid;
    }

    /*
     *  Created by Torben on 05.10.2016
     *  Creates a new GPS Point
     */
    public void addGPSPoint(GPSPoint point){
        realm.beginTransaction();
        GPSPoint realmPoint = realm.copyToRealm(point);
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
            Settings realmSetting = realm.copyToRealm(setting);
        }else{
            Settings realmSetting = realm.where(Settings.class).findFirst();
            realmSetting = setting;
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
