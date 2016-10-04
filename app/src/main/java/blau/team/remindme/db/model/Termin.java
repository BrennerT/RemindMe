package blau.team.remindme.db.model;

import android.support.v7.widget.LinearLayoutCompat;

import java.sql.Time;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Termin extends RealmObject {
    @PrimaryKey
    private String termin_id;
    private Date date;
    private Time beginTime, endTime;

    public String getTermin_id() {
        return termin_id;
    }

    public void setTermin_id(String termin_id) {
        this.termin_id = termin_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
