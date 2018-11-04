package com.bitnovisad.ndexample1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.bitnovisad.ndexample1.home.HomeFragment;
import com.bitnovisad.ndexample1.news_list.view.NewsListFragment;
import com.bitnovisad.ndexample1.players_list.view.PlayersListFragment;
import com.bitnovisad.ndexample1.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    public static Toolbar toolbar;
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

        //pre nego sto se klikne na bilo koje dugme, ovde moramo da proverimo koja boja je sacuvana u preferencama
        if(getColor() != getResources().getColor(R.color.color_primary)){
            toolbar.setBackgroundColor(getColor());
            navigationView.setBackgroundColor(getColor());
            setToolbarColor();
        }

        //setting up hamburger button for navigation drawer
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //opening home fragment first after starting application
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        //handling navigation on navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home_fragment:
                        item.setChecked(true);
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.team:
                        item.setChecked(true);
                        selectedFragment = new PlayersListFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.news:
                        item.setChecked(true);
                        selectedFragment = new NewsListFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.table:
                        item.setChecked(true);
                        selectedFragment = new TableFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.settings:
                        item.setChecked(true);
                        selectedFragment = new SettingsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                }
                return true; //bilo je false
            }
        });
    }

    //method for handling click on hamburger button on toolbar fo opening navigation drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //closes navigation drawer on pressed BACK button
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //za setovanje boje toolbara
    public void setToolbarColor(){
        toolbar.setBackgroundColor(getColor());
    }

    //metod za dohvatanje boje iz preferenci - tj iz SharedPreferences
    public int getColor(){
        SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        int selectedColor = mSharedPreferences.getInt("color", getResources().getColor(R.color.color_primary));
        return selectedColor;
    }
}
