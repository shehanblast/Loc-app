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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;

public class Loc3 extends AppCompatActivity implements LocationListener {

    Button btLocation,nxt1;
    TextView tv_latitude2,tv_longitude2,tv_lt,tv_lg,tv_lt1,tv_lg1;
    private String lon1,lat1,lon2,lat2,lon3,lat3;
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
        nxt1 = findViewById(R.id.nxt1);
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

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(Loc3.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Loc3.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create method
                getLocation();
            }
        });

        nxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chg();
            }
        });



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


    @Override
    public void onLocationChanged(Location location) {
        //        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        tv_lt1.setText(String.valueOf(location.getLatitude()));
        tv_lg1.setText(String.valueOf(location.getLongitude()));

        lon3 = String.valueOf(location.getLongitude());
        lat3 = String.valueOf(location.getLatitude());

    }

    private void chg() {

        Intent intent = new Intent(Loc3.this,Loc4.class);
        intent.putExtra("lat1",lat1);
        intent.putExtra("lon1",lon1);
        intent.putExtra("lat2",lat2);
        intent.putExtra("lon2",lon2);
        intent.putExtra("lat3",lat3);
        intent.putExtra("lon3",lon3);
        startActivity(intent);

    }
}