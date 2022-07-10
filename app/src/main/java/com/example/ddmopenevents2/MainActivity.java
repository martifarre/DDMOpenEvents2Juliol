package com.example.ddmopenevents2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPrefs;
        SharedPreferences.Editor editor;
        sharedPrefs = this.getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);

        editor = sharedPrefs.edit();
        editor.putString(String.valueOf(R.string.TOKEN_TOKEN), "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTgxOCwibmFtZSI6Ik1hcnRpIiwibGFzdF9uYW1lIjoiRmFycmUgVHJ1amlsbG8iLCJlbWFpbCI6Im1hcnRpQGdtYWlsLmNvbSIsImltYWdlIjoiaHR0cHM6Ly9pLmltZ3VyLmNvbS9naHk4WHgxLnBuZyJ9.eCx7HYylVmHLXFlUn6JNxqmM4xo2BD4UPrriGMlwlPM");
        editor.putInt(String.valueOf(R.string.TOKEN_ID), 1818);
        editor.apply();

        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);

//        FragmentManager manager = getSupportFragmentManager();
//        Fragment fragment = manager.findFragmentById(R.id.toPopulateWithFragment);
//        if (fragment == null) {
//            fragment = new LoginFrag();
//            manager.beginTransaction().add(R.id.toPopulateWithFragment, fragment).commit();
//        }
    }
}
