package com.example.ddmopenevents2.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ddmopenevents2.R;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.communication.OpenEventsAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsListFrag extends Fragment {

    private ArrayList<Event> events;
    private EventsAdapter eventsAdapter;
    private RecyclerView recyclerView;

    public EventsListFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        events = new ArrayList<Event>();
        eventsAdapter = new EventsAdapter(getContext(), events);
        recyclerView.setAdapter(eventsAdapter);

        getAndSetEvents();

        return view;
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

                    orderEventsList();

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

    private void orderEventsList() {

    }
}