package com.example.ddmopenevents2.communication;

import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenEventsInterface {
    @POST("users/login/")
    Call<BearerToken> loginUser(@Body User user);

    @POST("users/")
    Call<User> registerUser(@Body UserRegister userRegister);
}