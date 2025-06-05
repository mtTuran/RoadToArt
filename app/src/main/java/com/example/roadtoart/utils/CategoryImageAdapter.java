package com.example.roadtoart.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadtoart.MainActivity;
import com.example.roadtoart.R;

import java.util.List;

public class CategoryImageAdapter extends RecyclerView.Adapter<CategoryImageAdapter.ViewHolder> {

    private final Context context;
    private final List<Integer> imageList;

    public CategoryImageAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public CategoryImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryImageAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));

        holder.itemView.setOnClickListener(v -> {
            // Context'i MainActivity'e cast et
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;
                // BottomNavigation'daki Hunt sekmesini seç
                mainActivity.getBottomNavigationView().setSelectedItemId(R.id.current_hunt);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_card); // item_category_image.xml içindeki ImageView ID'si
        }
    }
}
