package com.example.ilija_dimitrijevic_rn9920.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ilija_dimitrijevic_rn9920.fragments.AddTicketFragment;
import com.example.ilija_dimitrijevic_rn9920.fragments.StatisticsFragment;
import com.example.ilija_dimitrijevic_rn9920.fragments.TicketsFragment;
import com.example.ilija_dimitrijevic_rn9920.fragments.UserProfileFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 4;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;
    public static final int FRAGMENT_4 = 3;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position==FRAGMENT_1){
            fragment = new StatisticsFragment();
        }else if(position==FRAGMENT_2){
            fragment = new AddTicketFragment();
        }else if(position==FRAGMENT_3){
            fragment = new TicketsFragment();
        }else if (position==FRAGMENT_4){
            fragment = new UserProfileFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==FRAGMENT_1) return "1";
        else if(position==FRAGMENT_2) return "2";
        else if(position==FRAGMENT_3) return "3";
        return "0";
    }
}
