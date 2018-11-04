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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    //valid date format
    final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
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
        //     holder.articleDate.setText(newsItem.getPublishedDate());

        if(isDateValid(newsItem.getPublishedDate()) == true) {
            try {
                holder.articleDate.setText(parseDateToMyFormat(newsItem.getPublishedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                holder.articleDate.setText(parseDateToMyFormatUs(newsItem.getPublishedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

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

    //method for parsing date into desired format
    public String parseDateToMyFormat(String time) throws ParseException {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String outputPattern;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.UK);
        Date date;
        String str = null;
        date = inputFormat.parse(time);
        outputPattern = "dd.MM.YYYY.";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        str = outputFormat.format(date);
        return str.toUpperCase();
    }

    //method for parsing date into desired format US
    public String parseDateToMyFormatUs(String time) throws ParseException {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String outputPattern;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.UK);
        Date date;
        String str = null;
        date = inputFormat.parse(time);
        outputPattern = "dd.MM.YYYY.";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        str = outputFormat.format(date);
        return str.toUpperCase();
    }

    //method for check is date in valid format UK or US
    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
