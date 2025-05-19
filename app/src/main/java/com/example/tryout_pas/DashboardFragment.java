package com.example.tryout_pas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.rvTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamAdapter = new TeamAdapter();
        recyclerView.setAdapter(teamAdapter);

        // Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")  // Base URL for SportDB API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API instance
        SportsDBApi api = retrofit.create(SportsDBApi.class);

        // Make the API request
        api.getTeams().enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    // Update the RecyclerView with the list of teams
                    teamAdapter.setTeams(response.body().getTeams());
                } else {
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("DashboardFragment", "API call failed: " + t.getMessage());
            }
        });

        return view;
    }
}
