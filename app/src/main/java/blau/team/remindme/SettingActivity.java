package blau.team.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import blau.team.remindme.db.Model;

import static android.R.attr.mode;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SettingActivity extends AppCompatActivity {

    private Boolean vibration, sound;
    private ToggleButton vb, sb;
    private Button gpsSetter;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    View.OnClickListener vToggleButtonPressed= new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    View.OnClickListener sToggleButtonPressed= new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    View.OnClickListener gpsSetterPressed= new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

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
}
