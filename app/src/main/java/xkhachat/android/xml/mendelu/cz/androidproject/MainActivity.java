package xkhachat.android.xml.mendelu.cz.androidproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import xkhachat.android.xml.mendelu.cz.androidproject.database.DatabaseHelper;
import xkhachat.android.xml.mendelu.cz.androidproject.donate.Donate;
import xkhachat.android.xml.mendelu.cz.androidproject.gallery.ViewPictures;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<Events> eventsList;
    ListView listView;
    Events events;
    Column_ListAdapter clAdapter = null;


    String id_item; Button showOnTheMap, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clAdapter = new Column_ListAdapter(MainActivity.this, R.layout.list_adapter_view,eventsList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddContent.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.top_cities) {
            Intent i = new Intent(this, ViewPictures.class);
            startActivity(i);
            return true;
        }
/*
        if (id == R.id.donate) {
            //Toast.makeText(this, "You clicked on the settings.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Donate.class);
            startActivity(i);
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        myDB = new DatabaseHelper(this);
        eventsList = new ArrayList<>();
        final Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(MainActivity.this, "Empty Database :(", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                events = new Events(
                        data.getString(0),
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4));
                eventsList.add(i, events);
                i++;
            }
            Column_ListAdapter adapter = new Column_ListAdapter(this, R.layout.list_adapter_view, eventsList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setLongClickable(true);
        }
    }
}