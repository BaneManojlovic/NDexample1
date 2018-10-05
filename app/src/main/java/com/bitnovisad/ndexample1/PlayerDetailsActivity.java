package com.bitnovisad.ndexample1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlayerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details_fragment);

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

        TextView namePly = (TextView) findViewById(R.id.playerDetailsPlayerName);
        namePly.setText(playerName);

        TextView positionPly = (TextView) findViewById(R.id.playerDetailsPlayerPosition);
        positionPly.setText(playerPosition);

        ImageView image = (ImageView) findViewById(R.id.playerDetailsImageView);
        Picasso.with(this)
                .load(imageUrl)
                .resize(400, 400)
                .centerCrop()
                .into(image);
    }
}
