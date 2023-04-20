package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.Handler;
import android.os.Bundle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button start,stop,reset;
    TextView txtcounter;
    int seconds=0;
    Handler customHandler=new Handler();
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.start_button);
        stop=findViewById(R.id.stop_button);
        txtcounter=findViewById(R.id.time_view);
        reset=findViewById(R.id.reset_button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customHandler.postDelayed(updatetimer,0);
                running=true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customHandler.removeCallbacks(updatetimer);
                running=false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seconds=0;
                txtcounter.setText("00:00:00");
            }
        });
    }
    private final Runnable updatetimer=new Runnable() {
        @Override
        public void run() {
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;
            String time
                    = String
                    .format(Locale.getDefault(),
                            "%02d:%02d:%02d", hours,
                            minutes, secs);
            txtcounter.setText(time);
            if (running)
            {
                seconds++;
            }
            customHandler.postDelayed(this, 1000);
        }
    };
}


