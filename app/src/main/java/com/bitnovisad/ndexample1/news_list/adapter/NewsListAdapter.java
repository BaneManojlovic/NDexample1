package com.bitnovisad.ndexample1.news_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnovisad.ndexample1.NewsDetailsActivity;
import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.news_list.model.NewsItem;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsItem newsItem = listItems.get(position);
        holder.articleTitle.setText(newsItem.getTitle());
        holder.articleDate.setText(newsItem.getPublishedDate());

        //call Picasso to get image url
        Picasso.with(context)
                .load(newsItem.getUrlToImage())
                .resize(400, 400)
                .centerCrop()
                .into(holder.imageViewArticle);


        //handling onclick on items in list

        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, newsItem.getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("urlToImage", newsItem.getUrlToImage());
                intent.putExtra("title", newsItem.getTitle());
                intent.putExtra("description", newsItem.getDescription());
                intent.putExtra("content", newsItem.getContent());
                intent.putExtra("author", newsItem.getAuthor());
                intent.putExtra("publishedAt", newsItem.getPublishedDate());

                context.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView articleTitle;
        public TextView articleDate;
        public ImageView imageViewArticle;
        public LinearLayout linearLayoutItem;

        public ViewHolder(View itemView) {
            super(itemView);

            articleTitle = (TextView) itemView.findViewById(R.id.article_title);
            articleDate = (TextView) itemView.findViewById(R.id.article_date);
            imageViewArticle = (ImageView) itemView.findViewById(R.id.article_image);
            linearLayoutItem = (LinearLayout) itemView.findViewById(R.id.news_layout_item);
        }
    }
}
