package com.example.appglock;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appglock.adapter.AdapterViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager2 paper2;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        paper2 = findViewById(R.id.paperview);
        bottomNavigationView = findViewById(R.id.buttonnav);
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new AlarmFragment());
        fragmentArrayList.add(new TimerFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        paper2.setAdapter(adapterViewPager);
        paper2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.time);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.alarm);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.timer);
                }
                super.onPageSelected(position);
            }

        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.time){
                    paper2.setCurrentItem(0);
                    return true;
                }
                if (item.getItemId() == R.id.alarm){
                    paper2.setCurrentItem(1);
                    return true;
                }if(item.getItemId() == R.id.timer){
                    paper2.setCurrentItem(2);
                    return true;
                }
                return false;
            }
        });

    }





}