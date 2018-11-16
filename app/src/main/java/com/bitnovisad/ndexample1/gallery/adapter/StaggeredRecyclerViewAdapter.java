package com.bitnovisad.ndexample1.gallery.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.gallery.view.ImageDetailsFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder>  {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public StaggeredRecyclerViewAdapter(Context mContext, ArrayList<String> mNames, ArrayList<String> mImageUrls) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mImageUrls.get(position))
                .apply(requestOptions)
                .into(holder.image);

        holder.name.setText(mNames.get(position));

        //click on item to send data to fragment ImageDetailsFragment and to open it
        holder.image.setOnClickListener(v -> {
           // Toast.makeText(mContext, "Pressed", Toast.LENGTH_SHORT).show();

            //sends data for image to fragment ImageDetailsFragment in bundle
            Bundle bundle = new Bundle();
            bundle.putString("NAME_KEY", mImageUrls.get(position));
            //here I am calling to open new fragment from outside of the new fragment
            Fragment fragment = new ImageDetailsFragment();
            fragment.setArguments(bundle);
            FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        });
    }


    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.iv_image);
            name = (TextView) itemView.findViewById(R.id.tv_image_text);
        }
    }
}
