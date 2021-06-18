package com.example.smarttravelguideapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.HotelModel;
import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.StrictMode.strictmodeclass;
import com.example.smarttravelguideapplication.fragement.InnerActivity.HotelInnerActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    Context context;
    List<HotelModel> hotelModels;

    public HotelAdapter(Context context, List<HotelModel> hotelModels) {
        this.context = context;
        this.hotelModels = hotelModels;

    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
       final HotelModel hotel = hotelModels.get(position);
        String imgpath = GlobalURL.imagePath + hotel.getImage();
        Log.e("Image path is :", "Image path is" + imgpath);

        holder.hotelname.setText(hotel.getName());
        holder.hotelLocation.setText(hotel.getLocation());

        strictmodeclass.StrictMode();
        try {
            URL url = new URL(imgpath);
            holder.HotelImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    URL url = new URL(imgpath);

                    Intent intent = new Intent(context, HotelInnerActivity.class);
                    intent.putExtra("img", imgpath);
                    intent.putExtra("name", hotel.getName());
                    intent.putExtra("location", hotel.getLocation());
                    intent.putExtra("price", hotel.getPrice());
                    intent.putExtra("desc", hotel.getDescription());
                    intent.putExtra("phnumber", hotel.getContactnumber());
                    context.startActivity(intent);

                }
                catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });




    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;

        ImageView HotelImage;
        TextView hotelname, hotelLocation;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            HotelImage = itemView.findViewById(R.id.HotelImage);
            hotelname = itemView.findViewById(R.id.HotelName);
            hotelLocation = itemView.findViewById(R.id.hotelLocation);

            relativeLayout = itemView.findViewById(R.id.innerrelative);

        }
    }


}
