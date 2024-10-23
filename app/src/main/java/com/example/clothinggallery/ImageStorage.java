package com.example.clothinggallery;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class ImageStorage {
    private static ImageStorage instance;
    private List<Uri> imageUris;

    private ImageStorage() {
        imageUris = new ArrayList<>();
    }

    public static ImageStorage getInstance() {
        if (instance == null) {
            instance = new ImageStorage();
        }
        return instance;
    }

    public List<Uri> getImageUris() {
        return imageUris;
    }

    public void setImageUris(List<Uri> imageUris) {
        this.imageUris = imageUris;
    }
}
