package blau.team.remindme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import blau.team.remindme.notifier.GPSLocator;
import blau.team.remindme.notifier.Notifier;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * This Activity is the Controller for activity_main.xml
 * This Activity is the Main Activity and startpoint of RemindMe.
 */
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
    private Intent startNotifierService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Realm
        Realm.init(getApplicationContext());

        // Load Model with new Database
        model = Model.getInstance();
        model.reload();

        // GPSLocator must get the Context from MainActivity
        GPSLocator.setmContext(this);
        // Start Service notifier
        startNotifierService = new Intent(this, Notifier.class);
        startService(startNotifierService);
        notifier = Notifier.getInstance();


        // Find all UI Elements
        addButton = (Button) findViewById(R.id.addButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);
        viewModeSwitch = (Switch) findViewById(R.id.switch1);
        showedLists = (TextView) findViewById(R.id.textView);

        addButton.setOnClickListener(addButtonPressed);
        settingsButton.setOnClickListener(settingsButtonPressed);
        archiveButton.setOnClickListener(archiveButtonPressed);
        viewModeSwitch.setOnClickListener(switchHandler);

        // check if permission for location tracking is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //Permission needed
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS},1);
        }

        // Load all UIELements
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

    /**
     * Starts AddActivity
     */
    View.OnClickListener addButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivityAdd = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(changeActivityAdd);
        }
    };

    /**
     * Starts Settings Activity
     */
    View.OnClickListener settingsButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivitySettings = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(changeActivitySettings);
        }
    };

    /**
     * Starts Archive Activity
     */
    View.OnClickListener archiveButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivityArchive = new Intent(getApplicationContext(), ArchiveActivity.class);
            startActivity(changeActivityArchive);
        }
    };

    /**
     * Switch between standard list view and temporary view
     */
    public View.OnClickListener switchHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleMode();
            tempRVAdapter = new RVAdapterKlasse();
            tempRV.setAdapter(tempRVAdapter);
            // true -> add standard list, false -> add temporary list
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
        // TODO implement change between temporary and regular list view
    }

    public void onSwipeRight() {
        // TODO implement change between temporary and regular list view
    }

    /**
     * Shows all Lists in GUI
     * @param lists The ReminderLists to show
     */
    public void showLists(List<ReminderList> lists){
        lists_toShow = new ArrayList<>();
        itemFotoIDs = new ArrayList<>();
        for (ReminderList l: lists) {
            lists_toShow.add(l);
            itemFotoIDs.addAll(Arrays.asList(R.drawable.gps));
        }

    }

    /**
     * Find all StandardLists in Model
     * @return list of StandardLists
     */
    public List<ReminderList> getStandardList() {
        List<ReminderList> standards = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() != 0 && r.isActive()) {
                standards.add(r);
            }
        }
        return standards;
    }

    /**
     * Find all temporary lists in model
     * @return list of temporary lists
     */
    public List<ReminderList> getTempList() {
        List<ReminderList> temps = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() == 0 && r.isActive()) {
                temps.add(r);
            }
        }
        return temps;
    }

    @Override
    protected void onResume() {
        //TODO Implement Reload
        super.onResume();
    }

    // Getter and Setter Section

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
