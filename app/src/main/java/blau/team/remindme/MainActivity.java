package blau.team.remindme;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderElement;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.notifier.Notifier;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
    static Model model;
    private TextView showedLists;
    private Notifier notifier;
    private RecyclerView tempRV;
    private RecyclerView.Adapter tempRVAdapter;
    private RecyclerView.LayoutManager tempRVLM;
    static ArrayList<ReminderList> lists_toShow;
    static ArrayList<Integer> itemFotoIDs;
    static TextView tv1, tv2, tv3, tv4, tv5, tv6;
    private Switch viewModeSwitch;
    private boolean viewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());

        model = Model.getInstance();
        model.reload();

        addButton = (Button) findViewById(R.id.addButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);
        viewModeSwitch = (Switch) findViewById(R.id.switch1);
        showedLists = (TextView) findViewById(R.id.textView);

        //temp = (TableLayout) findViewById(R.id.temp);
        //standard = (TableLayout) findViewById(R.id.standard);

        addButton.setOnClickListener(addButtonPressed);
        settingsButton.setOnClickListener(settingsButtonPressed);
        archiveButton.setOnClickListener(archiveButtonPressed);
        viewModeSwitch.setOnClickListener(switchHandler);

        tempRV = (RecyclerView) findViewById(R.id.tempRV);
        tempRVLM = new LinearLayoutManager(this);
        tempRV.setLayoutManager(tempRVLM);

        tempRVAdapter = new RVAdapterKlasse();
        tempRV.setAdapter(tempRVAdapter);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);

        viewMode = true;
        showLists(getTempList());
    }

    View.OnClickListener addButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivityAdd = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(changeActivityAdd);
        }
    };

    View.OnClickListener settingsButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivitySettings = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(changeActivitySettings);
        }
    };

    View.OnClickListener archiveButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivityArchive = new Intent(getApplicationContext(), ArchiveActivity.class);
            startActivity(changeActivityArchive);
        }
    };

    public View.OnClickListener switchHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleMode();
            tempRVAdapter = new RVAdapterKlasse();
            tempRV.setAdapter(tempRVAdapter);
            // true ist enspricht dem Anlegen einer Standardliste, false dem Anlegen einer Temporären Liste
            if (viewMode == true){
                showedLists.setText("Temporäre Listen");
                showLists(getTempList());
            } else {
                showedLists.setText("Standard-Listen");
                showLists(getStandardList());
            }
        }
    };

    public void toggleMode(){
        this.viewMode = !viewMode;
    }

    public void onSwipeLeft() {

    }

    public void onSwipeRight() {

    }
    public void showLists(List<ReminderList> lists){
        lists_toShow = new ArrayList<>();
        itemFotoIDs = new ArrayList<>();
        for (ReminderList l: lists) {
            lists_toShow.add(l);
            itemFotoIDs.addAll(Arrays.asList(R.drawable.gps));
        }


     /*   for (ReminderList l: tempLists) {
            //Erstellen der einer neuen Reihe für die Liste
            TableRow row = new TableRow(MainActivity.this);

            //Textausgabe für den Namen der Liste
            TextView name = new TextView(MainActivity.this);
            name.setText(l.getName());
            row.addView(name);

            //Textausgabe der einzelnen Elemente
            for (ReminderElement e: l.getElements()) {
                TextView elementName = new TextView(MainActivity.this);
                elementName.setText(e.getName());
                row.addView(elementName);
            }

            //Textausgabe für Beginndatum
            TextView datum = new TextView(MainActivity.this);
            Date begin = null;
            datum.setText(format.format(l.getTermins().getBeginDate()));
            row.addView(datum);
            row.setClickable(true);
            row.setBackgroundColor(Color.LTGRAY);
//            Auskommentieren, wenn alle Listen gelöscht werden sollen
//            model.deleteList(l);
            if(mode ==1) {
                temp.addView(row);
            } else {standard.addView(row);}
        }*/
    }

    public List<ReminderList> getStandardList() {
        List<ReminderList> standards = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() != 0 && r.isActive()) {
                standards.add(r);
            }
        }
        return standards;
    }

    public List<ReminderList> getTempList() {
        List<ReminderList> temps = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() == 0 && r.isActive()) {
                temps.add(r);
            }
        }
        return temps;
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
