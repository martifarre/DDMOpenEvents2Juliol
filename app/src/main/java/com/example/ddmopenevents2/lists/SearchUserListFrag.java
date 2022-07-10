package com.example.ddmopenevents2.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddmopenevents2.DetailsEvents;
import com.example.ddmopenevents2.DetailsUser;
import com.example.ddmopenevents2.R;
import com.example.ddmopenevents2.business.User;
import com.example.ddmopenevents2.communication.OpenEventsAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUserListFrag extends Fragment implements RecyclerViewClickListener {

    private ArrayList<User> users;
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;

    private EditText searchUserName;

    public SearchUserListFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_user, container, false);

        searchUserName = view.findViewById(R.id.searchViewUsers);

        recyclerView = view.findViewById(R.id.usersList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        users = new ArrayList<User>();
        usersAdapter = new UsersAdapter(getContext(), users);
        recyclerView.setAdapter(usersAdapter);
        usersAdapter.setRecycleViewListener(this);

        getAndSetUsers();

        return view;
    }

    private void getAndSetUsers() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.TOKEN_SHARED), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(String.valueOf(R.string.TOKEN_TOKEN),"");

        OpenEventsAPI.getInstance().getAllUsers(token, new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.body() != null) {
                    users.clear();
                    users.addAll(response.body());

                    usersAdapter.notifyDataSetChanged();
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Error accessing API!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.i("GET","EXPLORE EVENTS KO!");
            }
        });
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        int userId = users.get(position).getId();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment = new DetailsUser(userId);
        manager.beginTransaction().replace(R.id.fragment_navigation, fragment).addToBackStack(null).commit();
    }
}