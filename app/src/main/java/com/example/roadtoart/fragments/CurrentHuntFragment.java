package com.example.roadtoart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roadtoart.MainActivity;
import com.example.roadtoart.R;

public class CurrentHuntFragment extends Fragment {

    public static int red_order = 0;
    public CurrentHuntFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_hunt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Listeye eklemek istediğiniz tüm item ID'lerini burada belirtin
        int[] locationItemIds = {
                R.id.location_1_item,
                R.id.location_2_item,
                R.id.location_3_item,
                R.id.location_4_item,
                R.id.location_5_item,
                R.id.location_6_item
        };

        view.findViewById(R.id.current_hunt_finish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.loadFragment(new CongratsFragment(), true);
                }
            }
        });

        for (int id : locationItemIds) {
            View item = view.findViewById(id);
            if (item != null) {
                item.setOnClickListener(v -> {
                    if (getActivity() instanceof MainActivity) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.loadFragment(new HuntLocationDetailFragment(), true);
                    }
                });
            }
        }
        for (int i = 0; i < red_order; i = i + 1){
            view.findViewById(locationItemIds[red_order]).setBackgroundColor(getResources().getColor(R.color.red_primary));
        }
        red_order += 1;
    }
}
