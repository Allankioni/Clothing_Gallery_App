package com.example.clothinggallery;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter extends BaseAdapter {
    private List<Uri> imageUris;
    private Context context;

    // Constructor
    public GalleryAdapter(Context context, List<Uri> imageUris) {
        this.context = context;
        this.imageUris = imageUris;
    }

    // Set new image URIs (for updating the GridView)
    public void setImageUris(List<Uri> imageUris) {
        this.imageUris = imageUris;
        notifyDataSetChanged(); // Refresh the adapter when new data is set
    }

    // Returns the count of images
    @Override
    public int getCount() {
        return imageUris.size();
    }

    // Returns the image URI at the specific position
    @Override
    public Object getItem(int position) {
        return imageUris.get(position);
    }

    // Returns the item ID (position in this case)
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Returns the view for each item in the GridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200)); // Adjust the size as needed
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        // Load image into ImageView using Glide
        Glide.with(context)
                .load(imageUris.get(position))
                .into(imageView);

        return imageView;
    }


}
