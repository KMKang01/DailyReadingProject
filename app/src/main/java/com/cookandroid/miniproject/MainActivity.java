package com.cookandroid.miniproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView; // 하단 목록

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.menuBar);

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new home()).commit(); //FrameLayout에 fragment.xml 띄우기

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new home()).commit();
                } else if (itemId == R.id.history) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new history()).commit();
                } else if (itemId == R.id.newPlan) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new newplan()).commit();
                } else if (itemId == R.id.setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new setting()).commit();
                }
                return true;
            }
        });


    }


}