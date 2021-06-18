package com.example.smarttravelguideapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.cabinfoMOdel;
import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.fragement.InnerActivity.CabDetailInnerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CabDetailAdapter extends RecyclerView.Adapter<CabDetailAdapter.CabDetailViewHolder>  {


    Context context;
    List<cabinfoMOdel> cabinfoMOdels;

    public CabDetailAdapter(Context context, List<cabinfoMOdel> cabinfoMOdels) {
        this.context = context;
        this.cabinfoMOdels = cabinfoMOdels;

    }
    @NonNull
    @Override
    public CabDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cab_info, parent, false);
        return new CabDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CabDetailViewHolder holder, int position) {
    final cabinfoMOdel cabinfoModel  = cabinfoMOdels.get(position);
        String Driverimgpath = GlobalURL.imagePath + cabinfoModel.getDriverimage();
        String Cabimgpath = GlobalURL.imagePath + cabinfoModel.getCabImage();

        Picasso.get().load(Driverimgpath).into(holder.profile_image);

        holder.drivername.setText(cabinfoModel.getName());
        holder.cabName.setText(cabinfoModel.getCabname());
        holder.cabmodel.setText(cabinfoModel.getCabmodel());

        holder.more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, CabDetailInnerActivity.class);

//                    driver detail
                    intent.putExtra("Driverimg", Driverimgpath);
                    intent.putExtra("drivername", cabinfoModel.getName());
                    intent.putExtra("drivercontactnumber", cabinfoModel.getContactnumber());
                    intent.putExtra("driverprice", cabinfoModel.getPrice());
                    intent.putExtra("driverdesc", cabinfoModel.getDescription());

                    //cab details
                    intent.putExtra("Cabimg", Cabimgpath);
                    intent.putExtra("cabName", cabinfoModel.getCabname());
                    intent.putExtra("cabModel", cabinfoModel.getCabmodel());
                    intent.putExtra("cabDesc", cabinfoModel.getCabdescription());

                    context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return cabinfoMOdels.size();
    }

    public class CabDetailViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image;
        TextView drivername, cabName,cabmodel;
        ImageView more_btn;


        public CabDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            drivername = itemView.findViewById(R.id.drivername);
            cabName = itemView.findViewById(R.id.cabName);
            cabmodel = itemView.findViewById(R.id.cabmodel);
            more_btn = itemView.findViewById(R.id.more_btn);

        }
    }
}


