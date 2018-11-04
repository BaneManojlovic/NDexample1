package com.bitnovisad.ndexample1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PlayerDetailsActivity extends AppCompatActivity {

    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details_fragment);

        //toast message to handle if there is no internet connection
        if(isNetworkConnected() == false){
            Toast.makeText(this, R.string.no_internet_msg, Toast.LENGTH_LONG).show();

        }

        //call for method that give data for player details screen
        getIncomingIntent();

    }

    //method for catching incoming intent
    public void getIncomingIntent() {
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("ply_name")
                && getIntent().hasExtra("ply_position")) {

            String imageUrl = getIntent().getStringExtra("image_url");
            String playerName = getIntent().getStringExtra("ply_name");
            String playerPosition = getIntent().getStringExtra("ply_position");

            setImage(imageUrl, playerName, playerPosition);

        }
    }

    //seting values from intent into layout
    public void setImage(String imageUrl, String playerName, String playerPosition) {

        ImageView image = (ImageView) findViewById(R.id.iv_player_details);
        Picasso.with(this)
                .load(imageUrl)
                .resize(400, 400)
                .centerCrop()
                .into(image);

        TextView namePly = (TextView) findViewById(R.id.tv_player_details_player_name);
        namePly.setText(playerName);

        TextView positionPly = (TextView) findViewById(R.id.tv_player_details_player_position);
        positionPly.setText(playerPosition);
    }
}
