package blau.team.remindme.db;

import java.util.List;

import blau.team.remindme.db.model.ReminderList;

/**
 * Created by Torben on 28.09.2016.
 * Edited on 04.10.2016 by Torben
 * changes: - now uses Singleton Pattern
 *          - implemented reload method
 */

public class Model {
    private static Model instance;
    private DBAdapter dbAd;
    private List<ReminderList> lists;

    public void reload(){
        dbAd = new DBAdapter();
        lists = dbAd.getAllLists();
    }

    public Model getInstance(){
        if(Model.instance == null){
            instance =  new Model();
        }
        return Model.instance;
    }

}
