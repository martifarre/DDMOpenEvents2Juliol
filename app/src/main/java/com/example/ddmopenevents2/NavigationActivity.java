package com.example.ddmopenevents2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ddmopenevents2.lists.EventsListFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView navigationView;

    private String getUserId () {
        SharedPreferences sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        return Integer.toString(sharedPreferences.getInt(String.valueOf(R.string.TOKEN_ID), 0));
    }

    private String getAccessToken() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationView = findViewById(R.id.container);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.menu_my_profile);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_my_profile) {
            EditProfileFrag editProfileFrag = new EditProfileFrag();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_navigation, editProfileFrag).commit();
            return true;
        }
        if (id == R.id.menu_explore_events) {
            EventsListFrag eventsListFrag = new EventsListFrag();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_navigation, eventsListFrag).commit();
            return true;
        }

        return false;
    }
}