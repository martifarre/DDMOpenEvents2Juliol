package com.example.ddmopenevents2.lists;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ddmopenevents2.R;

public class EventsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView name;
    private TextView startDate;
    private TextView location;

    private ImageView image;
    private RecyclerViewClickListener listener;

    public EventsHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.listener = listener;

        name = (TextView) itemView.findViewById(R.id.cardTitle);
        startDate = (TextView) itemView.findViewById(R.id.cardDate);
        location = (TextView) itemView.findViewById(R.id.cardPlace);
        image = (ImageView) itemView.findViewById(R.id.cardPic);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setStartDate(String name) {
        this.startDate.setText(name);
    }

    public void setLocation(String name) {
        this.location.setText(name);
    }

    public void setImage(int resource) {
        this.image.setImageResource(resource);
    }

    public ImageView getImage() {
        return image;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.recyclerViewListClicked(view, getBindingAdapterPosition());
        }
    }
}