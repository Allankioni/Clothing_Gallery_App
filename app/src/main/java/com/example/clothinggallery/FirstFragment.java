package com.example.clothinggallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    private static final int STORAGE_PERMISSION_CODE =101;
    private Spinner categorySpinner;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.firstFragment ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        categorySpinner =view.findViewById(R.id.categorySpinner);

        Button continueButton = view.findViewById(R.id.continueButton);
        // Set up the spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        continueButton.setOnClickListener(v ->  {
            String selectedCategory = categorySpinner.getSelectedItem().toString();
            Bundle bundle = new Bundle();
            bundle.putString("CATEGORY", selectedCategory);
            // Navigate to the second fragment
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navToGallery, bundle);
        });
//        continueButton.setOnClickListener(new View.OnClickListener() {
//                }
//            @Override
//            public void onClick(View v) {
//                String selectedCategory = categorySpinner.getSelectedItem().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("CATEGORY", selectedCategory);
//                // Navigate to the second fragment
//                NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView3);
//                navController.navigate(R.id.navToGallery);
//
//
////                Intent intent = new Intent(getActivity(), SecondFragment.class);
////                intent.putExtra("CATEGORY", selectedCategory);
////                startActivity(intent);
//            }
//        });
//        Button continueButton = view.findViewById(R.id.continueButton);
//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SecondFragment.class);
//                startActivity(intent);
//
//            }
//        });
        return view;
    }
}