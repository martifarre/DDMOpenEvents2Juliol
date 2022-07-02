package com.example.ddmopenevents2.communication;

import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenEventsAPI {
    private static OpenEventsAPI openEventsAPI;
    private Retrofit retrofit;
    private OpenEventsInterface openEventsInterface;

    public static OpenEventsAPI getInstance() {
        if (openEventsAPI == null) {
            return new OpenEventsAPI();
        }
        return openEventsAPI;
    }

    public OpenEventsAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://puigmal.salle.url.edu/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.openEventsInterface = this.retrofit.create(OpenEventsInterface.class);
    }

    public void loginUser (User user, Callback<BearerToken> callback) {
        openEventsInterface.loginUser(user).enqueue(callback);
    }

    public void registerUser (UserRegister user, Callback<User> callback) {
        openEventsInterface.registerUser(user).enqueue(callback);
    }

}
