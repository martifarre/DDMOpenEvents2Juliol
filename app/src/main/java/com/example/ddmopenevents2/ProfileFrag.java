package com.example.ddmopenevents2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFrag extends Fragment {

    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView tvUserId;

    private ImageView profileImage;

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
        tvUserId = view.findViewById(R.id.editTextUserId);

        profileImage = view.findViewById(R.id.imageView);

        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = new EditProfileFrag();
                manager.beginTransaction().replace(R.id.fragment_navigation, fragment).addToBackStack(null).commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logoutUser();
            }
        });

        populateUserData();

        return view;
    }

    private void populateUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");
        int userId = sharedPreferences.getInt(String.valueOf(R.string.TOKEN_ID),0);

        OpenEventsAPI.getInstance().getUser(token, userId, new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.body() != null) {
                    name.setText(response.body().get(0).getName());
                    surname.setText(response.body().get(0).getSurname());
                    email.setText(response.body().get(0).getEmail());
                    String userId = "" + response.body().get(0).getId();
                    tvUserId.setText(userId);

                    try {
                        Picasso.get()
                                .load(response.body().get(0).getImage())
                                .into(profileImage, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        profileImage.setImageResource(R.drawable.default_event_img);
                                    }
                                });
                    } catch (IllegalArgumentException iae) {
                        profileImage.setImageResource(R.drawable.default_event_img);
                    }

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cannot get user data!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void logoutUser(){
        SharedPreferences sharedPrefs;
        SharedPreferences.Editor editor;
        sharedPrefs = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);

        editor = sharedPrefs.edit();
        editor.putString(String.valueOf(R.string.TOKEN_TOKEN), "");
        editor.apply();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}