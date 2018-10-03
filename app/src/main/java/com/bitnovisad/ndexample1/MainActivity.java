package com.bitnovisad.ndexample1;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for setting custom toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);


        //setting up hamburger button fo nav. draw.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()){
                    case R.id.home_fragment:
                        item.setChecked(true);
                        displayMessage("Opening Home...");
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.news:
                        item.setChecked(true);
                        displayMessage("Opening News...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.team:
                        item.setChecked(true);
                        displayMessage("Opening Team...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.table:
                        item.setChecked(true);
                        displayMessage("Opening Table...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.history:
                        item.setChecked(true);
                        displayMessage("Opening History...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.settings:
                        item.setChecked(true);
                        displayMessage("Opening Settings...");
                        drawerLayout.closeDrawers();
                        return true;
                }
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return false;
            }
        });
    }


    //method for showing message while tapping on item inside of menu on nav. draw.
    public void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //method for handlig click on hamburger button on toolbar fo opening nav. draw.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
