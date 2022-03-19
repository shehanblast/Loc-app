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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;

public class Loc3 extends AppCompatActivity implements LocationListener {

    Button btLocation,nxt1,nxt2;
    TextView tv_latitude2,tv_longitude2,tv_lt,tv_lg,tv_lt1,tv_lg1,ff;
    private String lon1,lat1,lon2,lat2,lon3,lat3,p;
    LocationManager locationManager;
    private int distance;
    private double lol,loll,lol1,loll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc3);

        lat1 = getIntent().getStringExtra("lat1");
        lon1 = getIntent().getStringExtra("lon1");
        lat2 = getIntent().getStringExtra("lat2");
        lon2 = getIntent().getStringExtra("lon2");

        lol = Double.valueOf(lat1);
        loll = Double.valueOf(lon1);
        lol1 = Double.valueOf(lat2);
        loll1 = Double.valueOf(lon2);

        Log.i("gg",lat1);
        Log.i("gg",lon1);
        Log.i("gg",lat2);
        Log.i("gg",lon2);

        btLocation = findViewById(R.id.bt_location3);
        nxt1 = findViewById(R.id.nxt1);
        ff = findViewById(R.id.ff);
        nxt2 = findViewById(R.id.nxt2);

        tv_lt1 = findViewById(R.id.tv_lt1);
        tv_lg1 = findViewById(R.id.tv_lg1);


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

        nxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naviagte();
            }
        });



    }

    private void naviagte() {

        Intent intent = new Intent(Loc3.this,Loc4.class);
        intent.putExtra("distance1",String.valueOf(distance));
        intent.putExtra("lat3",lat3);
        intent.putExtra("lon3",lon3);
        startActivity(intent);

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


        distance = calculateDistanceInKilometer(loll,lol,lol1,loll1);
  //      distance = calculateDistanceInKilometer(6.927079,79.861244,6.7132734,79.9160491);
    //    distance = calculateDistanceInKilometer(6.713408110319169,79.91602709961393,6.713184955531217,79.91592511089544);

        ff.setText(String.valueOf(distance));



    }

    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public int calculateDistanceInKilometer(double userLat, double userLng,
                                            double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double r = (AVERAGE_RADIUS_OF_EARTH_KM * c) * 1000;

        int x = (int) r * 1;

        return x;

//////////////

//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));

//        final int R = 6371;
//        double latDistance = toRad(venueLat-userLat);
//        double lonDistance = toRad(venueLng-userLng);
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
//                Math.cos(toRad(userLat)) * Math.cos(toRad(venueLat)) *
//                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        double distance = (R * c) * 1000 ;
//
//        int x = (int) distance * 1;
//        return x;
    }

    private static double toRad(double value) {
        return value * Math.PI / 180;
    }

}