package com.example.ilija_dimitrijevic_rn9920.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ilija_dimitrijevic_rn9920.fragments.DoneFragment;
import com.example.ilija_dimitrijevic_rn9920.fragments.InProgressFragment;
import com.example.ilija_dimitrijevic_rn9920.fragments.ToDoFragment;

import timber.log.Timber;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private final int TAB_COUNT = 3;
    private static final int TAB_1 = 0;
    private static final int TAB_2 = 1;
    private static final int TAB_3 = 2;


    public TabsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Timber.e(String.valueOf(position));
        if(position==TAB_1){
            fragment = new ToDoFragment();
        }else if(position==TAB_2){
            fragment = new InProgressFragment();
        }else if(position==TAB_3){
            fragment = new DoneFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==TAB_1) return "ToDo";
        else if(position==TAB_2) return "In Progress";
        else if(position==TAB_3) return "Done";
        return "0";
    }
}


