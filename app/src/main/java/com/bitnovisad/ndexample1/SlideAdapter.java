package com.bitnovisad.ndexample1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    //List of images for tutorial
    public int[] listImages = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6
    };

    //List of intro text explenationes
    public String[] introTitles = {
            "Meet the future champion of Serbia in soccer!!!",
            "Look at the characteristics of your favorite players",
            "Browse all of the latest sports news from around the world",
            "Follow the results table and see the success of the team",
            "Pick Home colour theme for app, when team play on home ground",
            "Pick Away colour theme for app, when team play as guest"
    };

    //List of numbers
    public String[] slideNumbers = {"1", "2", "3", "4", "5", "6"};


    public SlideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return listImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);
        LinearLayout layoutSlide = (LinearLayout) view.findViewById(R.id.ll_slide_layout);
        TextView textIntro = (TextView) view.findViewById(R.id.tv_intro);
        ImageView slideImage = (ImageView) view.findViewById(R.id.iv_slide_image);
        TextView textCounter = (TextView) view.findViewById(R.id.tv_counter);

        textIntro.setText(introTitles[position]);
        slideImage.setImageResource(listImages[position]);
        textCounter.setText(slideNumbers[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
