package com.example.fenim.uilearn2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "populus-database").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listing stuff using recycler view
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        recyclerView = findViewById(R.id.recycler_view);

        // TODO: 4/11/2018 threading
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        List<MLog> mlogs = db.mlogDao().getAllLogs();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MLogAdapter(mlogs);
        recyclerView.setAdapter(adapter);


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
                            Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(myIntent);
                        } else if (id == R.id.nav_third_fragment) {
                            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
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