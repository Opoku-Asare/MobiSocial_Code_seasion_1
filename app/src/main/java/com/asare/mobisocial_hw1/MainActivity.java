package com.asare.mobisocial_hw1;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Vibrator vibratorIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alert = (Button) findViewById(R.id.button);
        alert.setOnClickListener(new EventHandlers());
    }


    private class EventHandlers implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                vibratorIntent = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // This example will cause the phone to vibrate "SOS" in Morse Code
                // In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
                // There are pauses to separate dots/dashes, letters, and words
                // The following numbers represent millisecond lengths
                int dot = 200;      // Length of a Morse Code "dot" in milliseconds
                int dash = 500;     // Length of a Morse Code "dash" in milliseconds
                int short_gap = 200;    // Length of Gap Between dots/dashes
                int medium_gap = 500;   // Length of Gap Between Letters
                int long_gap = 1000;    // Length of Gap Between Words

                long[] morseCodePartern = {
                        0,  // Start immediately
                        dot, short_gap, dot, short_gap, dot,    // s
                        medium_gap,
                        dash, short_gap, dash, short_gap, dash, // o
                        medium_gap,
                        dot, short_gap, dot, short_gap, dot,    // s
                        long_gap
                };
                for (int i = 0; i < 3; i++) {
                    vibratorIntent.vibrate(morseCodePartern, -1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        if (vibratorIntent != null) {
            vibratorIntent.cancel();
        }
        super.onDestroy();
    }
}
