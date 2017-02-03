package com.pyatkovskaya.xml;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.pyatkovskaya.xml.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = (ListView)findViewById(R.id.list);
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(Person.generateMe());
        persons.add(Person.generateMe());
        persons.add(Person.generateMe());
        persons.add(Person.generateMe());
        list.setAdapter(new PeopleAdapter(this, persons));



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


    private void generate(){
        new OneButtonDialog(this, getString(R.string.generate_list), OneButtonDialog.HAS_EDIT_INT_WITH_HINT+getString(R.string.count_hint), R.drawable.generate, new OneButtonDialog.OKListener() {
            @Override
            public void onOKpressed(String userInput) {
                try {
                    int count = Integer.parseInt(userInput);
                    ArrayList<Person> persons = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        persons.add(Person.generateMe());
                    }
                    list.setAdapter(new PeopleAdapter(MainActivity.this, persons));
                }catch (NumberFormatException nfe){}
            }
        });
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(id == R.id.generateButton){
            generate();
        } else if(id==R.id.newButton){
            list.setAdapter(new PeopleAdapter(MainActivity.this, new ArrayList<Person>()));
        } else if(id==R.id.addButton){
            Intent editActivity = new Intent(MainActivity.this, EditPersonActivity.class);
            startActivity(editActivity);
        }

        return true;
    }
}
