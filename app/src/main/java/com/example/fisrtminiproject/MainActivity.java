package com.example.fisrtminiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.example.fisrtminiproject.adapter.ImageAdapter;
import com.example.fisrtminiproject.adapter.PagerAdapter;
import com.example.fisrtminiproject.fragment.ImageFragment;
import com.example.fisrtminiproject.fragment.MineFragment;
import com.example.fisrtminiproject.listener.OnClickItem;
import com.example.fisrtminiproject.model.Image;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;
//    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.vp_main);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
//        tabLayout = findViewById(R.id.tab);
//        tabLayout.setupWithViewPager(viewPager);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nv_iamge:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.nv_mine:
                        viewPager.setCurrentItem(1);
                        return true;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }else
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_image_black_24dp);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person_black_24dp);
    }
}
