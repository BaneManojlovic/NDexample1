package com.bitnovisad.ndexample1.tutorial.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitnovisad.ndexample1.R;
import com.bitnovisad.ndexample1.tutorial.adapter.SlideAdapter;

import me.relex.circleindicator.CircleIndicator;

public class TutorialFragment extends Fragment {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;
    private CircleIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tutorial_fragment, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.vp_pager);
        indicator = (CircleIndicator) v.findViewById(R.id.ci_indicator);

        myAdapter = new SlideAdapter(getActivity());
        viewPager.setAdapter(myAdapter);
        indicator.setViewPager(viewPager);
        return v;
    }
}
