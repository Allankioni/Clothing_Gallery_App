//package com.example.clothinggallery;
//
//import android.app.AlertDialog;
//import android.content.ContentResolver;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class GalleryActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gallery);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
////                R.drawable.image1, // Add your drawable resources here
////                R.drawable.image2,
////                R.drawable.image3
//
//        FloatingActionButton fabAddImage = findViewById(R.id.fabButton);
//        fabAddImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openImagePicker();
//            }
//        });
//
//        ViewPager2 viewPager = findViewById(R.id.viewPager);
//        List<Uri> imagesUris = ImageStorage.getInstance().getImageUris();
//        if (imagesUris != null && !imagesUris.isEmpty()) {
//            ImageAdapter adapter = new ImageAdapter(this, imagesUris);
//            viewPager.setAdapter(adapter);
//        } else {
//            Toast.makeText(this, "No images found", Toast.LENGTH_SHORT).show();
//        }
//        // Call this method in onCreate
//        initializeCategories();
//
//    }
////
////    private void openImagePicker() {
////        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////        intent.setType("image/*");
////        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
////        startActivityForResult(Intent.createChooser(intent,"Select Images"), PICK_IMAGES);
////    }
////
////    private List<Uri> loadImagesFromStorage() {
////        List<Uri> imageUris = new ArrayList<>();
////        ContentResolver contentResolver = getContentResolver();
////        Uri collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
////
////        String[] projection = {MediaStore.Images.Media._ID};
////
////        Cursor cursor = contentResolver.query(
////                collection,
////                projection,
////                null,
////                null,
////                MediaStore.Images.Media.DATE_ADDED + " DESC"
////        );
////
////        if (cursor != null) {
////            while (cursor.moveToNext()) {
////                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
////                Uri imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));
////                imageUris.add(imageUri);
////            }
////            cursor.close();
////        }
////
////        if (imageUris.isEmpty()) {
////            Toast.makeText(this, "No images found", Toast.LENGTH_SHORT).show();
////        }
////
////        return imageUris; // Ensure you're returning the URIs
////    }
////
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
////
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        int id = item.getItemId();
////        if (id == R.id.camera) {
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////
////    }
////
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if (requestCode == PICK_IMAGES && resultCode == RESULT_OK) {
////            if (data.getClipData() != null) {
////                int count = data.getClipData().getItemCount();
////                for (int i = 0; i < count; i++) {
////                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
////                    // Add the URI to your list and categorize
////                    categorizeImage(imageUri);
////                }
////            } else if (data.getData() != null) {
////                Uri imageUri = data.getData();
////                // Add the URI to your list and categorize
////                categorizeImage(imageUri);
////            }
////        }
////    }
////
////
////    private void categorizeImage(Uri imageUri) {
////        // Show a dialog to select category
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setTitle("Select Category")
////                .setItems(new String[]{"Adults", "Children"}, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        // Save the image URI in the selected category
////                        if (which == 0) {
////                            // Add to Adults category
////                            adultsImages.add(imageUri);
////                        } else {
////                            // Add to Children category
////                            childrenImages.add(imageUri);
////                        }
////                        // Update your RecyclerView or any view you are using to show images
////                    }
////                }).show();
////    }
////
////
////    private void showSubCategorySelectionDialog(List<Uri> selectedImages, String selectedCategory) {
////        String[] subCategories = {"Shirts", "Dresses", "Trousers"};
////
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setTitle("Select Sub-Category")
////                .setItems(subCategories, (dialog, which) -> {
////                    String selectedSubCategory = subCategories[which];
////                    // Handle the images based on the selected category and sub-category
////                    handleSelectedImages(selectedImages, selectedCategory, selectedSubCategory);
////                })
////                .show();
////    }
////    private void handleSelectedImages(List<Uri> selectedImages, String category, String subCategory) {
////        if (categorizedImages.containsKey(category)) {
////            Map<String, List<Uri>> subCategoryMap = categorizedImages.get(category);
////            if (subCategoryMap != null && subCategoryMap.containsKey(subCategory)) {
////                List<Uri> imageList = subCategoryMap.get(subCategory);
////                imageList.addAll(selectedImages);
////
////                // Update the view to display these images
////                updateImageView(category, subCategory);
////            }
////        }
////        // Store or process the images according to the selected category and sub-category
////        // For example, create a Map<String, List<Uri>> to hold the images
////        // Update your ViewPager or RecyclerView to display the images
////    }
////    private void initializeCategories() {
////        // Initialize main categories
////        String[] mainCategories = {"Adults", "Children"};
////        String[] subCategories = {"Shirts", "Dresses", "Trousers"};
////
////        for (String category : mainCategories) {
////            Map<String, List<Uri>> subCategoryMap = new HashMap<>();
////            for (String subCategory : subCategories) {
////                subCategoryMap.put(subCategory, new ArrayList<>());
////            }
////            categorizedImages.put(category, subCategoryMap);
////        }
////    }
////    private void updateImageView(String category, String subCategory) {
////        if (categorizedImages.containsKey(category)) {
////            Map<String, List<Uri>> subCategoryMap = categorizedImages.get(category);
////            if (subCategoryMap != null && subCategoryMap.containsKey(subCategory)) {
////                List<Uri> imagesToDisplay = subCategoryMap.get(subCategory);
////
////                ViewPager2 viewPager = findViewById(R.id.viewPager);
////                ImageAdapter adapter = new ImageAdapter(this, imagesToDisplay);
////                viewPager.setAdapter(adapter);
////            }
////        }
////    }
//
//
//
//
//}