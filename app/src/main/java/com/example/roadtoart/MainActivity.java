package com.example.roadtoart;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.roadtoart.fragments.AddToCollectionFragment;
import com.example.roadtoart.fragments.CollectionFragment;
import com.example.roadtoart.fragments.CreateHuntFragment;
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
        collectionFragment = new CollectionFragment();
        profileFragment = new ProfileFragment();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                selectedFragment = homeFragment;
            } else if (itemId == R.id.current_hunt) {
                selectedFragment = currentHuntFragment;
            } else if (itemId == R.id.create) {
                selectedFragment = addToCollectionFragment;
            } else if (itemId == R.id.collection) {
                selectedFragment = collectionFragment;
            } else if (itemId == R.id.profile) {
                selectedFragment = profileFragment;
            } else {
                return false;
            }

            loadFragment(selectedFragment, false); // Don't add bottom nav changes to back stack
            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.home);
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