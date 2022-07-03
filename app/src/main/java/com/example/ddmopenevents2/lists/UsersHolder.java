package com.example.ddmopenevents2.lists;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ddmopenevents2.R;
import androidx.recyclerview.widget.RecyclerView;

public class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView name;
    private TextView surname;
    private TextView email;

    private ImageView image;

    public UsersHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        name = (TextView) itemView.findViewById(R.id.userName);
        surname = (TextView) itemView.findViewById(R.id.userLastName);
        email = (TextView) itemView.findViewById(R.id.userEmail);
        image = (ImageView) itemView.findViewById(R.id.userPic);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setSurname(String name) {
        this.surname.setText(name);
    }

    public void setEmail(String name) {
        this.email.setText(name);
    }

    public void setImage(int resource) {
        this.image.setImageResource(resource);
    }

    public ImageView getImage() {
        return image;
    }

    @Override
    public void onClick(View view) {
    }

}
