package com.example.smarttravelguideapplication.fragement;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smarttravelguideapplication.R;
import com.example.smarttravelguideapplication.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherFragment extends Fragment {
    EditText CityName;
    Button searchButton;
    TextView result;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_weather, container, false);
        CityName = view.findViewById(R.id.etplaceName);
        CityName.setEnabled(true);
        searchButton = view.findViewById(R.id.searchButton);
        result = view.findViewById(R.id.result);

        dateTimeDisplay = (TextView)view.findViewById(R.id.cureentDatenTime);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWeather();
            }
        });

        return view;
    }

    private void searchWeather() {

        String cName = CityName.getText().toString();
        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://api.openweathermap.org/data/2.5/weather?q="+cName+"&appid=5baf7c79f6786dd8d786e4632d7e68dc").get();
            //First we will check data is retrieve successfully or not
            Log.i("contentData", content);

            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherDATA = jsonObject.getString("weather");
            String mainTemperature = jsonObject.getString("main");// it is not main array . it is seperate vrialble like weather

            String Location;

            double visibility;

            Log.i("weatherDATA", weatherDATA);

            //Weather Data is in Array

            JSONArray array = new JSONArray(weatherDATA);
            String main = "";
            String description = "";
            String temperature = "";
            String humidity = "";

            for (int i = 0; i < array.length(); i++) {
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");
            }

            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            humidity = mainPart.getString("humidity");
            visibility = Double.parseDouble(jsonObject.getString("visibility"));
            //by default visibility is in meter:
            int visibilityinKilometer = (int) visibility / 1000;
            double tempincelcius = Double.parseDouble(temperature) - 273.15 ;
            double tempincelfernheit = (Double.parseDouble(temperature) - 273.15) * 9/5 + 32 ;

            NumberFormat tfc= NumberFormat.getInstance();
            tfc.setMaximumFractionDigits(2);
            String tempfc = tfc.format(tempincelcius);
            String tempff = tfc.format(tempincelfernheit);

//            for location
            Location = jsonObject.getString("name");
            result.setText("Main : " + main +
                    "\n \n" + "Description : " + description +
                    "\n \n" + "Temperature : " + tempfc  + " °C | "+ tempff  +" °F" +
                    "\n \n" + "Visibility : " + visibilityinKilometer + "km" +
                    "\n \n" + "Humidity : " + humidity + "%"+
                    "\n \n" + "Location : " + Location);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}