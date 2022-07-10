package com.example.ddmopenevents2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsUser extends Fragment {

    private TextView name;
    private TextView email;

    private ImageView image;

    private int descriptionUserId;

    public DetailsUser(int descriptionUserId) {
        this.descriptionUserId = descriptionUserId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_user, container, false);

        name = view.findViewById(R.id.detailsUserSpecificTitle);
        email = view.findViewById(R.id.detailUserDescription);

        image = view.findViewById(R.id.detailsUserImage);

        populateUserData();

        return view;
    }

    private void populateUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN), "");

        OpenEventsAPI.getInstance().getUserById(token, descriptionUserId, new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.body() != null) {
                    String userFullName = response.body().get(0).getName() + " " + response.body().get(0).getSurname();
                    name.setText(userFullName);
                    email.setText(response.body().get(0).getEmail());

                    try {
                        Picasso.get()
                                .load(response.body().get(0).getImage())
                                .into(image, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        image.setImageResource(R.drawable.profile);
                                    }
                                });
                    } catch (IllegalArgumentException iae) {
                        Toast toast = Toast.makeText(getActivity(), "Cannot get user data!", Toast.LENGTH_SHORT);
                        toast.show();
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
}