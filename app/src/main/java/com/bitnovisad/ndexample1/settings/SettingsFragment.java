package com.bitnovisad.ndexample1.settings;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnovisad.ndexample1.MainActivity;
import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.TutorialFragment;


public class SettingsFragment extends Fragment implements IntSettingsFragment {

    Button btnHomeTheme, btnAwayTheme;
    private Button btnTutorial;
    private TextView privacyPolicy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflate view
        View v = inflater.inflate(R.layout.settings_fragment, container, false);
        MainActivity obj = new MainActivity();

        btnHomeTheme = (Button) v.findViewById(R.id.toggleButton);
        btnAwayTheme = (Button) v.findViewById(R.id.toggleButton2);

        btnHomeTheme.setOnClickListener((view) -> {
          //  Toast.makeText(getActivity(), "Blue pressed", Toast.LENGTH_SHORT).show();
            obj.toolbar.setBackgroundColor(getResources().getColor(R.color.color_blue));
            storeColor(getResources().getColor(R.color.color_blue));
//            getActivity().finish();
            restartApp();
        });

        btnAwayTheme.setOnClickListener((view) -> {
         //   Toast.makeText(getActivity(), "Green pressed", Toast.LENGTH_SHORT).show();
            obj.toolbar.setBackgroundColor(getResources().getColor(R.color.color_green));
            storeColor(getResources().getColor(R.color.color_green));
            restartApp();
        });

        //open tutorial fragment
        btnTutorial = v.findViewById(R.id.btn_tutorial);
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
                TutorialFragment nextFrag= new TutorialFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
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
                    Toast.makeText(getActivity(), R.string.no_internet_msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    //restart app in order to implement theme changes
    public void restartApp() {
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

    //metoda koja skladisti i cuva boju u SharedPreferences - tj. preferencama
    private void storeColor(int color) {
        SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("color", color);
        mEditor.apply();
    }
}