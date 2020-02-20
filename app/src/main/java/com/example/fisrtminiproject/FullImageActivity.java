package com.example.fisrtminiproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.fisrtminiproject.adapter.FullimagePagerAdapter;
import com.example.fisrtminiproject.model.Image;

import java.util.List;

public class FullImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Image> listFullImage;
    FullimagePagerAdapter adapter;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_full_image);
        viewPager = findViewById(R.id.vp_full_image);
        int pos = getIntent().getExtras().getInt("posision");
        listFullImage = (List<Image>) getIntent().getSerializableExtra("list");
        adapter = new FullimagePagerAdapter(fragmentManager,listFullImage,this,pos);
        viewPager.setAdapter(adapter);
//        addData();

    }

//    private void addData() {
//        ImageAPI.getService().getAllImage(page).enqueue(new Callback<List<Image>>() {
//            @Override
//            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
//                if (response.isSuccessful()) {
//                    List<Image> images = response.body();
//                    if (images != null) {
//                        listFullImage.clear();
//                        listFullImage.addAll(images);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Image>> call, Throwable t) {
//
//            }
//        });
//    }
}
