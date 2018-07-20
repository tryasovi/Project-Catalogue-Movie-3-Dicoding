package com.example.donnyekosaputro.mydictionary;

import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.donnyekosaputro.mydictionary.Adapter.SearchAdapter;
import com.example.donnyekosaputro.mydictionary.Helper.KamusHelper;
import com.example.donnyekosaputro.mydictionary.Model.KamusModel;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private KamusHelper kamusHelper;
    private SearchAdapter searchAdapter;

    private ArrayList<KamusModel> list = new ArrayList<>();

    RecyclerView recyclerView;
    SearchView searchView;
    String lang_selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        searchView = (SearchView)findViewById(R.id.search_bar);
        searchView.onActionViewExpanded(); //supaya always expanded
        searchView.setOnQueryTextListener(this);

        kamusHelper = new KamusHelper(this);
        searchAdapter = new SearchAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);


        lang_selection = "Eng";
        getData(lang_selection, "");
    }

    private void getData(String selection, String search) {
        try {
            kamusHelper.open();
            if (search.isEmpty()) {
                list = kamusHelper.getAllData(selection);
            } else {
                list = kamusHelper.getDataByName(search, selection);
            }

            String title = null;
            String hint = null;
            if (selection == "Eng") {
                title   = getResources().getString(R.string.eng_to_ind);
                hint    = getResources().getString(R.string.search_keyword);
            } else {
                title = getResources().getString(R.string.ind_to_eng);
                hint    = getResources().getString(R.string.cari_kosakata);
            }
            getSupportActionBar().setSubtitle(title);
            searchView.setQueryHint(hint);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }
        searchAdapter.replaceAll(list);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_english_to_indo) {
            lang_selection = "Eng";
            getData(lang_selection, "");
        } else if (id == R.id.nav_indo_to_english) {
            lang_selection = "Ind";
            getData(lang_selection, "");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String keyword) {
        getData(lang_selection, keyword);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String keyword) {
        getData(lang_selection, keyword);
        return false;
    }
}
