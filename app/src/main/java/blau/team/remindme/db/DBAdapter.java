package blau.team.remindme.db;

import java.util.List;

import blau.team.remindme.db.model.ReminderList;
import io.realm.Realm;
import io.realm.RealmResults;

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
     *  Adds new List to database
     */
    public void addList(ReminderList r){

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

    }

    public List<String> getSettings(){
        return null;
    }

    public void setSettings(){

    }
}
