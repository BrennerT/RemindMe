package blau.team.remindme.db.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Termin extends RealmObject {
    @PrimaryKey
    private String terminId;
    private Date beginDate, endDate;

    public Termin(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Termin(){

    }

    public String getTerminId() {
        return terminId;
    }

    public void setTerminId(String terminId) {
        this.terminId = terminId;
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
