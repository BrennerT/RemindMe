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

        //if Database structure changed this must be use to make database.
        //will delete Old Database and make a new Database

//      RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//       realm = Realm.getInstance(config);

        realm = Realm.getDefaultInstance();

        listIdGenerator = new IDGenerator(realm,"List");
        elementIdGenerator = new IDGenerator(realm,"Element");
        terminIdGenerator = new IDGenerator(realm,"Termin");
    }

    /**
     *  Created by Torben on 04.10.2016
     *  Searches for List with same id in Database and returns the first found list.
     *  @param id The id of the searched list
     */
    public ReminderList getListById(String id){
        RealmResults<ReminderList> result = realm.where(ReminderList.class).equalTo("listId", id).findAll();
        ReminderList resultList = result.get(0);
        return resultList;
    }

    /**
     *  Created by Torben on 04.10.2016
     *  Save ReminderList in Database
     *  @param list The list to save
     */
    public void addList(ReminderList list){
        // create ids for elements in list
        for (ReminderElement e: list.getElements()) {
            createIdElement(e);
        }
        // create id for termin in list
        createIdTermin(list.getTermins());
        // create id for list
        long id = listIdGenerator.getID();
        list.setId(id);
        realm.beginTransaction();
        // this also safes the termin and elements in database
        realm.copyToRealm(list);
        realm.commitTransaction();
    }

    /**
     *  Created by Torben on 04.10.2016
     *  creates an id for a given element
     *  @param element the element to give a new id
     */
    public long createIdElement(ReminderElement element){
        long id = elementIdGenerator.getID();
        element.setId(id);
        return id;
    }

    /**
     *  Created by Torben on 04.10.2016
     *  creates an id for a given termin object
     *  @param termin the termin to give a new id
     */
    public long createIdTermin(Termin termin){
        long id = terminIdGenerator.getID();
        termin.setId(id);
        return id;
    }

    /**
     *  Created by Torben on 04.10.2016
     *  Returns all Lists in Database
     */
    public List<ReminderList> getAllLists(){
        RealmResults<ReminderList> result = realm.where(ReminderList.class).findAll();
        return result;
    }

    /**
     *  Created by Torben on 04.10.2016
     *  Deletes the given list from database
     *  @param rl list to delete
     */
    public void deleteList(ReminderList rl){
        realm.beginTransaction();
        rl.deleteFromRealm();
        realm.commitTransaction();
    }


    /**
     *  Created by Torben on 04.10.2016
     *  Overrides the current Settings in Database
     *  @param setting The new settings object to copy in to database
     */
    public void changeSetting(Settings setting){
        realm.beginTransaction();
        if(realm.where(Settings.class).findAll().isEmpty()){
            // if there is no setting in database write this new on into it and give it an id
            setting.setId(1);
            realm.copyToRealm(setting);
        }else{
            // this overrides the object with the same id like setting
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
