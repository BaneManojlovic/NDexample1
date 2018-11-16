package com.bitnovisad.ndexample1.gallery.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitnovisad.ndexample1.R;
import com.bumptech.glide.Glide;

public class ImageDetailsFragment extends Fragment {

    private ImageButton btnCloseImage;
    private View view;
    private ImageView imageView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_image_details, container, false);

        imageView = (ImageView) view.findViewById(R.id.iv_detail_image);

        //UNPACK OUR DATA FROM OUR BUNDLE
        String imageRes = this.getArguments().getString("NAME_KEY").toString();
        Glide.with(this)
                .load(imageRes)
                .into(imageView);

        //button for closing fragment ImageDetailsFragment
        btnCloseImage = (ImageButton) view.findViewById(R.id.btn_close_details);
        btnCloseImage.setOnClickListener(v -> {
          //  Toast.makeText(getActivity(), "Clossing image", Toast.LENGTH_SHORT).show();
            getFragmentManager().popBackStack();
        });

        return view;
    }
}
