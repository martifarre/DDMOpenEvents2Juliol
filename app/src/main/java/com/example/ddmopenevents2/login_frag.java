package com.example.ddmopenevents2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class login_frag extends Fragment {

    Button callSingUp;
    public login_frag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_frag, container, false);
        callSingUp = view.findViewById(R.id.registerButton);
        callSingUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(R.id.toPopulateWithFragment);
                    fragment = new registerScreen();
                    manager.beginTransaction().replace(R.id.toPopulateWithFragment, fragment).addToBackStack(null).commit();

            }
        });
        return view;
    }


}