package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private static final String TAG = "TimelineActivity";

    private DrawerLayout mDrawerLayout;
    //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "populus-database").build();


    private MLogViewModel mLogViewModel;

    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_LIST_VIEW = "list view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* setContentView(R.layout.activity_timeline);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final MLogAdapter adapter = new MLogAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        mLogViewModel.getrmAllLogs().observe(this, new Observer<List<MLog>>() {
            @Override
            public void onChanged(@Nullable final List<MLog> mLogs) {
                // Update the cached copy of the words in the adapter.
                adapter.setMlogs(mLogs);
            }
        });*/
        setContentView(R.layout.activity_demo);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new ExampleExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ExpandableExampleFragment(), FRAGMENT_LIST_VIEW)
                    .commit();
        }
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);




        //nav drawer stuff
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Timeline");
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
                int id = menuItem.getItemId();

                if (id == R.id.nav_first_fragment) {
                    // Handle the camera action
                } else if (id == R.id.nav_second_fragment) {
                    Intent myIntent = new Intent(TimelineActivity.this, LoggingActivity.class);
                    startActivityForResult(myIntent, 1);
                } else if (id == R.id.nav_third_fragment) {
                    Intent myIntent = new Intent(TimelineActivity.this, SettingsActivity.class);
                    startActivity(myIntent);
                }

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                return true;
            }
        });

/*
        Button butt = (Button) findViewById(R.id.click_here_btn);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> everyone = db.getPersonDao().getAllPeople();
            }
        });*/

    }

    public AbstractExpandableDataProvider getDataProvider() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DATA_PROVIDER);
        return ((ExampleExpandableDataProviderFragment) fragment).getDataProvider();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String fName = data.getStringExtra("fName");
            String lName = data.getStringExtra("lName");
            String mnote = data.getStringExtra("mnote");
            Long time_data = data.getLongExtra("time_data", 0);
            String mseekbar = data.getStringExtra("mseekbar");

            MLog mlog = new MLog(fName,lName,mnote,time_data, mseekbar);
            mLogViewModel.insert(mlog);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Word not saved because it is empty",
                    Toast.LENGTH_LONG).show();
        }
    }

}