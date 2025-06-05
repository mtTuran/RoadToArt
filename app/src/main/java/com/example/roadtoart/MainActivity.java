package com.example.roadtoart;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.roadtoart.fragments.AddToCollectionFragment;
import com.example.roadtoart.fragments.CollectionFragment;
import com.example.roadtoart.fragments.CreateHuntFragment;
import com.example.roadtoart.fragments.CreateOptionsBottomSheet;
import com.example.roadtoart.fragments.CurrentHuntFragment;
import com.example.roadtoart.fragments.HomeFragment;
import com.example.roadtoart.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    String username;
    TextView usernameView;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    CurrentHuntFragment currentHuntFragment;
    AddToCollectionFragment addToCollectionFragment;
    CreateHuntFragment createHuntFragment;
    CollectionFragment collectionFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameView = findViewById(R.id.usernameView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        username = getIntent().getStringExtra("username");
        usernameView.setText(username);

        homeFragment = new HomeFragment();
        currentHuntFragment = new CurrentHuntFragment();
        addToCollectionFragment = new AddToCollectionFragment();
        createHuntFragment = new CreateHuntFragment();
        collectionFragment = new CollectionFragment();
        profileFragment = new ProfileFragment();

        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }


    private boolean onNavigationItemSelected(MenuItem item) {
        Fragment selectedFragment;
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            selectedFragment = homeFragment;
        } else if (itemId == R.id.current_hunt) {
            selectedFragment = currentHuntFragment;
        } else if (itemId == R.id.create) {
            showCreateOptionsDialog();
            return false;
        } else if (itemId == R.id.collection) {
            selectedFragment = collectionFragment;
        } else if (itemId == R.id.profile) {
            selectedFragment = profileFragment;
        } else {
            return false;
        }

        loadFragment(selectedFragment, false); // Don't add bottom nav changes to back stack
        return true;
    }

    private void showCreateOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New"); // Or your desired title
        builder.setMessage("What would you like to create?"); // Or your desired message

        // Button 1: Add to Collection
        builder.setPositiveButton("Add to Collection", (dialog, which) -> {
            // User chose "Add to Collection"
            // Programmatically select the "create" item in BottomNavigationView
            bottomNavigationView.setOnItemSelectedListener(null); // Temporarily remove listener to avoid recursion
            bottomNavigationView.setSelectedItemId(R.id.create);
            bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected); // Re-attach listener
            loadFragment(addToCollectionFragment, false);
        });

        // Button 2: Create Hunt
        builder.setNegativeButton("Create Hunt", (dialog, which) -> {
            // User chose "Create Hunt"
            bottomNavigationView.setOnItemSelectedListener(null);
            bottomNavigationView.setSelectedItemId(R.id.create);
            bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
            loadFragment(createHuntFragment, false);
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
