package com.example.ddmopenevents2.communication;

import com.example.ddmopenevents2.business.AssistanceResponse;
import com.example.ddmopenevents2.business.BearerToken;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.business.EventResponse;
import com.example.ddmopenevents2.business.EventWithCommentary;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.business.UserRegister;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenEventsAPI {
    private final String baseUrl = "http://puigmal.salle.url.edu/api/v2/";

    private static OpenEventsAPI openEventsAPI;
    private Retrofit retrofit;
    private OpenEventsInterface openEventsInterface;

    public static OpenEventsAPI getInstance() {
        if (openEventsAPI == null) {
            openEventsAPI = new OpenEventsAPI();
        }
        return openEventsAPI;
    }

    public OpenEventsAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.openEventsInterface = this.retrofit.create(OpenEventsInterface.class);
    }

    public void loginUser(User user, Callback<BearerToken> callback) {
        this.openEventsInterface.loginUser(user).enqueue(callback);
    }

    public void registerUser(UserRegister user, Callback<User> callback) {
        this.openEventsInterface.registerUser(user).enqueue(callback);
    }

    public void getAllEvents(String token, Callback<ArrayList<Event>> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getAllEvents(tokenString).enqueue(callback);
    }

    public void getBestEvents(String token, Callback<ArrayList<Event>> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getBestEvents(tokenString).enqueue(callback);
    }

    public void editUserProfile(String token, UserRegister userRegister, Callback<User> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.editUserProfile(tokenString, userRegister).enqueue(callback);
    }

    public void getAllUsers(String token, Callback<ArrayList<User>> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getAllUsers(tokenString).enqueue(callback);
    }

    public void getUser(String token, int userId, Callback<ArrayList<User>> callback){
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getUser(tokenString, userId).enqueue(callback);
    }

    public void createEvent(String token, EventResponse eventAdapted, Callback<Event> callback){
        String tokenString = "Bearer " + token;
        this.openEventsInterface.createEvent(tokenString, eventAdapted).enqueue(callback);
    }

    public void getEventById(String token, int eventId, Callback<ArrayList<Event>> callback){
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getEventById(tokenString, eventId).enqueue(callback);
    }

    public void assistEvent(String token, int eventId, Callback<AssistanceResponse> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.assistEvent(tokenString, eventId).enqueue(callback);
    }

    public void getAssistanceByUserId(String token, int userId, Callback<ArrayList<EventWithCommentary>> callback) {
        String tokenString = "Bearer " + token;
        this.openEventsInterface.getAssistanceByUserId(tokenString, userId).enqueue(callback);
    }

}
