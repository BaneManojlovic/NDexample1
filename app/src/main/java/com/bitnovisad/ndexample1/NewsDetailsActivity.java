package com.bitnovisad.ndexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class NewsDetailsActivity extends AppCompatActivity {

    //valid date format
    final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

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

            //for parsing date into desired format
//            DateFormat outputFormat = new SimpleDateFormat("dd.MM.YYYY.");
//            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK);
//           // DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
//            //String inputText = "2012-11-17T00:00:00.000-05:00";
//            String inputText = newsPublishingDate.toString();
//            Date date = inputFormat.parse(inputText);
//            String outputText = outputFormat.format(date);

            if(isDateValid(newsPublishingDate) == true) {
                try {
                    setImage(imageUrl, newsTitle, newsDescription, newsContent, newsAuthor, parseDateToMyFormat(newsPublishingDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    setImage(imageUrl, newsTitle, newsDescription, newsContent, newsAuthor, parseDateToMyFormatUs(newsPublishingDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            // setImage(imageUrl, newsTitle, newsDescription, newsContent, newsAuthor, newsPublishingDate);
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

    //method for parsing date into desired format UK
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
