package com.example.vrsiddhartha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new fragmentHome()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new fragmentHome();
                    break;
                case R.id.lms:
                    selectedFragment = new fragment_lms();
                    break;
                case R.id.cms:
                    selectedFragment = new fragment_cms();
                    break;
                case R.id.moodle:
                    selectedFragment = new fragment_moodle();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
        };
}