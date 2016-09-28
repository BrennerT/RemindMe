package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
//    private Model model;
//    private Notifier notifier;

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

//    public List<ReminderList> getStandardList(){
//        return null;
//    }
//
//    public List<ReminderList> getTempList(){
//        return null;
//    }

}
