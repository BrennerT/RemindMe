package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AddActivity extends AppCompatActivity {

    private Button addButton;
    private Boolean mode;
    private CalendarView calendar;
    private DatePicker dp;
    private TimePicker tp;
//    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    View.OnClickListener addButtonPressed= new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };
}
