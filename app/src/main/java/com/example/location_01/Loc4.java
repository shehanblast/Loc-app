package com.example.location_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

public class Loc4 extends AppCompatActivity implements LocationListener {

    Button btLocation,nxt1;
    TextView tv_lt1,tv_lg1,tv_lt2,tv_lg2,d1,d2;
    private String dstance1,lon3,lat3,lon4,lat4;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc4);

        dstance1 = getIntent().getStringExtra("distance1");
        lat3 = getIntent().getStringExtra("lat3");
        lon3 = getIntent().getStringExtra("lon3");

        btLocation = findViewById(R.id.bt_location3);
        nxt1 = findViewById(R.id.nxt1);
        tv_lt1 = findViewById(R.id.tv_lt1);
        tv_lg1 = findViewById(R.id.tv_lg1);
        d1 = findViewById(R.id.distance1);

        tv_lt2 = findViewById(R.id.tv_lt2);
        tv_lg2 = findViewById(R.id.tv_lg2);
        d2 = findViewById(R.id.distance2);

        tv_lt1.setText(lat3);
        tv_lg1.setText(lon3);
        d1.setText(dstance1);

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(Loc4.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Loc4.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        nxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate();
            }
        });

    }

    private void navigate() {

        Intent intent = new Intent(Loc4.this,Result.class);
        intent.putExtra("distance1",dstance1);
        intent.putExtra("lat3",lat3);
        intent.putExtra("lon3",lon3);
        intent.putExtra("lat4",lat4);
        intent.putExtra("lon4",lon4);
        startActivity(intent);

    }

    @Override
    public void onLocationChanged(Location location) {

        tv_lt2.setText(String.valueOf(location.getLatitude()));
        tv_lg2.setText(String.valueOf(location.getLongitude()));

        lon4 = String.valueOf(location.getLongitude());
        lat4 = String.valueOf(location.getLatitude());

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this::onLocationChanged);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}