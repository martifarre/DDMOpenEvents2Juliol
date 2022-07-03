package com.example.ddmopenevents2.lists;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ddmopenevents2.R;
import com.example.ddmopenevents2.business.Event;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsHolder> {

    private Context context;
    private ArrayList<Event> eventsArrayList;

    public EventsAdapter(Context context, ArrayList<Event> eventsArrayList) {
        this.context = context;
        this.eventsArrayList = eventsArrayList;
    }

    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder holder, int position) {
        holder.setName(eventsArrayList.get(position).getName());
        holder.setStartDate(eventsArrayList.get(position).getEventStart_date());
        holder.setLocation(eventsArrayList.get(position).getLocation());
        holder.setImage(R.drawable.default_event_img);

        Log.i("image", ""+ eventsArrayList.get(position).getImage());
        try {
            Picasso.get()
                    .load(eventsArrayList.get(position).getImage())
                    .into(holder.getImage(), new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            holder.setImage(R.drawable.default_event_img);
                        }
                    });
        } catch (IllegalArgumentException iae) {
            holder.setImage(R.drawable.default_event_img);
        }
    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }
}
