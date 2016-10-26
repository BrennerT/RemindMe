package blau.team.remindme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
    private Model model;
    private Notifier notifier;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());

        model = Model.getInstance();
        model.reload();

        // GPSLocator must get the Context from MainActivity
        GPSLocator.setmContext(this);
        notifier = Notifier.getInstance();

        addButton = (Button) findViewById(R.id.addButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);

        temp = (TableLayout) findViewById(R.id.temp);
        standard = (TableLayout) findViewById(R.id.standard);

        addButton.setOnClickListener(addButtonPressed);
        settingsButton.setOnClickListener(settingsButtonPressed);
        archiveButton.setOnClickListener(archiveButtonPressed);

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

        showLists(getTempList(),1);
        showLists(getStandardList(),0);

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

    public void onSwipeLeft() {

    }

    public void onSwipeRight() {

    }
    public void showLists(List<ReminderList> tempLists, int mode){
        for (ReminderList l: tempLists) {
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
        }
    }

    public List<ReminderList> getStandardList() {
        List<ReminderList> standards = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() != 0) {
                standards.add(r);
            }
        }
        return standards;
    }

    public List<ReminderList> getTempList() {
        List<ReminderList> temps = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.getInterval() == 0) {
                temps.add(r);
            }
        }
        return temps;
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
