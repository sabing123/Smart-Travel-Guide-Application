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

import com.example.smarttravelguideapplication.Adapter.AdventrousPlaces_Apdater;
import com.example.smarttravelguideapplication.GlobalAPI.GlobalAPI;
import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.AdventrousPlaces_Model;
import com.example.smarttravelguideapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdventurousPlacesFragment extends Fragment {

    RecyclerView Adventrousplace_RecycleView;
    List<AdventrousPlaces_Model> adventrousPlaces;
    AdventrousPlaces_Apdater adventrousPlaces_apdater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adventurous_places, container, false);


        Adventrousplace_RecycleView = view.findViewById(R.id.adventrousPlaces_RecycleView);

        GetAdventrousPlacesInfo();
        return view;
    }

    private void GetAdventrousPlacesInfo() {

        adventrousPlaces = new ArrayList<>();
        GlobalAPI globalAPI = GlobalURL.getInstance().create(GlobalAPI.class);
        Call<List<AdventrousPlaces_Model>> callList = globalAPI.getadventrousPlaces();
        callList.enqueue(new Callback<List<AdventrousPlaces_Model>>() {
            @Override
            public void onResponse(Call<List<AdventrousPlaces_Model>> call, Response<List<AdventrousPlaces_Model>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: Server is not Responding ", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<AdventrousPlaces_Model> adventrousPlaces_models = response.body();
                adventrousPlaces_apdater = new AdventrousPlaces_Apdater(getContext(), adventrousPlaces_models);
                Adventrousplace_RecycleView.setAdapter(adventrousPlaces_apdater);
                Adventrousplace_RecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<AdventrousPlaces_Model>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error : No Network Access",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}