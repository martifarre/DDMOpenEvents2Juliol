package com.example.ddmopenevents2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.toPopulateWithFragment);
        if (fragment == null) {
            fragment = new LoginFrag();
            manager.beginTransaction().add(R.id.toPopulateWithFragment, fragment).commit();
        }
    }
}
