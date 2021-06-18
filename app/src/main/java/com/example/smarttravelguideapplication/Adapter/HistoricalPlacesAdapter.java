package com.example.smarttravelguideapplication.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.HistoricalPlaces;
import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.StrictMode.strictmodeclass;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class HistoricalPlacesAdapter extends RecyclerView.Adapter<HistoricalPlacesAdapter.HistoricalPlacesViewHolder>{
    Context context;
    List<HistoricalPlaces> historicalPlacesList;

    public HistoricalPlacesAdapter(Context context, List<HistoricalPlaces> historicalPlacesList) {
        this.context = context;
        this.historicalPlacesList = historicalPlacesList;
    }

    @NonNull
    @Override
    public HistoricalPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hsitorical_places, parent, false);
        return new HistoricalPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricalPlacesViewHolder holder, int position) {

        HistoricalPlaces historicalPlaces = historicalPlacesList.get(position);
        String imgpath = GlobalURL.imagePath + historicalPlaces.getImage();
        Log.e("Image path is :", "Image path is" + imgpath);

        holder.HistoricaplaceName.setText(historicalPlaces.getName());
        holder.historicalLocation.setText(historicalPlaces.getLocation());
        holder.historicalTiming.setText(historicalPlaces.getTiming());
        holder.Entryfee_data.setText(historicalPlaces.getEntryFree());
        holder.HistoricalplaceDec.setText(historicalPlaces.getDescription());
          strictmodeclass.StrictMode();
        try {
            URL url = new URL(imgpath);
            holder.idIVhistorticalPlacesImages.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }


//        double distance=startPoint.distanceTo(endPoint);


        LatLng latLngA = new LatLng(27.7049292,85.265476);
        LatLng latLngB = new LatLng(27.694394,85.281391);

        Location locationA = new Location("point A");
        locationA.setLatitude(latLngA.latitude);
        locationA.setLongitude(latLngA.longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(latLngB.latitude);
        locationB.setLongitude(latLngB.longitude);

        double distance = locationA.distanceTo(locationB);
        double distaceinkm = distance/0.62137;

        DecimalFormat amountFormate  = new DecimalFormat("#####.##");
        amountFormate.setMinimumFractionDigits(2);
        amountFormate.setMaximumFractionDigits(2);
        holder.DistaceData.setText(amountFormate.format(distaceinkm)+" km");


//        holder.DistaceData.setText(" Lat: "+ gll.getLatt() + " and Long: " + gll.getLonn());

    }

    @Override
    public int getItemCount() {
        return historicalPlacesList.size();
    }

    public class HistoricalPlacesViewHolder extends RecyclerView.ViewHolder {

        ImageView idIVhistorticalPlacesImages;
        TextView HistoricaplaceName, historicalLocation, historicalTiming,Entryfee_data, DistaceData,HistoricalplaceDec;

        public HistoricalPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            idIVhistorticalPlacesImages = itemView.findViewById(R.id.idIVhistorticalPlaces);
            HistoricaplaceName = itemView.findViewById(R.id.HistoricaplaceName);
            historicalLocation = itemView.findViewById(R.id.historicalLocation);
            historicalTiming = itemView.findViewById(R.id.historicalPlaces_Timing);
            Entryfee_data = itemView.findViewById(R.id.Entryfee_data);
            DistaceData = itemView.findViewById(R.id.Distance_Data);
            HistoricalplaceDec = itemView.findViewById(R.id.HistoricalplaceDec);


        }
    }

}
