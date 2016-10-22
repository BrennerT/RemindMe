package blau.team.remindme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private EditText et1, et2, et3, dt1, dt2, tt1, tt2;
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

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        dt1 = (EditText) findViewById(R.id.dateText1);
        dt2 = (EditText) findViewById(R.id.dateText2);
        tt1 = (EditText) findViewById(R.id.timeText1);
        tt2 = (EditText) findViewById(R.id.timeText2);

        mode = false;
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
            // true ist enspricht dem Anlegen einer Standardliste, false dem Anlegen einer Temporären Liste
            if (mode == true){
                modeSwitch.setText("Standard");
            } else {
                modeSwitch.setText("Temporär");
            }
        }
    };

    public Date convertToDate(String s){
        //Konvertieren des User Date & Zeit Inputs zu einem Datum
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
        try {
            Date date = format.parse(s);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public View.OnClickListener addButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Element-Input des Users in Liste packen
            List<String> elements = new ArrayList<>();
            elements.add(et1.getText().toString());
            elements.add(et2.getText().toString());
            elements.add(et3.getText().toString());
            elementInput = elements;

            //Name-Input noch nicht implementiert
            nameInput = "Test";

            // Konvertieren des Inputs zu Daten
            dateBeginInput = convertToDate(dt1.getText().toString() + ' '+ tt1.getText().toString());
            dateEndInput = convertToDate(dt2.getText().toString() + ' '+ tt2.getText().toString());

            //Standard-Modus Intervall = 7 , Temporär-Modus Intervall = 0
            if (mode == true){
                intervalInput = 7;
            } else { intervalInput = 0; }

            //Prüfung auf leere Eingaben
            if(dateBeginInput != null && dateEndInput != null &&  !elementInput.isEmpty() && nameInput != null){
                // HInzufügen der ReminderListe zur DB
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

            linearLayout.addView(dp, 3);
            linearLayout.addView(submitDate, 4);

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

            linearLayout.addView(tp, 4);
            linearLayout.addView(submitTime, 5);
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
