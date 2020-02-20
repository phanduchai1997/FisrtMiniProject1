package com.example.fisrtminiproject.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fisrtminiproject.FullImageActivity;
import com.example.fisrtminiproject.R;
import com.example.fisrtminiproject.adapter.ImageAdapter;
import com.example.fisrtminiproject.adapter.ImageWallPaperAdapter;
import com.example.fisrtminiproject.listener.OnClickItem;
import com.example.fisrtminiproject.model.Image;
import com.example.fisrtminiproject.model.ImageWallPager;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MineFragment extends Fragment implements OnClickItem {
    private static MineFragment INSTANCE;
    private List<ImageWallPager> listImages;
    private ImageWallPaperAdapter imageAdapter;
    private RecyclerView recyclerView;
    public static MineFragment getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MineFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_mine,container,false);
        recyclerView = view.findViewById(R.id.rv_mine);
        initview();
        return view;
    }

    private void initview() {
        listImages = new ArrayList<>();
        imageAdapter = new ImageWallPaperAdapter(getContext(),listImages,MineFragment.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }



    @Override
    public void onClickImage(int position) {
        Intent intent = new Intent(getContext(), FullImageActivity.class);
        intent.putExtra("posision",position);
        intent.putExtra("list", (Serializable) listImages);
        startActivity(intent);
    }



    public class GetImage extends AsyncTask<String, Void, List<ImageWallPager> >{

        @Override
        protected List<ImageWallPager> doInBackground(String... files) {
            String file = files[0];
            File file1 = new File(file);
            List<ImageWallPager> list = new ArrayList<>();
            if(file1.isDirectory()){
                File[] listFile = file1.listFiles();
                for (int i=0;i<listFile.length;i++){
                    ImageWallPager image = new ImageWallPager(listFile[i].getAbsoluteFile());
                    list.add(image);
                }
                return list;
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<ImageWallPager> imageWallPagers) {
            super.onPostExecute(imageWallPagers);
            imageAdapter.setListImage(imageWallPagers);

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        new GetImage().execute("data/data/com.example.fisrtminiproject/storageImages");
    }
}
