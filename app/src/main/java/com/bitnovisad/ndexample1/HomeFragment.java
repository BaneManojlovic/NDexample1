package com.bitnovisad.ndexample1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private ImageButton btnSendEmail;
    private ImageButton btnOpenFb;
    private ImageButton btnCallClub;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //initialize View object
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        //ImageButton for sending email
        btnSendEmail = (ImageButton) v.findViewById(R.id.emailBtn);
        ImageButton.OnClickListener listenerEmail = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSend(v);
            }
        };

        //ImageButton for opening Facebook page
        btnOpenFb = (ImageButton) v.findViewById(R.id.facebookBtn);
        ImageButton.OnClickListener listenerFb = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFbClick(v);
            }
        };

        //ImageButton for starting a telephone call
        btnCallClub = (ImageButton) v.findViewById(R.id.telephoneBtn);
        ImageButton.OnClickListener listenerCall = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCall(v);
            }
        };


        btnSendEmail.setOnClickListener(listenerEmail);
        btnCallClub.setOnClickListener(listenerCall);
        btnOpenFb.setOnClickListener(listenerFb);
        return v;
    }


    public void onClickSend(View view) {

        if (isConnectedToInternet()) {
            String[] primalac = {"bane1manojlovic@gmail.com"};
            Intent sentIntent = new Intent(Intent.ACTION_SEND);
            sentIntent.setType("text/plain");
            sentIntent.putExtra(Intent.EXTRA_EMAIL, primalac);
            sentIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
            sentIntent.putExtra(Intent.EXTRA_TEXT, "Body of Email");
            startActivity(sentIntent);
        } else {
            Toast.makeText(getActivity(), "NO INTERNET CONNECTION!", Toast.LENGTH_LONG).show();
        }

    }

    public void onFbClick(View v) {
        if (isConnectedToInternet()) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://FK-Cement-Beocin/360493994053052"));
                startActivity(intent);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/category/Sports-Team/Fk-cement-Beocin-360493994053052/")));
            }
        } else {
            Toast.makeText(getActivity(), "NO INTERNET CONNECTION!", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickCall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:123456789"));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
            return;
        }
        startActivity(callIntent);
    }

    //proverava da li postoji konekcija sa internetom
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