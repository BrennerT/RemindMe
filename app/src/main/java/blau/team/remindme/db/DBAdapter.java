package blau.team.remindme.db;

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
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.media.CamcorderProfile.get;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    private Realm realm;

    public DBAdapter(){
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
     *  Takes the Data of the parameters and creates an entry in Table ReminderList
     */
    public String addList(ReminderList list, RealmList<ReminderElement> elements, RealmList<Termin> termins){
        realm.beginTransaction();
        ReminderList listRealm = realm.createObject(ReminderList.class);
        String uuid = UUID.randomUUID().toString();
        list.setListId(uuid);
        listRealm.setName(list.getName());
        listRealm.setInterval(list.getInterval());
        listRealm.setActive(list.isActive());
        listRealm.setElements(elements);
        listRealm.setTermins(termins);
        realm.commitTransaction();
        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Creates a new entry in Table ReminderElement
     */
    public String addElement(String name){
        realm.beginTransaction();
        ReminderElement element = realm.createObject(ReminderElement.class);
        String uuid = UUID.randomUUID().toString();
        element.setElementId(uuid);
        element.setName(name);
        realm.commitTransaction();
        return uuid;
    }

    /*
     *  Created by Torben on 04.10.2016
     *  Creates a new entry in table Termin
     */
    public void addTermin(Date beginDate, Date endDate){
        realm.beginTransaction();
        Termin termin = realm.createObject(Termin.class);
        termin.setTerminId(UUID.randomUUID().toString());
        termin.setBeginDate(beginDate);
        termin.setEndDate(endDate);
        realm.commitTransaction();
    }

    /*
     *  Created by Torben on 05.10.2016
     *  Creates a new GPS Point
     */
    public void addGPSPoint(long latitude, long longitude){
        realm.beginTransaction();
        GPSPoint point = realm.createObject(GPSPoint.class);
        point.setLatitude(latitude);
        point.setLongitude(longitude);
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


    /*
     *  Created by Torben on 04.10.2016
     *  Overrides the current Settings in Database
     */
    public void changeSetting(boolean sound, boolean vibration, RealmList<GPSPoint> corners){
        realm.beginTransaction();
        if(realm.where(Settings.class).findFirst().equals(null)){
            Settings setting = realm.createObject(Settings.class);
            setting.setSound(sound);
            setting.setVibration(vibration);
            setting.setCorners(corners);
        }else{
            Settings setting = realm.where(Settings.class).findFirst();
            setting.setSound(sound);
            setting.setVibration(vibration);
            setting.setCorners(corners);
        }
        realm.commitTransaction();
    }

    /*
     * Created by Torben on 11.10.2016
     * Gets the Settings from Database
     */
    public Settings getSettings(){
        Settings result = realm.where(Settings.class).findFirst();
        return result;
    }

    }
