package blau.team.remindme.db.model;

import android.support.v7.widget.LinearLayoutCompat;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Termin extends RealmObject {
    @PrimaryKey
    private String termin_id;
    private Date beginDate, endDate;

    public String getTermin_id() {
        return termin_id;
    }

    public void setTermin_id(String termin_id) {
        this.termin_id = termin_id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
