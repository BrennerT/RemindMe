package blau.team.remindme;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.GPSPoint;
import blau.team.remindme.db.model.Settings;
import blau.team.remindme.notifier.Notifier;
import io.realm.Realm;
import io.realm.RealmList;

import static android.R.attr.mode;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.media.CamcorderProfile.get;
import static blau.team.remindme.R.id.sound;

/**
 * This Activity is the Controller for activity_settings.xml
 * The Activity is needed change the Settings and save them to Database
 */
public class SettingActivity extends AppCompatActivity {

    private Settings settingInput;
    private Switch vb, sb;
    private Button gpsSetter1;
    private Button gpsSetter2;
    private Button gpsSetter3;
    private Button gpsSetter4;
    private Model model;
    private Realm realm;
    private Notifier notifier;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Get current instance of Model and Realm
        model = Model.getInstance();
        realm = Realm.getDefaultInstance();
        Notifier.getInstance();

        vb = (Switch) findViewById(R.id.vibration);
        sb = (Switch) findViewById(sound);
        gpsSetter1 = (Button) findViewById(R.id.corner1);
        gpsSetter2 = (Button) findViewById(R.id.corner2);
        gpsSetter3 = (Button) findViewById(R.id.corner3);
        gpsSetter4 = (Button) findViewById(R.id.corner4);

        vb.setOnClickListener(vSwitchPressed);
        sb.setOnClickListener(sSwitchPressed);
        gpsSetter1.setOnClickListener(gpsSetter1Pressed);
        gpsSetter2.setOnClickListener(gpsSetter2Pressed);
        gpsSetter3.setOnClickListener(gpsSetter3Pressed);
        gpsSetter4.setOnClickListener(gpsSetter4Pressed);

        // loads settings from database
        settingInput = model.getSettings();

        // if no settings are found make default settings
        if(settingInput == null) {
            RealmList<GPSPoint> corners = new RealmList<>();
            for (int i = 0; i <= 3; i++) {
                corners.add(new GPSPoint(0.0, 0.0));
            }
            settingInput = new Settings(false, false, corners);
        }

        // Update GUI
        vb.setChecked(settingInput.isVibration());
        sb.setChecked(settingInput.isSound());

        gpsSetter1.setText(String.valueOf(settingInput.getCorners().get(0).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(0).getLongitude()));
        gpsSetter2.setText(String.valueOf(settingInput.getCorners().get(1).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(1).getLongitude()));
        gpsSetter3.setText(String.valueOf(settingInput.getCorners().get(2).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(2).getLongitude()));
        gpsSetter4.setText(String.valueOf(settingInput.getCorners().get(3).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(3).getLongitude()));

    }

    /**
     * Is called when user wants to change settings for Vibration
     * Safes the change in Database
     */
    View.OnClickListener vSwitchPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            realm.beginTransaction();
            settingInput.setVibration(!settingInput.isVibration());
            realm.commitTransaction();
        }
    };

    /**
     * Is called when user wants to change settings for Sound
     * Safes the change in Database
     */
    View.OnClickListener sSwitchPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            realm.beginTransaction();
            settingInput.setSound(!settingInput.isSound());
            realm.commitTransaction();
        }
    };

    /**
     * Is called when user wants to change the first corner of GPSSquare
     * Safes the change in Database
     */
    View.OnClickListener gpsSetter1Pressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Location location = notifier.getActualLocation();
            realm.beginTransaction();
            settingInput.getCorners().get(0).setLatitude(location.getLatitude());
            settingInput.getCorners().get(0).setLongitude(location.getLongitude());
            realm.commitTransaction();
            gpsSetter1.setText(String.valueOf(settingInput.getCorners().get(0).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(0).getLongitude()));
        }
    };

    /**
     * Is called when user wants to change the second corner of GPSSquare
     * Safes the change in Database
     */
    View.OnClickListener gpsSetter2Pressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Location location = notifier.getActualLocation();
            realm.beginTransaction();
            settingInput.getCorners().get(1).setLatitude(location.getLatitude());
            settingInput.getCorners().get(1).setLongitude(location.getLongitude());
            realm.commitTransaction();
            gpsSetter2.setText(String.valueOf(settingInput.getCorners().get(1).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(1).getLongitude()));
        }
    };

    /**
     * Is called when user wants to change the third corner of GPSSquare
     * Safes the change in Database
     */
    View.OnClickListener gpsSetter3Pressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Location location = notifier.getActualLocation();
            realm.beginTransaction();
            settingInput.getCorners().get(2).setLatitude(location.getLatitude());
            settingInput.getCorners().get(2).setLongitude(location.getLongitude());
            realm.commitTransaction();
            gpsSetter3.setText(String.valueOf(settingInput.getCorners().get(2).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(2).getLongitude()));

        }
    };

    /**
     * Is called when user wants to change the fourth corner of GPSSquare
     * Safes the change in Database
     */
    View.OnClickListener gpsSetter4Pressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Location location = notifier.getActualLocation();
            realm.beginTransaction();
            settingInput.getCorners().get(3).setLatitude(location.getLatitude());
            settingInput.getCorners().get(3).setLongitude(location.getLongitude());
            realm.commitTransaction();
            gpsSetter4.setText(String.valueOf(settingInput.getCorners().get(3).getLatitude()) + "\n" + String.valueOf(settingInput.getCorners().get(3).getLongitude()));
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //update db
        model.setSettings(settingInput);
        finish();
    }

    // Getters and Setters section

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
