package blau.team.remindme.db.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Torben on 30.09.2016.
 */

public class Termin extends RealmObject {
    @PrimaryKey
    private long id;
    private Date beginDate, endDate;

    /**
     *  Constructor with parameters
     * @param beginDate Start time to check if user leaves the area
     * @param endDate End time to check if user leaves the area
     */
    public Termin(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Constructor with no parameters
     * Needed by Realm
     */
    public Termin(){

    }

    // Getters and Setters section

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
