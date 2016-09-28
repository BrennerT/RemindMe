package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.ReminderList;
import blau.team.remindme.notifier.Notifier;

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
    private Model model;
    private Notifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    View.OnClickListener addButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener settingsButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    View.OnClickListener archiveButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    public void onSwipeLeft(){

    }

    public void onSwipeRight(){

    }

    public List<ReminderList> getStandardList(){
        return null;
    }

    public List<ReminderList> getTempList(){
        return null;
    }

    public TableLayout getTemp() {
        return temp;
    }

    public void setTemp(TableLayout temp) {
        this.temp = temp;
    }

    public TableLayout getStandard() {
        return standard;
    }

    public void setStandard(TableLayout standard) {
        this.standard = standard;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
}
