package com.example.vize;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button donusturucu;
    Button random;
    Button sms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donusturucu = (Button)findViewById(R.id.donusturucu);
        random = (Button)findViewById(R.id.random);
        sms = (Button)findViewById(R.id.sms);

        donusturucu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donusturmeIntent = new Intent(MainActivity.this, Donusturucu.class);
                startActivity(donusturmeIntent);
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent randomIntent = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(randomIntent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(MainActivity.this, SMS.class);
                startActivity(smsIntent);
            }
        });
    }
}