package blau.team.remindme.db;

import java.util.List;

import blau.team.remindme.db.model.ReminderListTable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Torben on 28.09.2016.
 */

public class DBAdapter {

    Realm realm = Realm.getDefaultInstance();

    public ReminderList getListById(int id){
        RealmResults<ReminderListTable> result = realm.where(ReminderListTable.class).equalTo("list_id", id).findAll();
        ReminderList rml = new ReminderList();
        rml.setId(result.get(0).getList_id());
        return null;
    }

    public void addList(ReminderList r){

    }

    public List<ReminderList> getAllLists(){
        RealmResults<ReminderListTable> result = realm.where(ReminderListTable.class).findAll();
        return null;
    }

    public void deleteList(int id){

    }

    public List<String> getSettings(){
        return null;
    }

    public void setSettings(){

    }
}
