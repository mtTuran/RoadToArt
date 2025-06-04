package com.example.roadtoart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadtoart.R;
import com.example.roadtoart.utils.CategoryImageAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Sample images — replace with your actual drawables
        List<Integer> category1Images = Arrays.asList(
                R.drawable.img1, R.drawable.img2, R.drawable.img3
        );

        List<Integer> category2Images = Arrays.asList(
                R.drawable.img4, R.drawable.img5, R.drawable.img6
        );

        List<Integer> category3Images = Arrays.asList(
                R.drawable.img3, R.drawable.img1
        );

        setupRecyclerView(view, R.id.recycler_category_1, category1Images);
        setupRecyclerView(view, R.id.recycler_category_2, category2Images);
        setupRecyclerView(view, R.id.recycler_category_3, category3Images);
    }

    private void setupRecyclerView(View view, int recyclerId, List<Integer> imageList) {
        RecyclerView recyclerView = view.findViewById(recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new CategoryImageAdapter(getContext(), imageList));
    }
}