package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.ReminderList;

public class ArchiveActivity extends AppCompatActivity {

    private Boolean vibration, sound;
    private List<Button> restoreButtons;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
    }

    public View.OnClickListener restoreButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    public List<ReminderList> getDeactiveLists(){
        return null;
    }

    public Boolean getVibration() {
        return vibration;
    }

    public void setVibration(Boolean vibration) {
        this.vibration = vibration;
    }

    public Boolean getSound() {
        return sound;
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }

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
