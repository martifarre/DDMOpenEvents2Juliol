package com.example.ddmopenevents2.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddmopenevents2.CreateEvent;
import com.example.ddmopenevents2.R;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.communication.OpenEventsAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEventsListFrag extends Fragment {

    private ArrayList<Event> events;
    private EventsAdapter eventsAdapter;
    private RecyclerView recyclerView;

    private Button createButton;

    public MyEventsListFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_events_list, container, false);

        createButton = view.findViewById(R.id.mycreateEventsButton);

        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createNewEventFragment();
            }
        });

        recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        events = new ArrayList<Event>();
        eventsAdapter = new EventsAdapter(getContext(), events);
        recyclerView.setAdapter(eventsAdapter);

        getAndSetEvents();

        return view;
    }

    private void createNewEventFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = new CreateEvent();
        manager.beginTransaction().replace(R.id.fragment_navigation, fragment).addToBackStack(null).commit();
    }

    private void getAndSetEvents() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");

        OpenEventsAPI.getInstance().getAllEvents(token, new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.body() != null) {
                    events.clear();
                    events.addAll(response.body());

                    filtrateMyEvents();

                    eventsAdapter.notifyDataSetChanged();
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.i("GET","EXPLORE EVENTS KO!");
            }
        });
    }

    private void filtrateMyEvents() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt(String.valueOf(R.string.TOKEN_ID), 0);

        ArrayList<Event> eventsFiltrated = new ArrayList<Event>();
        for (Event event : events) {
            if (event.getOwner_id() == userId) {
                eventsFiltrated.add(event);
            }
        }
        events.clear();
        events.addAll(eventsFiltrated);
    }
}