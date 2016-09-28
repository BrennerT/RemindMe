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

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Boolean getMode() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    public CalendarView getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarView calendar) {
        this.calendar = calendar;
    }

    public DatePicker getDp() {
        return dp;
    }

    public void setDp(DatePicker dp) {
        this.dp = dp;
    }

    public TimePicker getTp() {
        return tp;
    }

    public void setTp(TimePicker tp) {
        this.tp = tp;
    }

    public View.OnClickListener getAddButtonPressed() {
        return addButtonPressed;
    }

    public void setAddButtonPressed(View.OnClickListener addButtonPressed) {
        this.addButtonPressed = addButtonPressed;
    }
}
