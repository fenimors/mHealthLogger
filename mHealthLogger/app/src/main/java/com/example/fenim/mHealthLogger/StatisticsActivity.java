package com.example.fenim.mHealthLogger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.Thread.sleep;

public class StatisticsActivity extends AppCompatActivity {

    private MLogViewModel mLogViewModel;
    private DrawerLayout mDrawerLayout;

    public static Button btnDate, btnDate2, refresh;
    SimpleDateFormat simpleDateFormat;

    FragmentManager fm = getSupportFragmentManager();
    public static TextView SelectedDateView;
    public static TextView SelectedDateView2;
    public static Calendar frmCalen;
    public static Calendar toCalen;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.stats_toolbar);
        //setSupportActionBar(myToolbar);
        //myToolbar.showOverflowMenu();
       /* tM = 6;
        tD = 12;
        tY = 2018;
        fM = 5;
        fD = 15;
        fY = 2017;*/
        SelectedDateView = (TextView)this.findViewById(R.id.selected_date);
        SelectedDateView2 = (TextView)this.findViewById(R.id.selected_date2);
        btnDate = (Button) this.findViewById(R.id.to);
        btnDate2 = (Button) this.findViewById(R.id.from);
        refresh = (Button) this.findViewById(R.id.showRef);

        frmCalen = Calendar.getInstance();
        toCalen = Calendar.getInstance();
        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(fm, "datePicker");


            }
        });
        btnDate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(fm, "datePicker2");

            }
        });

        for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
            if (!sharedPref.getBoolean("textView" + (i + 1), true)) {
                findViewById(getResources().getIdentifier(
                        "textView" + (i + 1), "id", getPackageName())).setVisibility(View.GONE);
            }
        }

        final float[] keepAvgs = new float[Constant.SLIDER_COUNT];
        final int[] keepCount = new int[Constant.SLIDER_COUNT];





        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(StatisticsActivity.this, "Hey: " + frmCalen.getTime().toString() + "    " + toCalen.getTime().toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0 ; i < Constant.SLIDER_COUNT; i++) {
                    keepAvgs[i] = 0;
                    keepCount[i] = 0;
                }
                frmCalen.getTime().toString();
                List<MLog> mLogs = mLogViewModel.getFromTable(frmCalen.getTimeInMillis(), toCalen.getTimeInMillis());

                for (MLog l : mLogs) {
                    String slider = l.getSlider();
                    for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
                        if (slider.charAt(i) != 'E') {
                            keepAvgs[i] += Character.getNumericValue(slider.charAt(i));
                            keepCount[i] += 1;
                        }
                    }
                }
                for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
                    if ((sharedPref.getBoolean("textView" + (i + 1), true)) && (keepCount[i] > 0)) {
                        ((TextView) findViewById(getResources().getIdentifier(
                                "textView" + (i + 1), "id", getPackageName()))).setText(Constant.SLIDER_NAMES[i] + " Average: " + Float.toString(keepAvgs[i]/keepCount[i]));
                    }
                }

            }

        });


        //nav drawer stuff
        final NavigationView navigationView = findViewById(R.id.nav_view);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.stats_toolbar);
        toolbar.setTitle("Statistics View");
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
                            Intent myIntent = new Intent(StatisticsActivity.this, TimelineActivity.class);
                            startActivityForResult(myIntent, 1);
                        } else if (id == R.id.nav_second_fragment) {
                          //  Intent myIntent = new Intent(StatisticsActivity.this, LoggingActivity.class);
                          //  startActivityForResult(myIntent, 1);
                        } else if (id == R.id.nav_third_fragment) {
                            Intent myIntent = new Intent(StatisticsActivity.this, SettingsActivity.class);
                            startActivity(myIntent);
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            if(getTag() == "datePicker") {
                frmCalen.set(year,month,day);
                SelectedDateView.setText((month + 1) + "-" + day + "-" + year);
            }
            else {
                toCalen.set(year,month,day);
                SelectedDateView2.setText((month + 1) + "-" + day + "-" + year);
            }
        }
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
