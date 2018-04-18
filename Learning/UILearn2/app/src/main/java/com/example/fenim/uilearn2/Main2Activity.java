package com.example.fenim.uilearn2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    TextInputEditText firstname;
    TextInputEditText lastname;
    TextInputEditText note;
    Button button;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText("Current Date and Time : "+formattedDate);
        txtView.setTextSize(20);


        //database stuff
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        note = findViewById(R.id.note);


        button = findViewById(R.id.save);
        // TODO: 4/11/2018 thread this stuff
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.mlogDao().insertAll(new MLog(firstname.getText().toString(), lastname.getText().toString(), note.getText().toString(), c.getTimeInMillis()));
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });

        //action panel stuff
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Logging");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(myIntent);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}