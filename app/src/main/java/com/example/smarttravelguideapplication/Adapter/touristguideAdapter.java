package com.example.smarttravelguideapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.Model.touristguideModel;
import com.example.smarttravelguideapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class touristguideAdapter extends RecyclerView.Adapter<touristguideAdapter.touristguideViewHolder>{
    Context context;
    List<touristguideModel> touristguideModels;

    public touristguideAdapter(Context context, List<touristguideModel> touristguideModels) {
        this.context = context;
        this.touristguideModels = touristguideModels;

    }

    @NonNull
    @Override
    public touristguideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tourist_guide, parent, false);
        return new touristguideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull touristguideViewHolder holder, int position) {

        final touristguideModel touristguideModel = touristguideModels.get(position);
        String imgpath = GlobalURL.imagePath + touristguideModel.getImage();
        Picasso.get().load(imgpath).into(holder.guideImg);

        holder.guidename.setText(touristguideModel.getName());
        holder.guideaddress.setText(touristguideModel.getAddress());
        holder.guidecontact.setText(touristguideModel.getContact());
        holder.guidelanguage.setText(touristguideModel.getLanguage());
        holder.guidedesc.setText(touristguideModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return touristguideModels.size();
    }

    public class touristguideViewHolder extends RecyclerView.ViewHolder{
        ImageView guideImg;
        TextView guidename, guideaddress,guidecontact,guidelanguage,guidedesc;

        public touristguideViewHolder(@NonNull View itemView) {
            super(itemView);
            guideImg = itemView.findViewById(R.id.guideimg);
            guidename = itemView.findViewById(R.id.guidename);
            guideaddress = itemView.findViewById(R.id.guideaddress);
            guidecontact = itemView.findViewById(R.id.guidecontact);
            guidelanguage = itemView.findViewById(R.id.guidelanguage);
            guidedesc = itemView.findViewById(R.id.guidedesc);

        }
    }
}
