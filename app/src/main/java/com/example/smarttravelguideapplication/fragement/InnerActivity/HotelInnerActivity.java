package com.example.smarttravelguideapplication.fragement.InnerActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttravelguideapplication.R;
import com.squareup.picasso.Picasso;

public class HotelInnerActivity extends AppCompatActivity {

    ImageView imgHotelImage, call_button;
    TextView txt_hotel_name, txt_hotel_Location, txt_hotel_price, txt_hotel_desc, txt_hotel_phNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_inner);

        imgHotelImage = findViewById(R.id.txt_hotel_image);
        txt_hotel_name = findViewById(R.id.txt_hotel_name);
        txt_hotel_Location = findViewById(R.id.txt_hotel_Location);
        txt_hotel_price = findViewById(R.id.txt_hotel_price);
        txt_hotel_desc = findViewById(R.id.txt_hotel_description);
        txt_hotel_phNumber = findViewById(R.id.txt_hotel_phNumber);
        call_button = findViewById(R.id.hotel_call_btn);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Picasso.get().load(bundle.getString("img")).into(imgHotelImage);
            txt_hotel_name.setText(bundle.getString("name"));
            txt_hotel_Location.setText(bundle.getString("location"));
            txt_hotel_price.setText(bundle.getString("price"));
            txt_hotel_desc.setText(bundle.getString("desc"));
            txt_hotel_phNumber.setText(bundle.getString("phnumber"));

            if (bundle.getString("phnumber") == null) {
                call_button.setVisibility(View.GONE);
            } else {
                call_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + bundle.getString("phnumber")));
                        startActivity(callIntent);
                    }
                });

            }
        }
    }
}