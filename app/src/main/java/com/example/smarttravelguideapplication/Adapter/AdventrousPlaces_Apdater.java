package com.example.smarttravelguideapplication.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.AdventrousPlaces_Model;
import com.example.smarttravelguideapplication.Model.HistoricalPlaces;
import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.StrictMode.strictmodeclass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AdventrousPlaces_Apdater extends RecyclerView.Adapter<AdventrousPlaces_Apdater.AdventrousPlacesViewHolder>  {


    Context context;
    List<AdventrousPlaces_Model> AdventrousplaceList;


    public AdventrousPlaces_Apdater(Context context, List<AdventrousPlaces_Model> AdventrousplaceList) {
        this.context = context;
        this.AdventrousplaceList = AdventrousplaceList;
    }


    @NonNull
    @Override
    public AdventrousPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adventurous_place, parent, false);
        return new AdventrousPlaces_Apdater.AdventrousPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdventrousPlacesViewHolder holder, int position) {

        AdventrousPlaces_Model adventrousPlacesModel = AdventrousplaceList.get(position);
        String imgpath = GlobalURL.imagePath + adventrousPlacesModel.getImage();
        Log.e("Image path is :", "Image path is" + imgpath);

        holder.AdventrousPlacesName.setText(adventrousPlacesModel.getName());
        holder.AdventrousPlacesLocation.setText(adventrousPlacesModel.getLocation());
        holder.AdventrousPlacesPrice.setText(adventrousPlacesModel.getPrice());
        holder.AdventrousPlacesTiming.setText(adventrousPlacesModel.getBestTimeToVisit());
        holder.AdventrousPlacesdec.setText(adventrousPlacesModel.getDescription());

        strictmodeclass.StrictMode();
        try {
            URL url = new URL(imgpath);
            holder.AdventrousPlacesImages.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return AdventrousplaceList.size();
    }

    public class AdventrousPlacesViewHolder extends RecyclerView.ViewHolder {

        ImageView AdventrousPlacesImages;
        TextView AdventrousPlacesName, AdventrousPlacesLocation, AdventrousPlacesPrice,AdventrousPlacesTiming, AdventrousPlacesdec;
        public AdventrousPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            AdventrousPlacesImages = itemView.findViewById(R.id.Image_adventrousPlaces);
            AdventrousPlacesName = itemView.findViewById(R.id.AdventrousPlace_Name);
            AdventrousPlacesLocation = itemView.findViewById(R.id.AdventrousPlaces_Location);
            AdventrousPlacesPrice = itemView.findViewById(R.id.AdventrousPlaces_Price);
            AdventrousPlacesTiming = itemView.findViewById(R.id.AdventrousPlaces_Timing_data);
            AdventrousPlacesdec = itemView.findViewById(R.id.AdventrousPlaces_Dec);

        }
    }


}
