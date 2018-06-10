package xkhachat.android.xml.mendelu.cz.androidproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import xkhachat.android.xml.mendelu.cz.androidproject.database.DatabaseHelper;

public class Column_ListAdapter extends ArrayAdapter<Events> {

    String id_item;
    private LayoutInflater mInflater;
    private ArrayList<Events> events;
    private int mViewResourceId;
    DatabaseHelper myDB = new DatabaseHelper(getContext());

    public Column_ListAdapter(Context context, int textViewResourceId, ArrayList<Events> events) {
        super(context, textViewResourceId, events);
        this.events = events;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        final Events events = this.events.get(position);

        if (events != null) {
            TextView name = (TextView) convertView.findViewById(R.id.textName);
            TextView description = (TextView) convertView.findViewById(R.id.textDescription);
            final TextView longitude = (TextView) convertView.findViewById(R.id.textLongitude);
            final TextView latitude = (TextView) convertView.findViewById(R.id.textLatitude);

            ImageButton showOnTheMap = (ImageButton) convertView.findViewById(R.id.showOnTheMap);
            ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);

            if (name != null) {
                name.setText(events.getName());
            }
            if (description != null) {
                description.setText((events.getDescription()));
            }
            if (longitude != null) {
                longitude.setText((events.getLongitude()));
            }
            if (latitude != null) {
                latitude.setText((events.getLatitude()));
            }

            showOnTheMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String urlAddress = "http://maps.google.com/maps?q=" +
                            events.getLatitude() + "," +
                            events.getLongitude() +
                            "&iwloc=A&hl=es";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                    getContext().startActivity(intent);

                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setTitle("Attantion !");
                    builder.setMessage("Delete selected item ?");

                    id_item = events.getId();
                    builder.setNegativeButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Integer deleteRows = myDB.deleteItem(id_item);

                                    if (deleteRows > 0) {
                                        Toast.makeText(v.getRootView().getContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getRootView().getContext(), "Data was not deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    builder.setPositiveButton("No", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        return convertView;
    }
}