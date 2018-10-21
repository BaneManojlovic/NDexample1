package com.bitnovisad.ndexample1.news_list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.news_list.model.NewsItem;
import com.bitnovisad.ndexample1.players_list.model.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<NewsItem> listItems;
    private Context context;

    public NewsListAdapter(List<NewsItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {

        final NewsItem newsItem = listItems.get(position);
        holder.articleTitle.setText(newsItem.getTitle());
        holder.articleDate.setText(newsItem.getPublishedDate());

        //call Picasso to get image url
        Picasso.with(context)
                .load(newsItem.getUrlToImage())
                .resize(400, 400)
                .centerCrop()
                .into(holder.imageViewArticle);


        // final Player listItem = listItems.get(position);
        //        holder.textViewPlayerName.setText(listItem.getPlayerName());
        //        holder.textViewPlayerPosition.setText(listItem.getPlayerPosition());
        //
        //        //call Picasso to get image url
        //        Picasso.with(context)
        //                .load(listItem.getPlayerImageUrl())
        //                .resize(400, 400)
        //                .centerCrop()
        //                .transform(new RoundedCornersTransformation(35, 10))
        //                .into(holder.imageViewPlayer);
        //
        //        //handling onclick on items in list
        //        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Toast.makeText(context, listItem.getPlayerName(), Toast.LENGTH_LONG).show();
        //
        //                //sending data to player details screen - activity
        //                Intent intent = new Intent(context, PlayerDetailsActivity.class);
        //                intent.putExtra("image_url", listItem.getPlayerImageUrl());
        //                intent.putExtra("ply_name", listItem.getPlayerName());
        //                intent.putExtra("ply_position", listItem.getPlayerPosition());
        //                //added because of bug on Android 6.0. Here I am calling startActivity() from outside of an Activity.
        //                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //                context.startActivity(intent);
        //
        //            }
        //        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView articleTitle;
        public TextView articleDate;
        public ImageView imageViewArticle;

        public ViewHolder(View itemView) {
            super(itemView);

            articleTitle = (TextView) itemView.findViewById(R.id.articleTitle);
            articleDate = (TextView) itemView.findViewById(R.id.articleDate);
            imageViewArticle = (ImageView) itemView.findViewById(R.id.articleImage);
        }
    }
}
