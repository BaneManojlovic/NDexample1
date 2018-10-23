package com.bitnovisad.ndexample1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SwitchPreference siwtchHomeTheme;
    private SwitchPreference siwtchAwayTheme;
    private SwitchPreference siwtchThirdTheme;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            getActivity().setTheme(R.style.AppAwayTheme);
        }
        else
            getActivity().setTheme(R.style.AppTheme);

        View v = super.onCreateView(inflater, container, savedInstanceState);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        v.setPadding(5, 5, 5, 5);

        siwtchHomeTheme = (SwitchPreference) findPreference("switch_preference_home");
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            siwtchHomeTheme.setChecked(true);
        }
        siwtchHomeTheme.setOnPreferenceChangeListener(new SwitchPreference.OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isHomeOn = (boolean) newValue;
                if(isHomeOn == true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
                return true;
            }
        });

        siwtchAwayTheme = (SwitchPreference) findPreference("switch_preference_away");
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            siwtchHomeTheme.setChecked(false);
        }
        siwtchAwayTheme.setOnPreferenceChangeListener(new SwitchPreference.OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isAwayOn = (boolean) newValue;
                if(isAwayOn == true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
                return true;
            }
        });

        siwtchThirdTheme = (SwitchPreference) findPreference("switch_preference_third");
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            siwtchHomeTheme.setChecked(false);
        }
        siwtchThirdTheme.setOnPreferenceChangeListener(new SwitchPreference.OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isThirdOn = (boolean) newValue;
                if(isThirdOn == true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
                return true;
            }
        });


        return v;
    }

    //restart app in order to implement theme changes
    public void restartApp(){
        Intent intent = getActivity().getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
        startActivity(intent);
        getActivity().finish();
    }
}
