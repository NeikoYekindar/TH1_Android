package com.example.appglock.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appglock.MainActivity;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentStateAdapter {
    ArrayList<Fragment> arr;
    public AdapterViewPager(@NonNull MainActivity fragment, ArrayList<Fragment> arr ) {
        super(fragment);
        this.arr = arr;// show
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arr.get(position);
    } //thêm
    @Override
    public int getItemCount() {
        return arr.size();
    }// cập nhật size
}
