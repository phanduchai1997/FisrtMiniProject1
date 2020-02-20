package com.example.fisrtminiproject.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fisrtminiproject.fragment.FullimageFragment;
import com.example.fisrtminiproject.model.Image;

import java.util.List;
import java.util.Objects;

public class FullimagePagerAdapter extends FragmentPagerAdapter {
    private List<Image> imageList;
    private Context mContext;
    private int posision;

    public FullimagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FullimagePagerAdapter(FragmentManager fm, List<Image> imageList, Context mContext, int posision) {
        super(fm);
        this.imageList = imageList;
        this.mContext = mContext;
        this.posision = posision;
    }

    @Override
    public Fragment getItem(int position) {
        return new FullimageFragment(imageList,position+this.posision);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}
