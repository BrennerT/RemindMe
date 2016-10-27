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
import blau.team.remindme.db.model.ReminderList;

public class ArchiveActivity extends AppCompatActivity {


    static Model model;
    private TableLayout inactive;
    private Button backButton;
    private RecyclerView tempRV;
    private RecyclerView.Adapter tempRVAdapter;
    private RecyclerView.LayoutManager tempRVLM;
    static ArrayList<ReminderList> lists_toShow;
    static ArrayList<Integer> itemFotoIDs;
    static TextView tv1a, tv2a, tv3a, tv4a, tv5a, tv6a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonPressed);

        tempRV = (RecyclerView) findViewById(R.id.tempRV);
        tempRVLM = new LinearLayoutManager(this);
        tempRV.setLayoutManager(tempRVLM);

        tempRVAdapter = new RVArchiveAdapterKlasse();
        tempRV.setAdapter(tempRVAdapter);

        model = Model.getInstance();

        tv1a = (TextView) findViewById(R.id.tv1a);
        tv2a = (TextView) findViewById(R.id.tv2a);
        tv3a = (TextView) findViewById(R.id.tv3a);
        tv4a = (TextView) findViewById(R.id.tv4a);
        tv5a = (TextView) findViewById(R.id.tv5a);
        tv6a = (TextView) findViewById(R.id.tv6a);

        showLists(getInactiveLists());
    }

    public void showLists(List<ReminderList> lists){
        lists_toShow = new ArrayList<>();
        itemFotoIDs = new ArrayList<>();
        for (ReminderList l: lists) {
            lists_toShow.add(l);
            itemFotoIDs.addAll(Arrays.asList(R.drawable.trash));
        }
    }


    public List<ReminderList> getInactiveLists() {
        List<ReminderList> inactive = new ArrayList<>();
        for (ReminderList r : model.getLists()) {
            if (r.isActive() == false) {
                inactive.add(r);
            }
        }
        return inactive;
    }

    View.OnClickListener backButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent changeActivityMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(changeActivityMain);
        }
    };

}
