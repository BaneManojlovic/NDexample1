package com.bitnovisad.ndexample1.gallery.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.gallery.adapter.StaggeredRecyclerViewAdapter;
import com.bitnovisad.ndexample1.players_list.view.PlayersListFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final int NUM_COLUMNS = 2;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //toast message to handle if there is no internet connection
        if(isNetworkConnected() == false){
            Toast.makeText(getActivity(), R.string.no_internet_msg, Toast.LENGTH_LONG).show();
        }

        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //for preventing doubleing of data for staggered recyclerview grid!!!
        if(mImageUrls != null && mNames != null) {
            mImageUrls.clear();
            mNames.clear();
        } else {
            mImageUrls = new ArrayList<>();
            mNames = new ArrayList<>();
        }

        initImageBitmaps();
        return view;
    }

    private void initImageBitmaps() {

        mImageUrls.add("https://secure.cache.images.core.optasports.com/soccer/venues/600x450/5595.jpg");
        mNames.add("Slika prva");

        mImageUrls.add("http://www.srbijafudbal.com/igraci/cement/katanic.jpg");
        mNames.add("Slika druga");

        mImageUrls.add("http://www.fkvojvodina.rs/wp-content/uploads/2013/09/obrazac-za-slike-vesti8.jpg");
        mNames.add("Slika treca");

        mImageUrls.add("https://farm5.static.flickr.com/4543/26882892929_3fa1bf8efc_b.jpg");
        mNames.add("Slika cetvrta");

        mImageUrls.add("https://c1.staticflickr.com/5/4581/37771422945_da05faed35_b.jpg");
        mNames.add("Slika peta");

        mImageUrls.add("https://farm5.static.flickr.com/4545/38602838636_24d6840e63_b.jpg");
        mNames.add("Slika sesta");

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxPL7-L8XExI2WTAS8AKA5xIDkmwZ5TdzWTIs53XxRqsdSrV64");
        mNames.add("Slika sedma");

        mImageUrls.add("http://2.bp.blogspot.com/-g_YaHdsR-uo/UGChw55tU_I/AAAAAAAADBQ/YOkAJLWX3cU/s1600/beocin+022.jpg");
        mNames.add("Slika osma");

        mImageUrls.add("http://static.rtv.rs/slike/2015/03/28/cement,-beocin_660x330.jpg");
        mNames.add("Slika deveta");

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT90ptUVPjpPxvV76sjd9JRNZV_HZ3OkbZqwFM-QL87Ab37Vs5J");
        mNames.add("Slika deseta");

        mImageUrls.add("http://www.fkvojvodina.rs/wp-content/uploads/2013/09/obrazac-za-slike-vesti12.jpg");
        mNames.add("Slika jedanaesta");

        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.rv_grid_layout);

        StaggeredRecyclerViewAdapter stRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(getActivity(), mNames, mImageUrls);

        StaggeredGridLayoutManager stGridLayoutMAnager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(stGridLayoutMAnager);
        recyclerView.setAdapter(stRecyclerViewAdapter);
    }

    //checking is network awailable
    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;
        }catch (NullPointerException e){
            return false;
        }
    }
}
