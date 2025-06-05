package com.example.roadtoart.fragments;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roadtoart.MainActivity;
import com.example.roadtoart.R;

public class CongratsFragment extends Fragment {

    public CongratsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Fragment layoutunu inflate et
        return inflater.inflate(R.layout.fragment_congrats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Finish butonuna tıklama dinleyicisi
        view.findViewById(R.id.congrats_finish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();

                    // HomeFragment'ı yükle
                    mainActivity.loadFragment(new HomeFragment(), false);

                    // Bottom navigation bar’da home itemini seç
                    mainActivity.findViewById(R.id.bottom_navigation).post(() -> {
                        BottomNavigationView bottomNav = mainActivity.findViewById(R.id.bottom_navigation);
                        if (bottomNav != null) {
                            bottomNav.setSelectedItemId(R.id.home);
                        }
                    });
                }
            }
        });
    }
}
