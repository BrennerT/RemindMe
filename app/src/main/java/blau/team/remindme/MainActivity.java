package blau.team.remindme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;
import blau.team.remindme.notifier.Notifier;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Button addButton, settingsButton, archiveButton;
    private TableLayout temp, standard;
    private Model model;
    private Notifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        model = Model.getInstance();

        model.reload();

        addButton = (Button) findViewById(R.id.addButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);

        addButton.setOnClickListener(addButtonPressed);
        settingsButton.setOnClickListener(settingsButtonPressed);
        archiveButton.setOnClickListener(archiveButtonPressed);
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
