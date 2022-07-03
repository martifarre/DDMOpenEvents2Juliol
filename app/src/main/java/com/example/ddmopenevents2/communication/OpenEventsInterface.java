package com.example.ddmopenevents2.communication;

import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OpenEventsInterface {
    @POST("users/login/")
    Call<BearerToken> loginUser(@Body User user);

    @POST("users/")
    Call<User> registerUser(@Body UserRegister userRegister);

    @GET("events/")
    Call<ArrayList<Event>> getAllEvents(@Header("Authorization") String token);

    @GET("users/")
    Call<ArrayList<User>> getAllUsers(@Header("Authorization") String token);

    @PUT("users")
    Call<User> editUserProfile(@Header("Authorization") String token, @Body UserRegister userRegister);

    @GET("users/{id}")
    Call<ArrayList<User>> getUser(@Header("Authorization") String token, @Path("id") int userId);
}
