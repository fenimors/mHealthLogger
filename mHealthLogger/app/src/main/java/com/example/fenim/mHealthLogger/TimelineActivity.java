package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xwray.groupie.ExpandableGroup;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import com.xwray.groupie.Section;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private static final String TAG = "TimelineActivity";

    private DrawerLayout mDrawerLayout;


    private MLogViewModel mLogViewModel;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
       /* setContentView(R.layout.activity_timeline);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final GroupAdapter gadapter = new GroupAdapter();
        final Section Tw17 = new Section();
        final Section Tw18 = new Section();
        Tw17.setHeader(new HeaderItem("2017"));
        Tw18.setHeader(new HeaderItem("2018"));
        gadapter.add(Tw17);
        gadapter.add(Tw18);

       // final MLogAdapter adapter = new MLogAdapter(this);
        recyclerView.setAdapter(gadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        mLogViewModel.getrmAllLogs().observe(this, new Observer<List<MLog>>() {
            @Override
            public void onChanged(@Nullable final List<MLog> mLogs) {
                // Update the cached copy of the words in the adapter.


            }
        });
        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                List<MLog> mmLogs = mLogViewModel.getFromTable("2018");
                for(MLog l : mmLogs)
                {
                    //Tw18.add(new MlogItem(l, mContext));
                }



            }
        });*/
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);




        /*




        super.onCreate(savedInstanceState);
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_timeline);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        rainbow200 = getResources().getIntArray(R.array.rainbow_200);

        groupAdapter = new GroupAdapter();
        groupAdapter.setOnItemClickListener(onItemClickListener);
        groupAdapter.setSpanCount(12);
        populateAdapter();
        layoutManager = new GridLayoutManager(this, groupAdapter.getSpanCount());
        layoutManager.setSpanSizeLookup(groupAdapter.getSpanSizeLookup());

       // final RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
      //  recyclerView.addItemDecoration(new HeaderItemDecoration(gray, betweenPadding));
       // recyclerView.addItemDecoration(new InsetItemDecoration(gray, betweenPadding));
      //  recyclerView.addItemDecoration(new DebugItemDecoration(this));
        recyclerView.setAdapter(groupAdapter);

       /* final RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HeaderItemDecoration(gray, betweenPadding));
        recyclerView.addItemDecoration(new InsetItemDecoration(gray, betweenPadding));
        recyclerView.addItemDecoration(new DebugItemDecoration(this));
        recyclerView.setAdapter(groupAdapter);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GroupAdapter adapter = new GroupAdapter();
        Section section = new Section();
        section.setHeader(new MDateItem("test"));
        section.add(new MlogItem("Sam", "Feni","test",1234,"123", this));
        adapter.add(section);
        recyclerView.setAdapter(adapter);*/
       /* recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLogViewModel = ViewModelProviders.of(this).get(MLogViewModel.class);
        mLogViewModel.getrmAllLogs().observe(this, new Observer<List<MLog>>() {
            @Override
            public void onChanged(@Nullable final List<MLog> mLogs) {
                // Update the cached copy of the words in the adapter.
                //groupAdapter.setMlogs(mLogs);
            }
        });*/

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
        });




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
                    Intent myIntent = new Intent(TimelineActivity.this, MinimalExpandableActivity.class);
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
/*
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(Item item, View view) {
            if (item instanceof CardItem) {
                CardItem cardItem = (CardItem) item;
                if (!TextUtils.isEmpty(cardItem.getText())) {
                    Toast.makeText(TimelineActivity.this, cardItem.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };*/
/*
    private void populateAdapter() {
        // Expandable group
        ExpandableHeaderItem expandableHeaderItem = new ExpandableHeaderItem(R.string.expanding_group, R.string.expanding_group_subtitle);
        ExpandableGroup expandableGroup = new ExpandableGroup(expandableHeaderItem);
        for (int i = 0; i < 2; i++) {
            expandableGroup.add(new CardItem(rainbow200[1]));
        }
        groupAdapter.add(expandableGroup);

    }
*/
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