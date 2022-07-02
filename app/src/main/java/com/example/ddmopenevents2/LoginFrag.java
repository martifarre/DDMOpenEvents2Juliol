package com.example.ddmopenevents2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.example.ddmopenevents2.communication.OpenEventsInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFrag extends Fragment {
    Button buttonRegister;
    Button buttonLogin;

    EditText editTextEmail;
    EditText editTextPassword;

    public LoginFrag() {
    }

    private void loginUser() {
        User user = new User();
        user.setPassword(editTextPassword.getText().toString());
        user.setEmail(editTextEmail.getText().toString());

        OpenEventsAPI.getInstance().loginUser(user, new Callback<BearerToken>() {
            @Override
            public void onResponse(Call<BearerToken> call, Response<BearerToken> response) {
                if (response.body() != null) {
                    SharedPreferences sharedPrefs;
                    SharedPreferences.Editor editor;
                    sharedPrefs = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);

                    editor = sharedPrefs.edit();
                    editor.putString(String.valueOf(R.string.TOKEN_TOKEN), response.body().getAccessToken());
                    editor.apply();

                    Intent intent = new Intent(getActivity(), EventsActivity.class);
                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Wrong username or password!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<BearerToken> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_frag, container, false);

        buttonRegister = view.findViewById(R.id.registerButton);
        buttonLogin = view.findViewById(R.id.LoginButton);

        editTextEmail = view.findViewById(R.id.loginEmailEditText);
        editTextPassword = view.findViewById(R.id.loginPassEditText);

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(R.id.toPopulateWithFragment);
                    fragment = new RegisterFrag();
                    manager.beginTransaction().replace(R.id.toPopulateWithFragment, fragment).addToBackStack(null).commit();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginUser();
            }
        });

        return view;
    }


}