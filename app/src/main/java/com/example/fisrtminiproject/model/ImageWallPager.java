package com.example.fisrtminiproject.model;

import java.io.File;

public class ImageWallPager {
    private File image;

    public ImageWallPager(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
