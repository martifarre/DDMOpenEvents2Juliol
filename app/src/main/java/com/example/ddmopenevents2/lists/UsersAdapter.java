package com.example.ddmopenevents2.lists;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ddmopenevents2.R;
import com.example.ddmopenevents2.business.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersHolder> {

    private Context context;
    private ArrayList<User> userArrayList;

    public UsersAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
        holder.setName(userArrayList.get(position).getName());
        holder.setSurname(userArrayList.get(position).getSurname());
        holder.setEmail(userArrayList.get(position).getEmail());
        holder.setImage(R.drawable.profile);

        try {
            Picasso.get()
                    .load(userArrayList.get(position).getImage())
                    .into(holder.getImage(), new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            holder.setImage(R.drawable.profile);
                        }
                    });
        } catch (IllegalArgumentException iae) {
            holder.setImage(R.drawable.profile);
        }
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
