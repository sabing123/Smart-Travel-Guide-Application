package com.example.smarttravelguideapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smarttravelguideapplication.fragement.HomeFragment;
import com.example.smarttravelguideapplication.fragement.HotelFragment;
import com.example.smarttravelguideapplication.fragement.PlaceFragment;
import com.example.smarttravelguideapplication.fragement.WeatherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
            
    FloatingActionButton fab;
            
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);

        CustomBottomNavigationView1 customBottomNavigationView1 = findViewById(R.id.customBottomBar);
        customBottomNavigationView1.inflateMenu(R.menu.menu_navigation);
        customBottomNavigationView1.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new HomeFragment(), 1);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Explore Some places ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
        

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    loadFragment(fragment, 1);
                    return true;
                case R.id.hotel:
                    fragment = new HotelFragment();
                    loadFragment(fragment, 2);
                    return true;
                case R.id.places:
                    fragment = new PlaceFragment();
                    loadFragment(fragment, 3);
                    return true;
                case R.id.weather:
                    fragment = new WeatherFragment();
                    loadFragment(fragment, 4);
                    return true;
            }

            return false;
        }
    };
    private int position = 0;

    private void loadFragment(Fragment fragment, int position) {
        while (this.position != position) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            if (this.position < position) {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right);
            } else {
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left);
            }
            this.position = position;
            transaction.addToBackStack(null);
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }
    }

  }