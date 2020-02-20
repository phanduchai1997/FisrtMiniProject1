package com.example.fisrtminiproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fisrtminiproject.R;
import com.example.fisrtminiproject.listener.OnClickItem;
import com.example.fisrtminiproject.model.ImageWallPager;

import java.util.ArrayList;
import java.util.List;

public class ImageWallPaperAdapter extends RecyclerView.Adapter<ImageWallPaperAdapter.ViewHolder> {
    private Context mContext;
    private List<ImageWallPager> listImage;
    private OnClickItem onClickListener;

    public ImageWallPaperAdapter(Context mContext, List<ImageWallPager> listImage, OnClickItem onClickListener) {
        this.mContext = mContext;
        this.listImage = listImage;
        this.onClickListener = onClickListener;
    }

    public void setListImage(List<ImageWallPager> listImage) {
        if (listImage != null) {
            this.listImage.clear();
            this.listImage.addAll(listImage);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageWallPager imageWallPager = listImage.get(position);
        Glide.with(mContext).load(imageWallPager.getImage())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cv_image);
        }
    }
}
