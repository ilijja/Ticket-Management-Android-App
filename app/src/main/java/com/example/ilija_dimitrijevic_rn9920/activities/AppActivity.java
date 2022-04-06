package com.example.ilija_dimitrijevic_rn9920.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.ilija_dimitrijevic_rn9920.viewpager.PagerAdapter;
import com.example.ilija_dimitrijevic_rn9920.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        this.init();
    }

    private void init(){
        this.initViewPager();
        this.initNavigation();
    }

    private void initViewPager(){
        viewPager = this.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager()));
    }

    private void initNavigation(){

        this.bottomNavigationView = this.findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navigation_1){
                viewPager.setCurrentItem(PagerAdapter.FRAGMENT_1,false);
            }else if(item.getItemId() == R.id.navigation_2){
                viewPager.setCurrentItem(PagerAdapter.FRAGMENT_2,false);
            }else if(item.getItemId() == R.id.navigation_3){
                viewPager.setCurrentItem(PagerAdapter.FRAGMENT_3,false);
            }else if(item.getItemId() == R.id.navigation_4){
                viewPager.setCurrentItem(PagerAdapter.FRAGMENT_4,false);
            }
            return true;
        });

    }
}
