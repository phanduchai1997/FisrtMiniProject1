package com.example.fisrtminiproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fisrtminiproject.R;
import com.example.fisrtminiproject.listener.OnClickItem;
import com.example.fisrtminiproject.model.Image;
import com.example.fisrtminiproject.util.Const;

import java.util.ArrayList;
import java.util.List;

import static com.example.fisrtminiproject.model.Image.LAYOUT_IMAGE;
import static com.example.fisrtminiproject.model.Image.LAYOUT_PROS;

public class ImageAdapter extends RecyclerView.Adapter {
    private List<Image> listImage= new ArrayList<>();
    private Context mContext;
    private OnClickItem onClickItem;

    public ImageAdapter(List<Image> listImage, Context mContext, OnClickItem onClickItem) {
        this.listImage = listImage;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType == LAYOUT_IMAGE) {
           View view = LayoutInflater.from(mContext).inflate(R.layout.layout_image, parent, false);
           return new ImageViewHolder(view);
       }
       else {
           View view1 = LayoutInflater.from(mContext).inflate(R.layout.layout_pros, parent, false);
           return new ProsViewHolder(view1);
       }
    }

    @Override
    public int getItemViewType(int position) {
        if(listImage.size()!=0) {
           if (listImage.get(position).getType()==0)
               return LAYOUT_IMAGE;
           else return LAYOUT_PROS;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (listImage.get(position).getType()) {
            case LAYOUT_IMAGE:
                Image image = listImage.get(position);
                ((ImageViewHolder) holder).setData(image.getUrls().getSmall());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cv_image);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItem != null) {
                        onClickItem.onClickImage(getAdapterPosition());
                    }
                }
            });
        }

        private void setData(String strImage) {
            Glide.with(mContext).load(strImage)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher)
                    .into(img);
        }
    }

    public class ProsViewHolder extends RecyclerView.ViewHolder {

        public ProsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
