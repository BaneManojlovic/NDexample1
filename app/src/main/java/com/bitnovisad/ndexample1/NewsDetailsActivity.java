package com.bitnovisad.ndexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_fragment);

        //call for method that give data for player details screen
        getIncomingIntent();
    }


    //method for catching incoming intent
    public void getIncomingIntent() {
//        if (getIntent().hasExtra("urlToImage")
//                && getIntent().hasExtra("title")
//                && getIntent().hasExtra("description")) {
//
//            String imageUrl = getIntent().getStringExtra("urlToImage");
//            String newsTitle = getIntent().getStringExtra("title");
//            String newsDescription = getIntent().getStringExtra("description");
//
//            setImage(imageUrl, newsTitle, newsDescription);
//
//        }

        if (getIntent().hasExtra("urlToImage")
                && getIntent().hasExtra("title")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("content")
                && getIntent().hasExtra("author")
                && getIntent().hasExtra("publishedAt")) {

            String imageUrl = getIntent().getStringExtra("urlToImage");
            String newsTitle = getIntent().getStringExtra("title");
            String newsDescription = getIntent().getStringExtra("description");
            String newsContent = getIntent().getStringExtra("content");
            String newsAuthor = getIntent().getStringExtra("author");
            String newsPublishingDate = getIntent().getStringExtra("publishedAt");

            setImage(imageUrl, newsTitle, newsDescription, newsContent, newsAuthor, newsPublishingDate);

        }
    }

    private void setImage(String imageUrl, String newsTitle, String newsDescription, String newsContent, String newsAuthor, String newsPublishingDate) {

        ImageView image = (ImageView) findViewById(R.id.iv_news_details);
        Picasso.with(this)
                .load(imageUrl)
                .resize(400, 400)
                .centerCrop()
                .into(image);

        TextView newsT = (TextView) findViewById(R.id.tv_news_details_title);
        newsT.setText(newsTitle);

        TextView newsDesc = (TextView) findViewById(R.id.tv_news_details_description);
        newsDesc.setText(newsDescription);

        TextView newsCont = (TextView) findViewById(R.id.tv_news_details_content);
        newsCont.setText(newsContent);
        //newsCont.setMovementMethod(new ScrollingMovementMethod());

        TextView newsAuth = (TextView) findViewById(R.id.tv_news_author);
        newsAuth.setText(newsAuthor);

        TextView newsPubDat = (TextView) findViewById(R.id.tv_news_details_date);
        newsPubDat.setText(newsPublishingDate);

    }

    //seting values from intent into layout
//    public void setImage(String imageUrl, String newsTitle, String newsDescription) {
//
//        TextView newsT = (TextView) findViewById(R.id.tv_news_details_title);
//        newsT.setText(newsTitle);
//
//        TextView newsDesc = (TextView) findViewById(R.id.tv_news_details_description);
//        newsDesc.setText(newsDescription);
//
//        ImageView image = (ImageView) findViewById(R.id.iv_news_details);
//        Picasso.with(this)
//                .load(imageUrl)
//                .resize(400, 400)
//                .centerCrop()
//                .into(image);
//    }

}
