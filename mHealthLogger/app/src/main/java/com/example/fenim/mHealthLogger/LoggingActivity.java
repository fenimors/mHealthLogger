package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.fenim.mHealthLogger.Constant.SLIDER_NAMES;

public class LoggingActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private static final String TAG = "LoggingActivity";

    private TextInputEditText firstname;
    private TextInputEditText lastname;
    private TextInputEditText note;
    private IndicatorSeekBar tSlider;
    //Button button;

    private DrawerLayout mDrawerLayout;

    private MLogViewModel mLogViewModel;
    private SharedPreferences sharedPref;
    Intent replyIntent;
    Long time_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.log_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.showOverflowMenu();

        //nav drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        replyIntent = new Intent();

        for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
            if (!sharedPref.getBoolean("key_slider" + (i + 1), true)) {
                findViewById(getResources().getIdentifier(
                        "seekBar" + (i + 1), "id", getPackageName())).setVisibility(View.GONE);
                findViewById(getResources().getIdentifier(
                        "seekBar" + (i + 1) + "Tit", "id", getPackageName())).setVisibility(View.GONE);
            }
            ((TextView) findViewById(getResources().getIdentifier(
                    "seekBar" + (i + 1) + "Tit", "id", getPackageName()))).setText(SLIDER_NAMES[i]);
        }

        //Getting the timestamp
        final Calendar c = Calendar.getInstance();


        //Handles sliders puts it into a string file to be passed back to the viewer
        //if( findViewById(R.id.seekBar1).getVisibility() != View.GONE)


        //database stuff
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        note = findViewById(R.id.note);

        int dbID = getIntent().getIntExtra("dbID", -1);
        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);

        if (dbID != -1) {
            MLog mmLog = mLogViewModel.getMlogByID(dbID);
            firstname.setText("lol");
            firstname.setText(mmLog.getFirstName());
            lastname.setText(mmLog.getLastName());
            note.setText(mmLog.getNote());
            replyIntent.putExtra("mID", mmLog.getId());
            time_data = mmLog.getDate();
            String slider = mmLog.getSlider();
            for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
                if (slider.charAt(i) != 'E') {
                    float t = Character.getNumericValue(slider.charAt(i));
                    ((IndicatorSeekBar) findViewById(getResources().getIdentifier("seekBar" + (i + 1), "id", getPackageName()))).setProgress(t);
                }
            }
        } else {
            time_data = c.getTimeInMillis();
        }


        // formattedDate have current date/time SHOWN
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY hh:mm.ss aa");
        String formattedDate = df.format(time_data);
        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText("Time: " + formattedDate);
        txtView.setTextSize(20);
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        //action panel stuff
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Logging");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        int id = menuItem.getItemId();

                        if (id == R.id.nav_first_fragment) {
                            Intent myIntent = new Intent(LoggingActivity.this, TimelineActivity.class);
                            startActivity(myIntent);
                        } else if (id == R.id.nav_second_fragment) {
                            Intent myIntent = new Intent(LoggingActivity.this, StatisticsActivity.class);
                            startActivity(myIntent);

                        } else if (id == R.id.nav_third_fragment) {
                            Intent myIntent = new Intent(LoggingActivity.this, SettingsActivity.class);
                            startActivity(myIntent);
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logging_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save: {
                // do your sign-out stuff
                if (TextUtils.isEmpty(firstname.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String slider = "";

                    for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
                        if (sharedPref.getBoolean("key_slider" + (i + 1), true)) {
                            slider += Integer.toString(((IndicatorSeekBar) findViewById(getResources().getIdentifier("seekBar" + (i + 1), "id", getPackageName())))
                                    .getProgress());
                        } else {
                            slider += 'E';
                        }
                    }


                    String fName = firstname.getText().toString();
                    String lName = lastname.getText().toString();
                    String mnote = note.getText().toString();


                    replyIntent.putExtra("fName", fName);
                    replyIntent.putExtra("lName", lName);
                    replyIntent.putExtra("mnote", mnote);
                    replyIntent.putExtra("time_data", time_data);
                    replyIntent.putExtra("mseekbar", slider);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
                break;
            }
            case R.id.menu_delete: {
                int dbID = getIntent().getIntExtra("dbID", -1);

                if (dbID != -1) {
                    MLog mmLog = mLogViewModel.getMlogByID(dbID);
                    mLogViewModel.deleteMlogByID(mmLog.getId());
                } else {
                    Toast.makeText(this, "Entry hasn't been saved yet or doesn't exist", Toast.LENGTH_SHORT).show();
                }

                finish();
                break;
            }
            case R.id.menu_quit: {
                finish();
                break;
            }
            case android.R.id.home: {
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return false;
    }
}