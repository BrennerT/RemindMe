package blau.team.remindme.db;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Torben on 28.09.2016.
 */

public class ReminderList {

    private Boolean mode, active;
    private String name;
    private List<String> elements;
    private Date date;
    private Time time;
    private int interval, id;

}
