package com.example.clothinggallery;

import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class category {
    private String parentCategory;
    private Map<String, List<Uri>> subCategories;// Map of sub-categories to their images

    public category(String parentCategory) {
        this.parentCategory = parentCategory;
        this.subCategories = new HashMap<>();
        // Initialize sub-categories with empty lists
        subCategories.put("Shirts", new ArrayList<>());
        subCategories.put("Dresses", new ArrayList<>());
        subCategories.put("Trousers", new ArrayList<>());
    }

    public String getParentCategory() {
        return parentCategory;
    }
    public List<Uri> getImagesForSubCategory(String subCategory) {
        return subCategories.get(subCategory);
    }
    public void addImageToSubCategory(String subCategory, Uri imageUri) {
        if (subCategories.containsKey(subCategory)) {
            subCategories.get(subCategory).add(imageUri);
        }
    }
    public void setImagesForSubCategory(String subCategory, List<Uri> images) {
        if (subCategories.containsKey(subCategory)) {
            subCategories.put(subCategory, images);
        }

    }
}
