package com.bitnovisad.ndexample1.players_list.view;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.players_list.adapter.PlayersListAdapter;
import com.bitnovisad.ndexample1.players_list.model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayersListFragment extends Fragment implements IntPlayersListFragment{

    private static final String URL_DATA = "https://raw.githubusercontent.com/BITNoviSad/fccementdata/master/mnu_players_modified.json";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Player> listItems;
    View v;

    public PlayersListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initialize View object
        v = inflater.inflate(R.layout.players_list_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewPlayersList);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)); //adding divider line under every item in players list
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        //loading players data
        loadRecyclerViewPlayersData();

        return v;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    //method for loading recyclerview data form server
    public void loadRecyclerViewPlayersData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("players");
                            for(int i=0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                Player player = new Player(
                                        obj.getString("plname"),
                                        obj.getString("possition"),
                                        obj.getString("image")
                                );
                                listItems.add(player);
                            }
                            adapter = new PlayersListAdapter(listItems, getActivity().getApplicationContext());
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

}