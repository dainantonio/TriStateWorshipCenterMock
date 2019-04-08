package com.example.dainr.tristateworshipcentermock;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String title;

    private ExpandableListAdapter expandableListAdapter;
    private List <String> listDataHeader;
    private HashMap <String, List <String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar replacing ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        title = getTitle().toString();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            MenuItem item = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }

        ExpandableListView expandableListView = findViewById(R.id.expandListView);


        listDataChild = ExpandableListDataSource.getData();
        listDataHeader = new ArrayList <>(listDataChild.keySet());
        ExpandableListAdapter expandableListAdapter = new com.example.dainr.tristateworshipcentermock.ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Temporary code:

                // till here
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " + Objects.requireNonNull(listDataChild.get(listDataHeader.get(groupPosition))).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_info:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_donations:
                intent = new Intent(MainActivity.this, DonationsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_events:
                intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_ministries:

                break;
            case R.id.nav_services:
                intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_newsletter:
                Intent newsletterIntent = new Intent(MainActivity.this, NewsletterActivity.class);
                startActivity(newsletterIntent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
