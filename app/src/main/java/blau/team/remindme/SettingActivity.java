package blau.team.remindme;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import blau.team.remindme.db.Model;

import static android.R.attr.mode;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SettingActivity extends AppCompatActivity {

    private Boolean vibration, sound;
    private Switch vb, sb;
    private Button gpsSetter1;
    private Button gpsSetter2;
    private Button gpsSetter3;
    private Button gpsSetter4;
    private Model model;
    private Location[] gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        model = Model.getInstance();
        vb.setOnClickListener(vSwitchPressed);
        sb.setOnClickListener(sSwitchPressed);
        gpsSetter1.setOnClickListener(gpsSetterPressed);
        gpsSetter2.setOnClickListener(gpsSetterPressed);
        gpsSetter3.setOnClickListener(gpsSetterPressed);
        gpsSetter4.setOnClickListener(gpsSetterPressed);

        //Laden der letzten Einstellungen
        vb.setEnabled(Boolean.valueOf(model.getSettings().get(0)));
        sb.setEnabled(Boolean.valueOf(model.getSettings().get(1)));
        for (int i =0; i < 4; i++){
            gps[i].setAltitude(Double.valueOf(model.getSettings().get(i+1)));
            gps[i].setLongitude(Double.valueOf(model.getSettings().get(i+1)));
        }
    }

    View.OnClickListener vSwitchPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SettingActivity.this.setVibration(!getVibration());
        }
    };

    View.OnClickListener sSwitchPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SettingActivity.this.setSound(!getSound());
        }
    };

    View.OnClickListener gpsSetterPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    public Location[] getGps() {
        return gps;
    }

    public void setGps(Location[] gps) {
        this.gps = gps;
    }
    public Boolean getVibration() {
        return vibration;
    }

    public void setVibration(Boolean vibration) {
        this.vibration = vibration;
    }

    public Boolean getSound() {
        return sound;
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //Updaten der DB
        //model.setSettings();
    }
}
