package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.listview.fragment.BeverageFragment;
import com.example.listview.fragment.ExtraFragment;
import com.example.listview.fragment.FoodFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView botNavFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        botNavFood = findViewById(R.id.navigation);
        botNavFood.setOnItemSelectedListener(this);

        botNavFood.setSelectedItemId(R.id.food_nav);

    }

    BeverageFragment beverageFragment = new BeverageFragment();
    ExtraFragment extraFragment = new ExtraFragment();
    FoodFragment foodFragment = new FoodFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.food_nav:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, foodFragment).commit();
                return true;
            case R.id.beverage_nav:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, beverageFragment).commit();
                return true;
            case R.id.extra_food_nav:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, extraFragment).commit();
                return true;
        }
        return false;
    }
}