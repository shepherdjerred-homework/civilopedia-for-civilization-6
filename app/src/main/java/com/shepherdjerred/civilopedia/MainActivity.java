package com.shepherdjerred.civilopedia;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;
import com.shepherdjerred.civilopedia.civitem.CivItem;
import com.shepherdjerred.civilopedia.civitem.CivItemDetailsFragment;
import com.shepherdjerred.civilopedia.civitem.CivItemListFragment;
import com.shepherdjerred.civilopedia.civitem.HomeFragment;
import com.shepherdjerred.civilopedia.storage.Datastore;
import com.shepherdjerred.civilopedia.storage.sqlite.SqliteDatastore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CivItemListFragment.OnListFragmentInteractionListener,
        CivItemDetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        MobileAds.initialize(this, "ca-app-pub-8402769089231334~8559189179");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        } else {
            Datastore datastore = new SqliteDatastore(getApplicationContext());
            ArrayList<? extends CivItem> civItems = null;

            switch (id) {
                case R.id.nav_civilizations:
                    civItems = datastore.getCivilizations();
                    break;
                case R.id.nav_leaders:
                    civItems = datastore.getLeaders();
                    break;
                case R.id.nav_city_states:
                    civItems = datastore.getCityStates();
                    break;
                case R.id.nav_districts:
                    civItems = datastore.getDistricts();
                    break;
                case R.id.nav_buildings:
                    civItems = datastore.getBuildings();
                    break;
                case R.id.nav_wonders:
                    civItems = datastore.getWonders();
                    break;
                case R.id.nav_projects:
                    civItems = datastore.getProjects();
                    break;
                case R.id.nav_units:
                    civItems = datastore.getUnits();
                    break;
            }

            if (civItems != null) {
                Fragment fragment = CivItemListFragment.newInstance(civItems);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle(item.getTitle());
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(CivItem item) {
        Fragment fragment = CivItemDetailsFragment.newInstance(item);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle(item.getName());
        }
    }

    @Override
    public void onCivItemDetailsFragmentInteraction(Uri uri) {

    }
}
