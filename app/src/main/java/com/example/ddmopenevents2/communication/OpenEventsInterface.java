package com.example.ddmopenevents2.communication;

import com.example.ddmopenevents2.business.AssistanceResponse;
import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.business.EventResponse;
import com.example.ddmopenevents2.business.EventWithCommentary;
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

    @GET("events/best/")
    Call<ArrayList<Event>> getBestEvents(@Header("Authorization") String token);

    @GET("users/")
    Call<ArrayList<User>> getAllUsers(@Header("Authorization") String token);

    @PUT("users")
    Call<User> editUserProfile(@Header("Authorization") String token, @Body UserRegister userRegister);

    @GET("users/{id}")
    Call<ArrayList<User>> getUser(@Header("Authorization") String token, @Path("id") int userId);

    @POST("events/")
    Call<Event> createEvent(@Header("Authorization") String token, @Body EventResponse eventAdapted);

    @GET("events/{id}")
    Call<ArrayList<Event>> getEventById(@Header("Authorization") String token, @Path("id") int eventId);

    @POST("events/{id}/assistances")
    Call<AssistanceResponse> assistEvent(@Header("Authorization") String token, @Path("id") int id);

    @GET("/users/{id}/assistances")
    Call<ArrayList<EventWithCommentary>> getAssistanceByUserId(@Header("Authorization") String token, @Path("id") int id);
}
