package com.example.ddmopenevents2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFrag extends Fragment {

    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText passwordRepeat;
    private EditText image;

    private Button saveChangesButton;

    public EditProfileFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        saveChangesButton = view.findViewById(R.id.editCreateAccountButton);

        name = view.findViewById(R.id.editFirstNameEditText);
        surname = view.findViewById(R.id.editTextTextPersonName3);
        email = view.findViewById(R.id.editemailEditText);
        password = view.findViewById(R.id.editPasswordEditText2);
        passwordRepeat = view.findViewById(R.id.editPasswordEditText1);
        image = view.findViewById(R.id.editProfilePictureEditText);

        saveChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveNewUserData();
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
                    image.setText(response.body().get(0).getImage());
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

    private void saveNewUserData() {
        UserRegister user = new UserRegister();
        user.setPassword(password.getText().toString());
        user.setEmail(email.getText().toString());
        user.setSurname(surname.getText().toString());
        user.setName(name.getText().toString());
        user.setImage(image.getText().toString());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");

        if (password.getText().toString().equals(passwordRepeat.getText().toString())) {
            OpenEventsAPI.getInstance().editUserProfile(token, user, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        Toast toast = Toast.makeText(getActivity(), "Updated successful, thanks!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getActivity(), "Error updating, make sure you have entered valid data!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }
}