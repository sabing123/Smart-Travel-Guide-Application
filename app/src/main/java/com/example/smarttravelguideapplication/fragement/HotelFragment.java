package com.example.smarttravelguideapplication.fragement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smarttravelguideapplication.Adapter.HotelAdapter;
import com.example.smarttravelguideapplication.GlobalAPI.GlobalAPI;
import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.HotelModel;
import com.example.smarttravelguideapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragment extends Fragment {
    RecyclerView hotelRecycle;
    List<HotelModel> hotelModelList;
    HotelAdapter hotelAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);

        hotelRecycle = view.findViewById(R.id.hotelRecycleView);

        getHotelInfo();

        return view;
    }

    private void getHotelInfo() {
        hotelModelList = new ArrayList<>();

        GlobalAPI globalAPI = GlobalURL.getInstance().create(GlobalAPI.class);
        Call<List<HotelModel>> listCall = globalAPI.getHotelInfo();
        listCall.enqueue(new Callback<List<HotelModel>>() {
            @Override
            public void onResponse(Call<List<HotelModel>> call, Response<List<HotelModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: Server is not Responding ", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<HotelModel> hotelModelList1 = response.body();
                hotelAdapter = new HotelAdapter(getContext(), hotelModelList1);
                hotelRecycle.setAdapter(hotelAdapter);
                hotelRecycle.setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false));


            }

            @Override
            public void onFailure(Call<List<HotelModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error : No Network Access",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

}