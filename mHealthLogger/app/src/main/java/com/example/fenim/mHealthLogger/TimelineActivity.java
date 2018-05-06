package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private static final String TAG = "TimelineActivity";

    private DrawerLayout mDrawerLayout;
    //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "populus-database").build();


    private MLogViewModel mLogViewModel;
    final ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //nav drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        MenuItem menuItem;
        menuItem = navigationView.getMenu().findItem(R.id.nav_first_fragment);
        if(!menuItem.isChecked())
        {
            menuItem.setChecked(true);
        }




// Create new timeline row (Row Id)
        TimelineRow myRow = new TimelineRow(0);

// To set the row Date (optional)
        myRow.setDate(new Date());
// To set the row Title (optional)
        myRow.setTitle("Title");
// To set the row Description (optional)
        myRow.setDescription("Description");
        /*
// To set the row bitmap image (optional)
        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
// To set row Below Line Color (optional)
        myRow.setBellowLineColor(Color.argb(255, 0, 0, 0));
// To set row Below Line Size in dp (optional)
        myRow.setBellowLineSize(6);
// To set row Image Size in dp (optional)
        myRow.setImageSize(40);
// To set background color of the row image (optional)
        myRow.setBackgroundColor(Color.argb(255, 0, 0, 0));
// To set the Background Size of the row image in dp (optional)
        myRow.setBackgroundSize(60);
// To set row Date text color (optional)
        myRow.setDateColor(Color.argb(255, 0, 0, 0));
// To set row Title text color (optional)
        myRow.setTitleColor(Color.argb(255, 0, 0, 0));
// To set row Description text color (optional)
        myRow.setDescriptionColor(Color.argb(255, 0, 0, 0));*/

// Add the new row to the list
        timelineRowsList.add(myRow);
        timelineRowsList.add(myRow);

// Create the Timeline Adapter
        myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,
                //if true, list will be sorted by date
                true);

// Get the ListView and Bind it with the Timeline Adapter
        ListView myListView = (ListView) findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);
        //myListView.setLayoutManager(new LinearLayoutManager(this));



        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineRow row = timelineRowsList.get(position);
                Toast.makeText(TimelineActivity.this, row.getTitle(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(TimelineActivity.this, LoggingActivity.class);
                myIntent.putExtra("dbID", row.getId());
                startActivityForResult(myIntent, 2);

            }
        };
        myListView.setOnItemClickListener(adapterListener);






        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.GONE);
       // final MLogAdapter adapter = new MLogAdapter(this);
       // recyclerView.setAdapter(adapter);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        mLogViewModel.getrmAllLogs().observe(this, new Observer<List<MLog>>() {
            @Override
            public void onChanged(@Nullable final List<MLog> mLogs) {
                // Update the cached copy of the words in the adapter.
               // adapter.setMlogs(mLogs);
               // timelineRowsList.add(mLogs);
                timelineRowsList.clear();
                Toast.makeText(TimelineActivity.this, "changed", Toast.LENGTH_SHORT).show();
                for (MLog l : mLogs) {
                    timelineRowsList.add(new TimelineRow(l.getId(), (new Date(l.getDate())), l.getNote(), l.getFirstName()));
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);





        FloatingActionButton butt = (FloatingActionButton) findViewById(R.id.fab);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(TimelineActivity.this, "testButt", Toast.LENGTH_SHORT).show();
                timelineRowsList.clear();
                myAdapter.notifyDataSetChanged();

            }
        });



        //nav drawer stuff
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Timeline");
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

        NavigationView navigationView = findViewById(R.id.nav_view);
        MenuItem menuItem;
        menuItem = navigationView.getMenu().findItem(R.id.nav_first_fragment);
        menuItem.setChecked(true);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String fName = data.getStringExtra("fName");
            String lName = data.getStringExtra("lName");
            String mnote = data.getStringExtra("mnote");
            Long time_data = data.getLongExtra("time_data", 0);
            String mseekbar = data.getStringExtra("mseekbar");

            MLog mlog = new MLog(fName,lName,mnote,time_data, mseekbar);
            mLogViewModel.insert(mlog);
        }
        else if (requestCode == 2 && resultCode == RESULT_OK) {
            String fName = data.getStringExtra("fName");
            String lName = data.getStringExtra("lName");
            String mnote = data.getStringExtra("mnote");
            Long time_data = data.getLongExtra("time_data", 0);
            String mseekbar = data.getStringExtra("mseekbar");
            MLog mlog = new MLog(fName,lName,mnote,time_data, mseekbar);
            int mID = data.getIntExtra("mID", 0);
            mlog.setId(mID);
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