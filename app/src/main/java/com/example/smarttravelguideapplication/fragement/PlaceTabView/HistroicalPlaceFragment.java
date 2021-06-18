package com.example.smarttravelguideapplication.fragement.PlaceTabView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smarttravelguideapplication.Adapter.HistoricalPlacesAdapter;
import com.example.smarttravelguideapplication.GlobalAPI.GlobalAPI;
import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.HistoricalPlaces;
import com.example.smarttravelguideapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistroicalPlaceFragment extends Fragment {

    RecyclerView HistoricaplaceRecycleView;
    List<HistoricalPlaces> historicalPlaces;
    HistoricalPlacesAdapter historicalPlacesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_histroical_place, container, false);
        HistoricaplaceRecycleView = view.findViewById(R.id.HistoricaplaceRecycleView);

        GetHistroicalInfo();



        return view;
    }

    private void GetHistroicalInfo() {

        historicalPlaces = new ArrayList<>();
        GlobalAPI globalAPI = GlobalURL.getInstance().create(GlobalAPI.class);
        Call<List<HistoricalPlaces>> listCall = globalAPI.getHistoricalPlaces();

        listCall.enqueue(new Callback<List<HistoricalPlaces>>() {
            @Override
            public void onResponse(Call<List<HistoricalPlaces>> call, Response<List<HistoricalPlaces>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: Server is not Responding ", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<HistoricalPlaces> historicalPlaces1 = response.body();
                historicalPlacesAdapter = new HistoricalPlacesAdapter(getContext(), historicalPlaces1);
                HistoricaplaceRecycleView.setAdapter(historicalPlacesAdapter);
                HistoricaplaceRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<List<HistoricalPlaces>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error : No Network Access",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}