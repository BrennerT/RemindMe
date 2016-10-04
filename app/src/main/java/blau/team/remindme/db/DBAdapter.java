package blau.team.remindme.db;

import android.location.Location;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import blau.team.remindme.SettingActivity;
import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.db.model.Termin;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.R.attr.end;
import static android.R.attr.id;
import static android.media.CamcorderProfile.get;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    private Realm realm = Realm.getDefaultInstance();

    /*
     *  Created by Torben on 04.10.2016
     *  Searches for List with same id in Database and returns the first found list.
     */
    public ReminderList getListById(String id){
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("list_id", id).findAll();
        ReminderList resultList = result.get(0);
        return resultList;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Takes the Data of the parameters and creates an entry in Table ReminderList
     */
    public void addList(int interval, boolean active, String name, RealmList<ReminderElement> elements, RealmList<Termin> termins, Settings setting){
        realm.beginTransaction();
        ReminderList list = realm.createObject(ReminderList.class);
        list.setList_id(UUID.randomUUID().toString());
        list.setName(name);
        list.setActive(active);
        list.setInterval(interval);
        list.setElements(elements);
        list.setTermins(termins);
        list.setSetting(setting);
        realm.commitTransaction();
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Creates a new entry in Table ReminderElement
     */
    public void addElement(String name){
        realm.beginTransaction();
        ReminderElement element = realm.createObject(ReminderElement.class);
        element.setElement_Id(UUID.randomUUID().toString());
        element.setName(name);
        realm.commitTransaction();
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Creates a new entry in table Settings
     */
    public void addSetting(boolean sound, boolean vibration, Location[] locations){
        realm.beginTransaction();
        Settings setting = realm.createObject(Settings.class);
        setting.setSettings_id(UUID.randomUUID().toString());
        setting.setSound(sound);
        setting.setVibration(vibration);
        setting.setLocations(locations);
        realm.commitTransaction();
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Creates a new entry in table Termin
     */
    public void addTermin(Date beginDate, Date endDate){
        realm.beginTransaction();
        Termin termin = realm.createObject(Termin.class);
        termin.setTermin_id(UUID.randomUUID().toString());
        termin.setBeginDate(beginDate);
        termin.setEndDate(endDate);
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
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("list_id",id).findAll();
        result.deleteFromRealm(0);
        realm.commitTransaction();
    }


    // Diese sollten mit den aktuellen Methoden nicht notwendig sein
//    public List<String> getSettings(){
//        return null;
//    }
//
//    public void setSettings(){

    }
