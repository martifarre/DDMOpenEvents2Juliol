package com.example.ddmopenevents2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ddmopenevents2.business.AssistanceResponse;
import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.business.EventWithCommentary;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsEvents extends Fragment {

    private TextView name;
    private TextView description;

    private ImageView image;

    private Button attendanceButton;

    private int eventId;

    public DetailsEvents(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_events, container, false);

        attendanceButton = view.findViewById(R.id.detailAttendButton);

        name = view.findViewById(R.id.detailsEventSpecificTitle);
        description = view.findViewById(R.id.detailEventDescription);

        image = view.findViewById(R.id.detailsEventImage);

        attendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendEvent();
            }
        });

        populateEventData();

        return view;
    }

    private void attendEvent() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN), "");

        OpenEventsAPI.getInstance().assistEvent(token, eventId, new Callback<AssistanceResponse>() {
            @Override
            public void onResponse(Call<AssistanceResponse> call, Response<AssistanceResponse> response) {
                if (response.body() != null) {
                    if (response.body().getAffectedRows() >= 1) {
                        Toast toast = Toast.makeText(getActivity(), "Event assistance correctly received!", Toast.LENGTH_SHORT);
                        toast.show();

                        attendanceButton.setAlpha(.5f);
                        attendanceButton.setClickable(false);
                    } else {
                        Toast toast = Toast.makeText(getActivity(), "Cannot assist event!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cannot assist event!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AssistanceResponse> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void populateEventData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN), "");
        int userId = sharedPreferences.getInt(String.valueOf(R.string.TOKEN_ID), 0);

        OpenEventsAPI.getInstance().getEventById(token, eventId, new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.body() != null) {
                    name.setText(response.body().get(0).getName());
                    description.setText(response.body().get(0).getDescription());

                    attendanceButton.setAlpha(.5f);
                    attendanceButton.setClickable(false);

                    try {
                        Picasso.get()
                                .load(response.body().get(0).getImage())
                                .into(image, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        image.setImageResource(R.drawable.default_event_img);
                                    }
                                });
                    } catch (IllegalArgumentException iae) {
                        Toast toast = Toast.makeText(getActivity(), "Cannot get event data!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    OpenEventsAPI.getInstance().getAssistanceByUserId(token, userId, new Callback<ArrayList<EventWithCommentary>>() {
                        @Override
                        public void onResponse(Call<ArrayList<EventWithCommentary>> call, Response<ArrayList<EventWithCommentary>> response) {
                            if (response.body() != null) {
                                boolean doesAssist = false;

                                for (EventWithCommentary event : response.body()) {
                                    if (event.getId() == eventId) {
                                        doesAssist = true;
                                    }
                                }

                                if (!doesAssist) {
                                    attendanceButton.setAlpha(1.0f);
                                    attendanceButton.setClickable(true);
                                }
                            } else {
                                attendanceButton.setAlpha(1.0f);
                                attendanceButton.setClickable(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<EventWithCommentary>> call, Throwable t) {
                            Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cannot get event data!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}