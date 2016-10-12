package blau.team.remindme.db;

import java.util.Date;
import java.util.List;

import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.db.model.Settings;

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

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void addList (String time, Date date, List<String> elements){
        // Update of the model

        //Write to database
        //dbAd.addList( ... );
    }

    public void addList(ReminderList rl){
        //Alternative addList method
    }

    public void deleteList(ReminderList rl){
        //Method to delete ReminderLists
    }
    public List<ReminderList> getLists() {
        return dbAd.getAllLists();
    }

    public void setLists(List<ReminderList> lists) {
        this.lists = lists;
    }
    public void reload(){
        dbAd = new DBAdapter();
        lists = dbAd.getAllLists();
    }

    public static Model getInstance(){
        if(Model.instance == null){
            instance =  new Model();
        }
        return Model.instance;
    }

}
