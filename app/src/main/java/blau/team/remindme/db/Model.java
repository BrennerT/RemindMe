package blau.team.remindme.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.db.model.Termin;
import io.realm.RealmList;

import static android.R.id.list;

/**
 * Created by Torben on 28.09.2016.
 * Edited on 04.10.2016 by Torben
 * changes: - now uses Singleton Pattern
 *          - implemented reload method
 *
 *  TODO: - Setter methods should update the database
 */

public class Model {
    private static Model instance;
    private DBAdapter dbAd;
    private Settings settings;
    private List<ReminderList> lists;

    public static Model getInstance(){
        if(Model.instance == null){
            instance =  new Model();
            instance.dbAd = new DBAdapter();
        }
        return Model.instance;
    }

    public void reload(){
        lists = dbAd.getAllLists();
        settings = dbAd.getSettings();
    }

    /**
     * Gets the current Settings from the database
     * @return
     */
    public Settings getSettings() {
        return dbAd.getSettings();
    }

    /**
     * Updates Settings in Database
     * @param setting
     */
    public void setSettings(Settings setting) {
        dbAd.changeSetting(setting);
    }

    public void addList (int interval, Date beginDate, Date endDate, String name, List<String> elements){
        // Update of the model
        List<ReminderElement> elementList = new ArrayList<>();
        for (String e: elements)
        {
            elementList.add(new ReminderElement(e));
        }
        ReminderList list = new ReminderList(interval, true, name, new Termin(beginDate,endDate), elementList);
        dbAd.addList(list);
    }

    public void addList(ReminderList rl){
        dbAd.addList(rl);
        this.reload();
    }

    // TODO: implement Method deleteList
    public void deleteList(ReminderList rl){
        //Method to delete
    }

    public List<ReminderList> getLists() {
        return dbAd.getAllLists();
    }

    public void setLists(List<ReminderList> lists) {
        this.lists = lists;
    }

}
