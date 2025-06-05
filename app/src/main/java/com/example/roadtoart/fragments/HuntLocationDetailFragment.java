package com.example.roadtoart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roadtoart.MainActivity;
import com.example.roadtoart.R;

public class HuntLocationDetailFragment extends Fragment {

    public HuntLocationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hunt_location_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitButton = view.findViewById(R.id.hunt_detail_submit_button);

        submitButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.loadFragment(new CurrentHuntFragment(), false);

                // Eğer alt menü varsa onun seçimini de değiştirmek istersen (opsiyonel):
                mainActivity.findViewById(R.id.bottom_navigation).post(() -> {
                    mainActivity.findViewById(R.id.bottom_navigation)
                            .performClick(); // Gerekirse Current Hunt sekmesini seç
                });
            }
        });
    }
}
