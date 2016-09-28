package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ArchiveActivity extends AppCompatActivity {

    private Boolean vibration, sound;
    private List<Button> restoreButtons;
//    private Model model;

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

//    public List<ReminderList> getDeactiveLists(){
//        return null;
//    }

}
