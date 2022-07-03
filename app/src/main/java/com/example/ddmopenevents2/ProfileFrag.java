package com.example.ddmopenevents2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ProfileFrag extends Fragment {

    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView comments;
    private TextView numEvents;

    private Button editProfile;
    private Button logout;

    public ProfileFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_user, container, false);

        editProfile = view.findViewById(R.id.button);
        logout = view.findViewById(R.id.button2);

        name = view.findViewById(R.id.editTextTextPersonName);
        surname = view.findViewById(R.id.editTextTextPersonName2);
        email = view.findViewById(R.id.editTextTextEmailAddress);
        comments = view.findViewById(R.id.editTextNumber);
        numEvents = view.findViewById(R.id.editTextNumber2);

        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(R.id.toPopulateWithFragment);
                fragment = new EditProfileFrag();
                manager.beginTransaction().replace(R.id.fragment_navigation, fragment).addToBackStack(null).commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logoutUser();
            }
        });

        return view;
    }

    private void logoutUser(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}