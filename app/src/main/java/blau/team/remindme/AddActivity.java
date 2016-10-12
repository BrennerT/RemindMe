package blau.team.remindme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Date;
import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;

import static android.R.attr.name;
import static android.R.attr.start;

public class AddActivity extends AppCompatActivity {

    private Button addButton, changeDateButton, changeTimeButton, submitDate, submitTime;
    private LinearLayout linearLayout;
    private Boolean mode;
    private Switch modeSwitch;
    //    private CalendarView calendar;
    private DatePicker dp;
    private TimePicker tp;
    private Model model;
    private Date dateBeginInput, dateEndInput;
    private List<String> elementInput;
    private int intervalInput;
    private String nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        model = Model.getInstance();

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        modeSwitch = (Switch) findViewById(R.id.modeSwitch);
        modeSwitch.setOnClickListener(switchHandler);

        addButton = (Button) findViewById(R.id.addButton);
        changeDateButton = (Button) findViewById(R.id.changeDateButton);
        changeTimeButton = (Button) findViewById(R.id.changeTimeButton);

        addButton.setOnClickListener(addButtonPressed);
        changeDateButton.setOnClickListener(changeDateButtonPressed);
        changeTimeButton.setOnClickListener(changeTimeButtonPressed);
    }

    public Boolean getMode() {
        return this.mode;
    }

    public void setMode(Boolean mode) { this.mode = mode;}

    public void toggleMode() {
        this.mode = !this.mode;
    }

    public View.OnClickListener switchHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleMode();
        }
    };

    public View.OnClickListener addButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dateBeginInput != null && dateEndInput != null &&  !elementInput.isEmpty() && nameInput != null){
                model.addList(intervalInput,dateBeginInput,dateEndInput, nameInput, elementInput);
            }
            else{
                //TODO: Notification about missing input
            }
            Intent changeActivityMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(changeActivityMain);
        }
    };

    public View.OnClickListener changeDateButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dp = new DatePicker(AddActivity.this);

            submitDate = new Button(AddActivity.this);
            submitDate.setText("Submit Date");
            submitDate.setOnClickListener(onSubmitDate);

            linearLayout.addView(dp, 2);
            linearLayout.addView(submitDate, 3);

        }
    };

    public View.OnClickListener onSubmitDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Add functionality to get Date from DatePicker

            linearLayout.removeView(dp);
            linearLayout.removeView(submitDate);
        }
    };

    public View.OnClickListener changeTimeButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            tp = new TimePicker(AddActivity.this);
            tp.setIs24HourView(true);

            submitTime = new Button(AddActivity.this);
            submitTime.setText("Submit Time");
            submitTime.setOnClickListener(onSubmitTime);

            linearLayout.addView(tp, 3);
            linearLayout.addView(submitTime, 4);
        }
    };

    public View.OnClickListener onSubmitTime = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Add functionality to get Time from TimePicker

            linearLayout.removeView(tp);
            linearLayout.removeView(submitTime);
        }
    };

    // Getter and Setter section


    public Date getDateBeginInput() {
        return dateBeginInput;
    }

    public void setDateBeginInput(Date dateBeginInput) {
        this.dateBeginInput = dateBeginInput;
    }

    public Date getDateEndInput() {
        return dateEndInput;
    }

    public void setDateEndInput(Date dateEndInput) {
        this.dateEndInput = dateEndInput;
    }

    public List<String> getElementInput() {
        return elementInput;
    }

    public void setElementInput(List<String> elementInput) {
        this.elementInput = elementInput;
    }

    public int getIntervalInput() {
        return intervalInput;
    }

    public void setIntervalInput(int intervalInput) {
        this.intervalInput = intervalInput;
    }
}
