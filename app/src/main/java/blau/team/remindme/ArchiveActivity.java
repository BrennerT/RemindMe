package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;

/**
 * This Activity is the Controller for activity_archive.xml
 * The Activity is shows the last 30 InActive Lists
 */
public class ArchiveActivity extends AppCompatActivity {

    private List<Button> restoreButtons;
    private Button backButton;
    private Model model;
    private TableLayout inactive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        // Get Current Database
        model = Model.getInstance();
    }


    public View.OnClickListener restoreButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO find currently selected list and the restore it
        }
    };

    /**
     * This Method should reactivate a inactive list
     * @param rl the list to be restored
     */
    public void restoreList (ReminderList rl){
        rl.setActive(true);
        model.addList(rl);
    }

    /**
     * Finds all inactive lists in database
     * @return a list of inactive lists
     */
    public List<ReminderList> getInactiveLists() {
        List<ReminderList> inactive = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.isActive() == false) {
                inactive.add(r);
            }
        }
        return inactive;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        finish();
    }

    // Getters and Setters section

    public List<Button> getRestoreButtons() {
        return restoreButtons;
    }

    public void setRestoreButtons(List<Button> restoreButtons) {
        this.restoreButtons = restoreButtons;
    }

    public View.OnClickListener getRestoreButtonPressed() {
        return restoreButtonPressed;
    }

    public void setRestoreButtonPressed(View.OnClickListener restoreButtonPressed) {
        this.restoreButtonPressed = restoreButtonPressed;
    }



}
