package com.example.smarttravelguideapplication.fragement.InnerActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttravelguideapplication.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CabDetailInnerActivity extends AppCompatActivity {
    ImageView cabImage;
    CircleImageView driverImage;
    TextView drivername, drivernumber, price, driverDesc, cabname, cabmodel, cabdesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_detail_inner);


//        driverinfor
        driverImage = findViewById(R.id.driver_inner_Image);
        drivername = findViewById(R.id.driver_inner_name);
        drivernumber = findViewById(R.id.driver_inner_contactnumber);
        price = findViewById(R.id.driver_inner_price);
        driverDesc = findViewById(R.id.driver_inner_desc);

        //cab Details
        cabImage = findViewById(R.id.cab_inner_Image);
        cabname = findViewById(R.id.cab_inner_Name);
        cabmodel = findViewById(R.id.cab_inner_model);
        cabdesc = findViewById(R.id.cab_inner_desc);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            //driver Details
            Picasso.get().load(bundle.getString("Driverimg")).into(driverImage);
            drivername.setText(bundle.getString("drivername"));
            drivernumber.setText(bundle.getString("drivercontactnumber"));
            price.setText(bundle.getString("driverprice"));
            driverDesc.setText(bundle.getString("driverdesc"));

            // cab details
            Picasso.get().load(bundle.getString("Cabimg")).into(cabImage);
            cabname.setText(bundle.getString("cabName"));
            cabmodel.setText(bundle.getString("cabModel"));
            cabdesc.setText(bundle.getString("cabDesc"));

        }



    }
}