package xkhachat.android.xml.mendelu.cz.androidproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xkhachat.android.xml.mendelu.cz.androidproject.database.DatabaseHelper;

public class AddContent extends AppCompatActivity {

    EditText etName, etDescription, etLongitude, etLatitude;
    Button btnAdd, btnView;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_content_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        etLatitude = (EditText) findViewById(R.id.etLatitude);
        etLongitude = (EditText) findViewById(R.id.etLongitude);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addName = etName.getText().toString();
                String addDescription = etDescription.getText().toString();
                String addLongitude = etLongitude.getText().toString();
                String addLatitude = etLatitude.getText().toString();

                if (addName.length() != 0 &&
                        addDescription.length() != 0 &&
                        addLongitude.length() != 0 &&
                        addLatitude.length() != 0) {
                    AddData(addName, addDescription, addLongitude, addLatitude);
                    etLongitude.setText("");
                    etDescription.setText("");
                    etName.setText("");
                    etLatitude.setText("");
                } else {
                    Toast.makeText(AddContent.this, "You must put something in text field!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void AddData(String name, String description, String longitude, String latitude) {
        boolean insertData = myDB.addData(name, description, longitude, latitude);

        if (insertData == true) {
            Toast.makeText(AddContent.this, "Data inserted !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddContent.this, "Error :(.", Toast.LENGTH_LONG).show();
        }
    }
}
