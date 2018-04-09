package com.example.fenim.uilearn2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.List;

import learn_db.AppDatabase;
import learn_db.Person;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "populus-database").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
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

                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        Button butt = (Button) findViewById(R.id.click_here_btn);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> everyone = db.getPersonDao().getAllPeople();
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