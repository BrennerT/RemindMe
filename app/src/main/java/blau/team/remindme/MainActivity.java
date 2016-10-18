package blau.team.remindme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.ReminderList;
import blau.team.remindme.notifier.Notifier;

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
    private Model model;
    private Notifier notifier;
    private RecyclerView tempRV;
    private RecyclerView.Adapter tempRVAdapter;
    private RecyclerView.LayoutManager tempRVLayoutmanager;
    protected static ArrayList<String> itemTexte;
    protected static ArrayList<Integer> itemFotoIDs;

    static TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);

        tempRV = (RecyclerView) findViewById(R.id.tempRV);
        tempRVLayoutmanager = new LinearLayoutManager(this);
        tempRVAdapter = new RVAdapterKlasse();
        itemTexte = new ArrayList<>();
        itemFotoIDs = new ArrayList<>();

        itemTexte.addAll(Arrays.asList("Liste1","Liste2"));
        itemFotoIDs.addAll(Arrays.asList(R.drawable.gps,R.drawable.uhr));
        tempRV.setLayoutManager(tempRVLayoutmanager);
        tempRV.setAdapter(tempRVAdapter);
        tv1 = (TextView) findViewById(R.id.textView);

        addButton.setOnClickListener(addButtonPressed);
        settingsButton.setOnClickListener(settingsButtonPressed);
        archiveButton.setOnClickListener(archiveButtonPressed);

    }

    View.OnClickListener addButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent changeActivityAdd = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(changeActivityAdd);
        }
    };

    View.OnClickListener settingsButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent changeActivitySettings = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(changeActivitySettings);
        }
    };

    View.OnClickListener archiveButtonPressed = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent changeActivityArchive = new Intent(getApplicationContext(),ArchiveActivity.class);
            startActivity(changeActivityArchive);
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
