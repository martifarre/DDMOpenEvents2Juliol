package com.example.ddmopenevents2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;
import com.example.ddmopenevents2.communication.OpenEventsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFrag extends Fragment {
    Button buttonRegister;

    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPasswordRepeat;
    EditText editTextSurname;
    EditText editTextName;
    EditText editTextImage;

    public RegisterFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_screen, container, false);

        buttonRegister = view.findViewById(R.id.registerCreateAccountButton);

        editTextEmail = view.findViewById(R.id.registeremailEditText);
        editTextPassword = view.findViewById(R.id.registerPasswordEditText2);
        editTextPasswordRepeat = view.findViewById(R.id.registerPasswordEditText1);
        editTextSurname = view.findViewById(R.id.replaceTextTextPersonName3);
        editTextName = view.findViewById(R.id.registerFirstNameEditText);
        editTextImage = view.findViewById(R.id.registerProfilePictureEditText);

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        UserRegister user = new UserRegister();
        user.setPassword(editTextPassword.getText().toString());
        user.setEmail(editTextEmail.getText().toString());
        user.setSurname(editTextSurname.getText().toString());
        user.setName(editTextName.getText().toString());
        user.setImage(editTextImage.getText().toString());

        if (editTextPassword.getText().toString().equals(editTextPasswordRepeat.getText().toString())) {
            OpenEventsAPI.getInstance().registerUser(user, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        Toast toast = Toast.makeText(getActivity(), "Registered successful, thanks!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getActivity(), "Error registering, make sure you have entered valid data!", Toast.LENGTH_SHORT);
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