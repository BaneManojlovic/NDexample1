package com.bitnovisad.ndexample1;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment implements IntSettingsFragment{

    private Switch switchHomeTheme;
    private Button btnTutorial;
    private TextView privacyPolicy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //predefine application theme
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            getActivity().setTheme(R.style.AppAwayTheme);
        }
        else
            getActivity().setTheme(R.style.AppTheme);

        //inflate view
        View v = inflater.inflate(R.layout.settings_fragment, container, false);

        switchHomeTheme = v.findViewById(R.id.theme_switch);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switchHomeTheme.setChecked(true);

        }
        switchHomeTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });

        //opens tutorial fragment
        btnTutorial = v.findViewById(R.id.btn_tutorial);
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });

        //opens privacy policy web page
        privacyPolicy = v.findViewById(R.id.tv_privacy_policy);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (isConnectedToInternet()) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bitnovisad.github.io/"));
                            startActivity(intent);
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bitnovisad.github.io/")));
                        }
                    } else {
                        Toast.makeText(getActivity(), "NO INTERNET CONNECTION!", Toast.LENGTH_LONG).show();
                    }

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

    //checking internet connection
    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
}