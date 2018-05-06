package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.akshaykale.swipetimeline.TimelineFragment;
import com.akshaykale.swipetimeline.TimelineGroupType;
import com.akshaykale.swipetimeline.TimelineObject;
import com.akshaykale.swipetimeline.TimelineObjectClickListener;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineActivity extends AppCompatActivity implements TimelineObjectClickListener {

    private static final String TAG = "TimelineActivity";

    private DrawerLayout mDrawerLayout;


    private MLogViewModel mLogViewModel;
    final ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;

    private TimelineFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.showOverflowMenu();


        //nav drawer
        final NavigationView navigationView = findViewById(R.id.nav_view);
        MenuItem menuItem;
        menuItem = navigationView.getMenu().findItem(R.id.nav_first_fragment);
        if(!menuItem.isChecked())
        {
            menuItem.setChecked(true);
        }

        mFragment = new TimelineFragment();

        //Set configurations
        mFragment.addOnClickListener(this);
        mFragment.setImageLoadEngine(new ImageLoad(getApplicationContext()));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mFragment);
        transaction.commit();



        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        mLogViewModel.getrmAllLogs().observe(this, new Observer<List<MLog>>() {
            @Override
            public void onChanged(@Nullable final List<MLog> mLogs) {
                Toast.makeText(TimelineActivity.this, "changed", Toast.LENGTH_SHORT).show();
                mFragment.setData(makeStuff(mLogs), TimelineGroupType.MONTH);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.detach(mFragment).attach(mFragment);
                transaction.replace(R.id.container, mFragment);
                transaction.commit();
            }
        });
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);





        FloatingActionButton butt = (FloatingActionButton) findViewById(R.id.fab);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(TimelineActivity.this, "testButt", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(TimelineActivity.this, LoggingActivity.class);
                startActivityForResult(myIntent, 1);

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
                    Intent myIntent = new Intent(TimelineActivity.this, StatisticsActivity.class);
                    startActivity(myIntent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public ArrayList<TimelineObject> makeStuff(List<MLog> mLogs){
        ArrayList<TimelineObject> objs = new ArrayList<>();
        for (MLog l : mLogs) {
            int happiness = Character.getNumericValue(l.getSlider().charAt(0));
            String url = "https://icons8.com/icon/set/smiley-face/ios";
            if (happiness < 3) {
                url = "http://png.icons8.com/color/96/000000/sad.png";
            }
            else if (happiness < 7) {
                url = "http://png.icons8.com/color/96/000000/neutral-emoticon.png";
            }
            else {
                url = "http://png.icons8.com/color/96/000000/lol.png";
            }

            url += "*" + l.getId();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd, hh:mm aa");
            String formattedDate = df.format(l.getDate());
           // mFragment.addSingleObject(new TestO(l.getDate(), (formattedDate), url), TimelineGroupType.MONTH);
            objs.add(new TestO(l.getDate(), (formattedDate), url));
        }
        //Toast.makeText(getApplicationContext(),"ObjSize: "+objs.size(),Toast.LENGTH_SHORT).show();
        return objs;
    }

    @Override
    public void onTimelineObjectClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(),"Clicked: "+timelineObject.getImageUrl(),Toast.LENGTH_SHORT).show();

       // Toast.makeText(TimelineActivity.this, timelineObject.getTitle(), Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(TimelineActivity.this, LoggingActivity.class);
        String mUrl = timelineObject.getImageUrl();
        int mID  = Integer.parseInt(mUrl.substring(mUrl.indexOf("*") + 1));
        myIntent.putExtra("dbID", mID);
        startActivityForResult(myIntent, 2);
    }

    @Override
    public void onTimelineObjectLongClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(),"LongClicked: "+timelineObject.getTitle(),Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"UPDATED",Toast.LENGTH_SHORT).show();
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