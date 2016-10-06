package blau.team.remindme.db;

/**
 * Created by Torben on 06.10.2016.
 */

public class ReminderListDBImage {
    private int interval;
    private boolean active;
    private String name;

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
