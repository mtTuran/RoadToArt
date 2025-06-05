package com.example.roadtoart.fragments; // Or your preferred package

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.roadtoart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateOptionsBottomSheet extends BottomSheetDialogFragment {

    public static final String TAG = "CreateOptionsBottomSheet";

    private BottomSheetListener mListener;

    public interface BottomSheetListener {
        void onButtonClicked(String choice); // Or pass more specific identifiers
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create_options_bottom_sheet, container, false);

        Button btnAddToCollection = view.findViewById(R.id.button_add_to_collection);
        Button btnCreateHunt = view.findViewById(R.id.button_create_hunt);

        btnAddToCollection.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onButtonClicked("add_to_collection");
            }
            dismiss(); // Close the bottom sheet
        });

        btnCreateHunt.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onButtonClicked("create_hunt");
            }
            dismiss(); // Close the bottom sheet
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Ensure the host activity implements the listener
        if (context instanceof BottomSheetListener) {
            mListener = (BottomSheetListener) context;
        } else {
            // If the activity hosting this BottomSheet doesn't implement the listener,
            // try to see if the target fragment (if set) implements it.
            if (getParentFragment() instanceof BottomSheetListener) {
                mListener = (BottomSheetListener) getParentFragment();
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement BottomSheetListener or set a target fragment that does.");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}