package com.bitnovisad.ndexample1.players_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnovisad.ndexample1.PlayerDetailsFragment;
import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.players_list.model.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.ViewHolder> {

    private List<Player> listItems;
    private Context context;

    public PlayersListAdapter(List<Player> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.players_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Player listItem = listItems.get(position);
        holder.textViewPlayerName.setText(listItem.getPlayerName());
        holder.textViewPlayerPosition.setText(listItem.getPlayerPosition());

        //call Picasso to get image url
        Picasso.with(context)
                .load(listItem.getPlayerImageUrl())
                .resize(400, 400)
                .centerCrop()
                .transform(new RoundedCornersTransformation(35, 10))
                .into(holder.imageViewPlayer);

        //handling onclick on items in list
        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            Fragment selectedFragment = null;

            @Override
            public void onClick(View view) {
                Toast.makeText(context, listItem.getPlayerName(), Toast.LENGTH_LONG).show();

                //TODO - ubaci za otvaranje podataka na PlayerDetails ekranu
                Intent intent = new Intent(context, PlayerDetailsFragment.class);
                intent.putExtra("image_url", listItem.getPlayerImageUrl());
                intent.putExtra("ply_name", listItem.getPlayerName());
                intent.putExtra("ply_position", listItem.getPlayerPosition());
                context.startActivity(intent);

                //TODO- resi otvaranje fragmenta!!!
//                        selectedFragment = new PlayerDetailsFragment();
//                        context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                //FragmentManager fm = getFragmentManager();
                //    FragmentTransaction ft = fm.beginTransaction();
                //    FragmentGreen llf = new FragmentGreen();
                //    ft.replace(R.id.listFragment, llf);
                //    ft.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewPlayerName;
        public TextView textViewPlayerPosition;
        public ImageView imageViewPlayer;
        public LinearLayout linearLayoutItem;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewPlayerName = (TextView) itemView.findViewById(R.id.playerName);
            textViewPlayerPosition = (TextView) itemView.findViewById(R.id.playerPosition);
            imageViewPlayer = (ImageView) itemView.findViewById(R.id.playerImage);
            linearLayoutItem = (LinearLayout) itemView.findViewById(R.id.linearLayoutItem);
        }
    }
}
