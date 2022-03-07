package com.example.location_01;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;

public class Loc3 extends AppCompatActivity {

    Button btLocation;
    TextView tv_latitude2,tv_longitude2,tv_lt,tv_lg,tv_lt1,tv_lg1;
    private String lon1,lat1,lon2,lat2;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc3);

        lat1 = getIntent().getStringExtra("lat1");
        lon1 = getIntent().getStringExtra("lon1");
        lat2 = getIntent().getStringExtra("lat2");
        lon2 = getIntent().getStringExtra("lon2");

        btLocation = findViewById(R.id.bt_location3);
        tv_latitude2 = findViewById(R.id.tv_latitude2);
        tv_longitude2 = findViewById(R.id.tv_longitude2);
        tv_lt = findViewById(R.id.tv_lt);
        tv_lg = findViewById(R.id.tv_lg);

        tv_lt1 = findViewById(R.id.tv_lt1);
        tv_lg1 = findViewById(R.id.tv_lg1);

        tv_latitude2.setText(lat1);
        tv_longitude2.setText(lon1);
        tv_lt.setText(lat2);
        tv_lg.setText(lon2);



    }
}