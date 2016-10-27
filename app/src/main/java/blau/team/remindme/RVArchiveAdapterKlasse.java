package blau.team.remindme;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import blau.team.remindme.db.Model;
import blau.team.remindme.db.model.ReminderList;
import io.realm.Realm;

import static blau.team.remindme.ArchiveActivity.tv1a;
import static blau.team.remindme.ArchiveActivity.tv2a;
import static blau.team.remindme.ArchiveActivity.tv3a;
import static blau.team.remindme.ArchiveActivity.tv4a;
import static blau.team.remindme.ArchiveActivity.tv5a;
import static blau.team.remindme.ArchiveActivity.tv6a;
import static blau.team.remindme.MainActivity.tv1;
import static blau.team.remindme.MainActivity.tv2;
import static blau.team.remindme.MainActivity.tv3;
import static blau.team.remindme.MainActivity.tv4;
import static blau.team.remindme.MainActivity.tv5;
import static blau.team.remindme.MainActivity.tv6;

/**
 * Created by Lukas on 27.10.2016.
 */

public class RVArchiveAdapterKlasse extends RecyclerView.Adapter<RVArchiveAdapterKlasse.ViewHolderKlasse>{
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");

    /**
     * Based on RVAdapterKlasse,for ArchiveActivity
     */
    public class ViewHolderKlasse extends RecyclerView.ViewHolder {

        TextView itemTextView;
        ImageView itemImageView;
        Button deleteButton;
        Model model;

        public ViewHolderKlasse(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.textViewItem);
            itemImageView = (ImageView) itemView.findViewById(R.id.imageViewItem);
            deleteButton = (Button) itemView.findViewById(R.id.deleteButton);
            model = Model.getInstance();
        }
    }

    @Override
    public RVArchiveAdapterKlasse.ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout1, null);

        return new RVArchiveAdapterKlasse.ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(final RVArchiveAdapterKlasse.ViewHolderKlasse viewHolderKlasse, final int i) {

            viewHolderKlasse.deleteButton.setText("RESTORE");
            viewHolderKlasse.deleteButton.setTextColor(Color.BLACK);
            viewHolderKlasse.itemTextView.setText(ArchiveActivity.lists_toShow.get(i).getName());
            viewHolderKlasse.itemImageView.setImageResource(ArchiveActivity.itemFotoIDs.get(i));



            viewHolderKlasse.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tv1a.setText(ArchiveActivity.lists_toShow.get(i).getName());
                    tv2a.setText(ArchiveActivity.lists_toShow.get(i).getElements().get(0).getName());
                    tv3a.setText(ArchiveActivity.lists_toShow.get(i).getElements().get(1).getName());
                    tv4a.setText(ArchiveActivity.lists_toShow.get(i).getElements().get(2).getName());
                    tv5a.setText(format.format(ArchiveActivity.lists_toShow.get(i).getTermins().getBeginDate()));
                    tv6a.setText(format.format(ArchiveActivity.lists_toShow.get(i).getTermins().getEndDate()));
                }
            });



            viewHolderKlasse.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Realm realm = Realm.getDefaultInstance();
                    ReminderList toHandle = ArchiveActivity.lists_toShow.get(i);

                    realm.beginTransaction();
                    toHandle.setActive(true);
                    realm.commitTransaction();
                    Log.d("Restore ", "");
                    ArchiveActivity.model.reload();
                    ViewGroup.LayoutParams params = viewHolderKlasse.itemView.getLayoutParams();
                    params.height = 0;
                    viewHolderKlasse.itemView.setLayoutParams(params);
                }
            });
        }


    @Override
    public int getItemCount() {
            return ArchiveActivity.lists_toShow.size();
    }
}
