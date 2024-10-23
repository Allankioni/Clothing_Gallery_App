package com.example.clothinggallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private GridView gridView;
    private FloatingActionButton fab;
    private BottomNavigationView bottomNavigationView;
    private String selectedCategory = "Adults"; // Adult or Children by default
    private GalleryAdapter galleryAdapter;

    private List<Uri> adultsShirtsImages = new ArrayList<>();
    private List<Uri> adultsDressesImages = new ArrayList<>();
    private List<Uri> adultsTrousersImages = new ArrayList<>();

    private static final int REQUEST_CODE_PICK_IMAGES = 103;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridView);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        fab = view.findViewById(R.id.fabButton);

        galleryAdapter = new GalleryAdapter(requireContext(), new ArrayList<>());
        gridView.setAdapter(galleryAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_trousers) {
                loadImages("Trousers");
            } else if (id == R.id.nav_dresses) {
                loadImages("Dresses");
            } else if (id == R.id.nav_shirts) {
                loadImages("Shirts");
            } else {
                return false;  // Return false if the item ID doesn't match any of the defined ones
            }
            return true;  // Return true after handling the item selection
        });


        fab.setOnClickListener(view1 -> openImagePicker());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGES);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    addImageToCategory("Shirts", imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                addImageToCategory("Shirts", imageUri);
            }
        }
    }

    private void addImageToCategory(String subCategory, Uri imageUri) {
        if (selectedCategory.equals("Adults")) {
            switch (subCategory) {
                case "Shirts":
                    adultsShirtsImages.add(imageUri);
                    break;
                case "Dresses":
                    adultsDressesImages.add(imageUri);
                    break;
                case "Trousers":
                    adultsTrousersImages.add(imageUri);
                    break;
            }
        }
        loadImages(subCategory);  // Refresh GridView with new images
    }

    private void loadImages(String subCategory) {
        List<Uri> images = new ArrayList<>();
        switch (subCategory) {
            case "Shirts":
                images.addAll(adultsShirtsImages);
                break;
            case "Dresses":
                images.addAll(adultsDressesImages);
                break;
            case "Trousers":
                images.addAll(adultsTrousersImages);
                break;
        }
        galleryAdapter.setImageUris(images);
        galleryAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}
