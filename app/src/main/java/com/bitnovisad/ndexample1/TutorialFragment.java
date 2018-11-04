package com.bitnovisad.ndexample1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutorialFragment extends Fragment {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tutorial_fragment, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.vp_pager);
        myAdapter = new SlideAdapter(getActivity());
        viewPager.setAdapter(myAdapter);

        return v;
    }
}
