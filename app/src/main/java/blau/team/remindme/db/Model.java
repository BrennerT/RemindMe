package blau.team.remindme.db;

import java.util.List;

/**
 * Created by Torben on 28.09.2016.
 */

public class Model {
    private DBAdapter dbAd;
    private List<ReminderList> list;
    private List<String> settings;

    public void reload(){

    }

    public DBAdapter getDbAd() {
        return dbAd;
    }

    public void setDbAd(DBAdapter dbAd) {
        this.dbAd = dbAd;
    }

    public List<ReminderList> getList() {
        return list;
    }

    public void setList(List<ReminderList> list) {
        this.list = list;
    }

    public List<String> getSettings() {
        return settings;
    }

    public void setSettings(List<String> settings) {
        this.settings = settings;
    };

}
