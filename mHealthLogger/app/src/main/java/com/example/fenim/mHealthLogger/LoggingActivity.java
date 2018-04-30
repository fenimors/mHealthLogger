package com.example.fenim.mHealthLogger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LoggingActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private static final String TAG = "LoggingActivity";

    private  TextInputEditText firstname;
    private  TextInputEditText lastname;
    private  TextInputEditText note;
    private  IndicatorSeekBar tSlider;
    //Button button;

    private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        for (int i = 0; i < Constant.SLIDER_COUNT; i++)
        {
            if( !sharedPref.getBoolean("key_slider" + (i+1), true))
                findViewById(getResources().getIdentifier(
                        "seekBar"+(i+1), "id", getPackageName())).setVisibility(View.GONE);
        }

        //Getting the timestamp
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());




        // formattedDate have current date/time SHOWN
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText("Current Date and Time : " + formattedDate);
        txtView.setTextSize(20);

        //Handles sliders puts it into a string file to be passed back to the viewer
        //if( findViewById(R.id.seekBar1).getVisibility() != View.GONE)


        //database stuff

        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        note = findViewById(R.id.note);


        //Add the button
        final Button button = findViewById(R.id.save);


        button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(firstname.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {

                    String slider ="";

                    for (int i = 0; i < Constant.SLIDER_COUNT; i++)
                    {
                        if( sharedPref.getBoolean("key_slider" + (i+1), true)) {
                            slider += Integer.toString(((IndicatorSeekBar) findViewById(getResources().getIdentifier("seekBar" +(i+1), "id", getPackageName())))
                                    .getProgress());
                        }
                        else { slider += 'E';}
                    }


                    String fName = firstname.getText().toString();
                    String lName = lastname.getText().toString();
                    String mnote = note.getText().toString();
                    Long time_data = c.getTimeInMillis();

                    //String test = Boolean.toString(sharedPref.getBoolean("key_slider1", true)); //this was for testing purposes
                    replyIntent.putExtra("fName", fName);
                    replyIntent.putExtra("lName", lName);
                    replyIntent.putExtra("mnote", mnote);
                    replyIntent.putExtra("time_data", time_data);
                    replyIntent.putExtra("mseekbar", slider);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

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
                        Intent myIntent = new Intent(LoggingActivity.this, TimelineActivity.class);
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