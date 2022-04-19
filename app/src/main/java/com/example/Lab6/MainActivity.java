package com.example.Lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    MediaPlayer azan;
    int control = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        azan = new MediaPlayer();
        azan = MediaPlayer.create(this, R.raw.athan);
        result = (TextView)findViewById(R.id.text);
        Button btn = (Button)findViewById(R.id.b);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (control) {
                    case 0:
                        azan.start();
                        control = 1;
                        result.setText("Azan is started");
                        break;
                    case 1:
                        azan.pause();
                        control = 0;
                        result.setText("Azan is paused");
                        break;
                }
            }
        });
    }
}