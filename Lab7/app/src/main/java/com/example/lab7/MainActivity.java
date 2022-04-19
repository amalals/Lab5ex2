package com.example.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nPeople = (EditText) findViewById(R.id.numOfPpl);
        final EditText license = (EditText) findViewById(R.id.validity);
        final EditText nDays = (EditText) findViewById(R.id.numOfDays);
        final Button button = (Button) findViewById(R.id.button);
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int licenseVal= Integer.parseInt(license.getText().toString());
                if (licenseVal < 1) {
                    Toast.makeText(MainActivity.this,
                            "Your Drivers License will expire soon.   "
                                    +"Please renew it first then visit us back :)", Toast.LENGTH_LONG).show();
                    return;
                }

                int valPeople = Integer.parseInt(nPeople.getText().toString());
                int valDays = Integer.parseInt(nDays.getText().toString());
                SharedPreferences.Editor editor= sp.edit();
                editor.putInt("people1",valPeople);
                editor.putInt("days1", valDays);
                editor.commit();

                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });
    }
}