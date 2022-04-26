package com.example.astrodaily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astrodaily.fragments.ApodFragment;
import com.example.astrodaily.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    BottomNavigationView bottom_navigation;
    HomeFragment homeFragment = new HomeFragment();
    ApodFragment apodFragment = new ApodFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        bottom_navigation = findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnItemSelectedListener(this::OnItemSelectedListener);
        bottom_navigation.setSelectedItemId(R.id.ic_home);
    }

    public boolean OnItemSelectedListener(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.ic_home:
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_wrapper, homeFragment)
                    .commitNow();
                break;
            case R.id.ic_apod:
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_wrapper, apodFragment)
                    .commitNow();
        }
        return true;
    };
}