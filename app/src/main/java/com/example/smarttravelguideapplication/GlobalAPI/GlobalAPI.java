package com.example.smarttravelguideapplication.GlobalAPI;

import com.example.smarttravelguideapplication.Model.AdventrousPlaces_Model;
import com.example.smarttravelguideapplication.Model.HistoricalPlaces;
import com.example.smarttravelguideapplication.Model.HotelModel;
import com.example.smarttravelguideapplication.Model.cabinfoMOdel;
import com.example.smarttravelguideapplication.Model.touristguideModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//  path
public interface GlobalAPI {
    @GET("historicalplace")
    Call<List<HistoricalPlaces>> getHistoricalPlaces();

    @GET("adventurousplace")
    Call<List<AdventrousPlaces_Model>> getadventrousPlaces();

    @GET("hotel")
    Call<List<HotelModel>> getHotelInfo();

    @GET("touristguide")
    Call<List<touristguideModel>> getGuideInfo();

    @GET("cabdetail")
    Call<List<cabinfoMOdel>> getCabInfo();
}
