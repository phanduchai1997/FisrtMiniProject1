package com.example.fisrtminiproject.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fisrtminiproject.fragment.ImageFragment;
import com.example.fisrtminiproject.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class PagerAdapter extends FragmentPagerAdapter {
    private String title[] = new String[]{"BackGround","Mine"};
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ImageFragment.getInstance();
            case 1:
                return MineFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
