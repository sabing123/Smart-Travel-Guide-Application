package com.example.smarttravelguideapplication.fragement;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttravelguideapplication.Adapter.CabDetailAdapter;
import com.example.smarttravelguideapplication.Adapter.touristguideAdapter;
import com.example.smarttravelguideapplication.GlobalAPI.GlobalAPI;
import com.example.smarttravelguideapplication.GlobalURL.GlobalURL;
import com.example.smarttravelguideapplication.LoginSystem.LoginActivity;
import com.example.smarttravelguideapplication.MainActivity;
import com.example.smarttravelguideapplication.Model.cabinfoMOdel;
import com.example.smarttravelguideapplication.Model.touristguideModel;
import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.Weather;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements LocationListener {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    RecyclerView touristGuideRecycleview;
    List<touristguideModel> touristguideModelList;
    com.example.smarttravelguideapplication.Adapter.touristguideAdapter touristguideAdapter;

    RecyclerView cabdetailRecycleview;
    List<cabinfoMOdel> cabinfoModels;
    CabDetailAdapter cabDetailAdapter;


    CarouselView carouselView;
    String latitude, longitude;
    TextView txttemp, txthumi, txtvisibility, txtdesc, txtlocation,c_user_name,c_user_location,c_user_email;
    ImageView imageicon, img_logut;


    protected LocationManager locationManager;
    protected Context context;

    int[] sampleImages = {R.drawable.nepal, R.drawable.buddhist, R.drawable.stupa};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        c_user_name = view.findViewById(R.id.c_username);
        c_user_location = view.findViewById(R.id.c_userlocation);
        c_user_email = view.findViewById(R.id.c_useremail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        DocumentReference docref = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    c_user_name.setText(documentSnapshot.getString("FirstName") + documentSnapshot.getString("LastName"));
                    c_user_email.setText(documentSnapshot.getString("Emailaddress"));
                    c_user_location.setText(documentSnapshot.getString("Location"));
                }
            }
        });

        txttemp = (TextView) view.findViewById(R.id.txtmaintemp);
        txtlocation = (TextView) view.findViewById(R.id.txtlocation);

        imageicon = (ImageView) view.findViewById(R.id.imgicon);
        img_logut = (ImageView) view.findViewById(R.id.img_logout);

        img_logut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Log Out!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();



            }
        });

        carouselView = view.findViewById(R.id.carouselView);


        imageSlider();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);


        touristGuideRecycleview = view.findViewById(R.id.touristGuideRecycleview);
        touristGuideinfo();

        cabdetailRecycleview = view.findViewById(R.id.cabdetailRecycleview);
        cabdetail();

        return view;
    }

    private void cabdetail() {

        cabinfoModels = new ArrayList<>();
        GlobalAPI globalAPI = GlobalURL.getInstance().create(GlobalAPI.class);
        Call<List<cabinfoMOdel>> listCall = globalAPI.getCabInfo();
        listCall.enqueue(new Callback<List<cabinfoMOdel>>() {
            @Override
            public void onResponse(Call<List<cabinfoMOdel>> call, Response<List<cabinfoMOdel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: Server is not Responding ", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<cabinfoMOdel> cabinfoModelList = response.body();
                cabDetailAdapter = new CabDetailAdapter(getContext(), cabinfoModelList);
                cabdetailRecycleview.setAdapter(cabDetailAdapter);
                cabdetailRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<cabinfoMOdel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error : No Network Access",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void touristGuideinfo() {


        touristguideModelList = new ArrayList<>();
        GlobalAPI globalAPI = GlobalURL.getInstance().create(GlobalAPI.class);
        Call<List<touristguideModel>> calllist = globalAPI.getGuideInfo();

        calllist.enqueue(new Callback<List<touristguideModel>>() {
            @Override
            public void onResponse(Call<List<touristguideModel>> call, Response<List<touristguideModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: Server is not Responding ", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<touristguideModel> touristguideModels = response.body();
                touristguideAdapter = new touristguideAdapter(getContext(), touristguideModels);
                touristGuideRecycleview.setAdapter(touristguideAdapter);
                touristGuideRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<touristguideModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error : No Network Access",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void imageSlider() {
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), sampleImages[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

   @Override
    public void onLocationChanged(@NonNull Location location) {
        // textview2.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        double lat = location.getLatitude();
        double longi = location.getLongitude();
        latitude = String.valueOf(lat);
        longitude = String.valueOf(longi);
        result(latitude, longitude);
    }

    private void result(String latitude, String longitude) {
        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=8db667a5e1bec89d9846ec74f98d1d9f").get();
            //content = weather.execute("http://api.openweathermap.org/data/2.5/weather?lat=27.700769&lon=85.300140&appid=8db667a5e1bec89d9846ec74f98d1d9f").get();
            //  Log.i("contentData", content);
            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherDATA = jsonObject.getString("weather");
            String mainTemperature = jsonObject.getString("main");// it is not main array . it is seperate vrialble like weather
            String Location;
            double visibility;
            // Log.i("weatherDATA", weatherDATA);

//            /Weather Data is in Array
            JSONArray array = new JSONArray(weatherDATA);
            String main = "";
            String description = "";
            String temperature = "";
            String humidity = "";
            String icon = "";

            for (int i = 0; i < array.length(); i++) {
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");
                icon = weatherPart.getString("icon");
            }
            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            humidity = mainPart.getString("humidity");
            visibility = Double.parseDouble(jsonObject.getString("visibility"));
            //by default visibility is in meter:
            int visibilityinKilometer = (int) visibility / 1000;

            double tempincelcius = Double.parseDouble(temperature) - 273.15;
            double tempincelfernheit = (Double.parseDouble(temperature) - 273.15) * 9 / 5 + 32;

//            for location
            Location = jsonObject.getString("name");
            txtlocation.setText(Location);


            DecimalFormat amountFormate = new DecimalFormat("##.#");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(1);
            txttemp.setText(amountFormate.format(tempincelcius) + " °C | " + amountFormate.format(tempincelfernheit) + " °F");


//           txttemp.setText(tempincelcius + " °C | " + tempincelfernheit + " °F");
            txthumi.setText(humidity + "%");
            txtvisibility.setText(visibilityinKilometer + "km");
            txtdesc.setText(description);
            Picasso.get().load("http://openweathermap.org/img/w/" + icon + ".png").into(imageicon);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}