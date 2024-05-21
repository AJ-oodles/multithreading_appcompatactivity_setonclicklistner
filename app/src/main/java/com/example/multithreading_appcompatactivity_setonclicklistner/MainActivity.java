package com.example.multithreading_appcompatactivity_setonclicklistner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
//Appcompatactivity represents the main activity of android app it automatically support action bar in android
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        Button btn = findViewById(R.id.btnStart);

        // Boolean for Button (initially False)
        final boolean[] boolbtn = {false};

     //
        btn.setOnClickListener(v -> {
            // Button (True)
            boolbtn[0] = !boolbtn[0];
            // Case where Button is False
            if (!boolbtn[0]) {
                tv1.setText("Stopped1");
                tv2.setText("Stopped2");
                tv3.setText("Stopped3");
                btn.setText("Start");
            } else {

                btn.setText("Stop");
// running multiple thread on textview
                // Thread 1
                //this is runnable thread:-Java runnable is an interface used to execute code on a concurrent thread. It is an interface which
                // is implemented by any class if we want that the instances of that class should be executed by a thread.

                new Thread(() -> {

                    while (boolbtn[0]) {
                        //runOmUiThread():-This method is used to update the UI from a background thread. In Android,
                        // UI updates must be performed on the main/UI thread.
                        runOnUiThread(() -> tv1.setText("Started1"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(() -> tv1.setText("Activity1"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // Thread 2
                new Thread(() -> {

                    while (boolbtn[0]) {
                        runOnUiThread(() -> tv2.setText("Started2"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(() -> tv2.setText("Activity2"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // Thread 3
                new Thread(() -> {

                    while (boolbtn[0]) {
                        runOnUiThread(() -> tv3.setText("Started3"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(() -> tv3.setText("Activity3"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        //LIFECYCLE OF THREAD:_
        //1:-new
        //2:-Runnable
        //3:-Running
        //4:Blocked
        //waiting
        //TimedWaiting
        //Terminated

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
