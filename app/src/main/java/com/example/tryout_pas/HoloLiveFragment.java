package com.example.tryout_pas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryout_pas.adapter.HoloAdapter;
import com.example.tryout_pas.api.ApiService;
import com.example.tryout_pas.api.ApiClient;
import com.example.tryout_pas.model.HoloMember;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoloLiveFragment extends Fragment {
    RecyclerView rvHololive;
    SearchView searchView;
    HoloAdapter adapter;
    List<HoloMember> holoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hololive, container, false);

        rvHololive = view.findViewById(R.id.rvHololive);
        searchView = view.findViewById(R.id.searchView);

        rvHololive.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HoloAdapter(getContext(), holoList);
        rvHololive.setAdapter(adapter);

        setupSearchView();
        fetchData();

        return view;
    }

    private void fetchData() {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        api.getHoloMembers().enqueue(new Callback<List<HoloMember>>() {
            @Override
            public void onResponse(Call<List<HoloMember>> call, Response<List<HoloMember>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    holoList = response.body();
                    adapter.updateData(holoList); // update adapter + simpan list asli
                } else {
                    Toast.makeText(getContext(), "Gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HoloMember>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSearchView() {
        searchView.setQueryHint("Cari nama atau generasi...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }
}
