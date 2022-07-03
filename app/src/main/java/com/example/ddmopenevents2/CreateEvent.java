package com.example.ddmopenevents2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ddmopenevents2.business.Event;
import com.example.ddmopenevents2.business.EventAdapted;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.communication.OpenEventsAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEvent extends Fragment {

    private EditText name;
    private EditText image;
    private EditText location;
    private EditText description;
    private EditText eventStart_date;
    private EditText eventEnd_date;
    private EditText n_participators;

    private Spinner type;

    private Button createEventButton;

    public CreateEvent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_event, container, false);

        createEventButton = view.findViewById(R.id.createEventsButton);

        name = view.findViewById(R.id.createTitleEdit);
        image = view.findViewById(R.id.createImageEditText);
        location = view.findViewById(R.id.editTextTextPersonName4);
        description = view.findViewById(R.id.createDescriptionEdit);
        eventStart_date = view.findViewById(R.id.createEditTextDate);
        eventEnd_date = view.findViewById(R.id.editTextDate2);
        n_participators = view.findViewById(R.id.editTextNumber3);

        type = view.findViewById(R.id.spinner1);

        createEventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveEvent();
            }
        });

        return view;
    }

    private void saveEvent() {
        EventAdapted eventAdapted = new EventAdapted();

        eventAdapted.setName(name.getText().toString());
        eventAdapted.setImage(image.getText().toString());
        eventAdapted.setLocation(location.getText().toString());
        eventAdapted.setDescription(description.getText().toString());
        eventAdapted.setEventStart_date(eventStart_date.getText().toString());
        eventAdapted.setEventEnd_date(eventEnd_date.getText().toString());

        String participants = "";

        if (n_participators.getText().toString().equals("")) {
            participants = "1";
        } else {
            participants = n_participators.getText().toString();
        }
        eventAdapted.setN_participators(Integer.parseInt(participants));
        eventAdapted.setType(type.getSelectedItem().toString());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");

        OpenEventsAPI.getInstance().createEvent(token, eventAdapted, new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.body() != null) {
                    Toast toast = Toast.makeText(getActivity(), "Event created successfully", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cannot create event, please check input data!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}