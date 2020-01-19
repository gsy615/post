package com.gz.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SocketClient.connect("wss://ws.mostonetech.com");
            }
        }).start();
    }
}
