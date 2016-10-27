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

import static blau.team.remindme.MainActivity.*;
import static blau.team.remindme.ArchiveActivity.*;

/**
 * Created by Adrian on 11.10.2016.
 */
public class RVAdapterKlasse extends RecyclerView.Adapter<RVAdapterKlasse.ViewHolderKlasse> {

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");

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
    public ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout1, null);

        return new ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(final ViewHolderKlasse viewHolderKlasse, final int i) {

            viewHolderKlasse.itemTextView.setText(MainActivity.lists_toShow.get(i).getName());
            viewHolderKlasse.itemImageView.setImageResource(MainActivity.itemFotoIDs.get(i));

            viewHolderKlasse.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv1.setText(MainActivity.lists_toShow.get(i).getName());
                    tv2.setText(MainActivity.lists_toShow.get(i).getElements().get(0).getName());
                    tv3.setText(MainActivity.lists_toShow.get(i).getElements().get(1).getName());
                    tv4.setText(MainActivity.lists_toShow.get(i).getElements().get(2).getName());
                    tv5.setText(format.format(MainActivity.lists_toShow.get(i).getTermins().getBeginDate()));
                    tv6.setText(format.format(MainActivity.lists_toShow.get(i).getTermins().getEndDate()));
                }

            });




            viewHolderKlasse.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Realm realm = Realm.getDefaultInstance();
                    ReminderList toHandle = MainActivity.lists_toShow.get(i);

                    realm.beginTransaction();
                    toHandle.setActive(false);
                    realm.commitTransaction();
                    Log.d("Delete ", "");
                    MainActivity.model.reload();
                    ViewGroup.LayoutParams params = viewHolderKlasse.itemView.getLayoutParams();
                    params.height = 0;
                    viewHolderKlasse.itemView.setLayoutParams(params);
                }

            });
        }


        @Override
        public int getItemCount() {
            return MainActivity.lists_toShow.size();
        }
    }