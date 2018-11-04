package com.bitnovisad.ndexample1.news_list.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.news_list.adapter.NewsListAdapter;
import com.bitnovisad.ndexample1.news_list.model.NewsItem;
import com.bitnovisad.ndexample1.players_list.adapter.PlayersListAdapter;
import com.bitnovisad.ndexample1.players_list.model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment {

    private static final String URL_DATA = "https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=89cecfc4fed94fbe9ad1f01407726463";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NewsItem> newsItems;
    View v;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //toast message to handle if there is no internet connection
        if(isNetworkConnected() == false){
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        //initialize View object
        v = inflater.inflate(R.layout.news_list_fragment, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.pb_news_list);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_news_list);
        recyclerView.setHasFixedSize(true);
       // recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)); //adding divider line under every item in players list
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsItems = new ArrayList<>();

        //loading players data
        loadRecyclerViewPlayersData();

        return v;
    }

    //method for loading recyclerview data form server
    public void loadRecyclerViewPlayersData() {

        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("articles");

                            for(int i=0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                NewsItem news = new NewsItem(
                                        obj.getString("urlToImage"),
                                        obj.getString("title"),
                                        obj.getString("description"),
                                        obj.getString("content"),
                                        obj.getString("author"),
                                        obj.getString("publishedAt")
                                );
                                newsItems.add(news);
                            }
                            adapter = new NewsListAdapter(newsItems, getActivity().getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueve = Volley.newRequestQueue(getActivity());
        requestQueve.add(stringRequest);

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
