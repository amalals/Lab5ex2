package com.example.assignment3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class MainActivity extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=21.48584&lon=39.1925&appid=5b34c288d82869579f2ee0de90e43141";
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity, feels_like, sunrise_t, sunset_t;
    Spinner city;
    Button btnChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // JSON object that contains weather information

        //link graphical items to variables
        temperature = findViewById(R.id.temperature);
        description = findViewById(R.id.description);
        feels_like = findViewById(R.id.feels_like);
        humidity = findViewById(R.id.humidity);
        sunrise_t = findViewById(R.id.Sunrise);
        sunset_t = findViewById(R.id.sunset);
        city = findViewById(R.id.cities);
        btnChange = findViewById(R.id.button);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clicked;
                clicked = city.getSelectedItem().toString();

                switch (clicked) {
                    case "Jeddah":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=21.48584&lon=39.1925&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;
                    case "Mecca":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=21.3891&lon=39.8579&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;
                    case "Dammam":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=26.4207&lon=50.0888&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;

                }
            }
        });

        weather(url);
        weatherBackground = findViewById(R.id.weatherbackground);

    }


    public void weather(String url){
        @SuppressLint("SetTextI18n") JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Log.d("Amal", "Response Received");
            Log.d("Amal", response.toString());
            try {
                JSONObject jsonMain = response.getJSONObject("main");
                JSONObject jsonSys = response.getJSONObject("sys");

                double temp = jsonMain.getDouble("temp");
                Log.d("Amal","temp=" + temp);
                temperature.setText(String.valueOf(temp)+"°C");

                double feels = jsonMain.getDouble("feels_like");
                Log.d("Amal","feels_like=" + feels);
                feels_like.setText("Feels Like: "+String.valueOf(feels)+"°C");

                double humid = jsonMain.getDouble("humidity");
                Log.d("Amal","humidity=" + feels);
                humidity.setText("Humidity: "+String.valueOf(humid));

                String townResponse = response.getString("name");
                description.setText(townResponse);

                long sunrise = jsonSys.getLong("sunrise");
                long sunset = jsonSys.getLong("sunset");

                Date sunriseDate = new Date(1000*sunrise);
                String sunriseTime = new SimpleDateFormat("H:mm").format(sunriseDate);
                Date sunsetDate = new Date(1000*sunset);
                String sunsetTime = new SimpleDateFormat("H:mm").format(sunsetDate);


                Log.d("Amal", "sunrise= "+sunrise);
                Log.d("Amal", "sunset= "+sunset);
                sunrise_t.setText("Sunrise: "+ String.valueOf(sunriseTime));
                sunset_t.setText("Sunset: "+ String.valueOf(sunsetTime));


                JSONArray jsonArray = response.getJSONArray("weather");
                for (int i=0; i<jsonArray.length();i++){
                    Log.d("Amal-array",jsonArray.getString(i));
                    JSONObject oneObject = jsonArray.getJSONObject(i);
                    String weather =
                            oneObject.getString("main");
                    Log.d("Amal-w",weather);

                    switch (weather) {
                        case "Clear":
                            Glide.with(MainActivity.this)
                                    .load("https://torange.biz/photo/20/HD/sky-blue-clear-20213.jpg")
                                    .into(weatherBackground);
                            break;
                        case "Clouds":
                            Glide.with(MainActivity.this)
                                    .load("https://media.istockphoto.com/photos/blue-sky-with-white-clouds-nature-background-picture-id1125295327?k=20&m=1125295327&s=612x612&w=0&h=KAs_-jMfnsUQ1W4P1Uus8wX1WV53FFb12-iCFV6EcBY=")
                                    .into(weatherBackground);
                            break;
                        case "Rain":
                            Glide.with(MainActivity.this)
                                    .load("https://travelerofcharleston.com/wp-content/uploads/2013/03/rainy-day.png")
                                    .into(weatherBackground);
                            break;
                    }

                }
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("Receive Error", e.toString());
            }
        }, error -> Log.d("Amal", "Error Retrieving URL"));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}