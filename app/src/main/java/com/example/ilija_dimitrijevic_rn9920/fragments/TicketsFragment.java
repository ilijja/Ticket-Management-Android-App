package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ilija_dimitrijevic_rn9920.viewpager.TabsPagerAdapter;
import com.example.ilija_dimitrijevic_rn9920.R;
import com.google.android.material.tabs.TabLayout;

public class TicketsFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    public TicketsFragment() {
        super(R.layout.fragment_tickets);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.init();
    }

    private void init(){
        this.initViewPager();
        this.initTabs();
    }

    private void initViewPager(){
        viewPager = getView().findViewById(R.id.tabViewPager);
        viewPager.setAdapter(new TabsPagerAdapter(this.getChildFragmentManager()));
    }

    private void initTabs(){
        this.tabLayout = getView().findViewById(R.id.tabLayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }


}
