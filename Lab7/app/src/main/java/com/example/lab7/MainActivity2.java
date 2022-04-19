package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    final int BIKE_PRICE = 100;
    final int CAR_PRICE = 300;
    final int JEEP_PRICE = 800;
    int RentalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView img = (ImageView)findViewById(R.id.imageView) ;
        TextView result = (TextView) findViewById(R.id.textView5);


        SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);
        int persons = sp1.getInt("people1",0);
        int day = sp1.getInt("days1",0);

        if(persons<=2){
            RentalPrice = BIKE_PRICE * day;
            img.setImageResource(R.drawable.bike1);
            result.setText("Total price is $"+RentalPrice);
        }

        else if(persons==3 || persons==4){
            RentalPrice = CAR_PRICE * day;
            img.setImageResource(R.drawable.car2);
            result.setText("Total price is $"+RentalPrice);
        }

        else if(persons>4){
            RentalPrice = JEEP_PRICE * day;
            img.setImageResource(R.drawable.jeep);
            result.setText("Total price is $"+RentalPrice);}


    }
}