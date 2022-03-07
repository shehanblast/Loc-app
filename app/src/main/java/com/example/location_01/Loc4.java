package com.example.location_01;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Loc4 extends AppCompatActivity {

    Button btLocation,nxt1;
    TextView tv_latitude2,tv_longitude2,tv_lt,tv_lg,tv_lt1,tv_lg1,tv_lt2,tv_lg2;
    private String lon1,lat1,lon2,lat2,lon3,lat3;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc4);

        lat1 = getIntent().getStringExtra("lat1");
        lon1 = getIntent().getStringExtra("lon1");
        lat2 = getIntent().getStringExtra("lat2");
        lon2 = getIntent().getStringExtra("lon2");
        lat3 = getIntent().getStringExtra("lat3");
        lon3 = getIntent().getStringExtra("lon3");

        btLocation = findViewById(R.id.bt_location3);
        nxt1 = findViewById(R.id.nxt1);
        tv_latitude2 = findViewById(R.id.tv_latitude2);
        tv_longitude2 = findViewById(R.id.tv_longitude2);
        tv_lt = findViewById(R.id.tv_lt);
        tv_lg = findViewById(R.id.tv_lg);
        tv_lt1 = findViewById(R.id.tv_lt1);
        tv_lg1 = findViewById(R.id.tv_lg1);

        tv_lt2 = findViewById(R.id.tv_lt2);
        tv_lg2 = findViewById(R.id.tv_lg2);

        tv_latitude2.setText(lat1);
        tv_longitude2.setText(lon1);
        tv_lt.setText(lat2);
        tv_lg.setText(lon2);
        tv_lt1.setText(lat3);
        tv_lg1.setText(lon3);


    }
}