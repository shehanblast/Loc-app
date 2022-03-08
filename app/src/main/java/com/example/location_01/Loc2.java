package com.example.location_01;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.List;
import java.util.Locale;

public class Loc2 extends AppCompatActivity implements LocationListener {

    Button btLocation,nxt1;
    TextView tvLatitude,tvLongitude,lt,lg,ff;
    FusedLocationProviderClient fusedLocationProviderClient2;
    private String lon,lat,l,ll;
    private boolean t;
    private int distance;
    private double lon2,lat2,lol,loll;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc2);

        btLocation = findViewById(R.id.bt_location2);
        nxt1 = findViewById(R.id.nxt1);
        tvLatitude = findViewById(R.id.tv_latitude2);
        tvLongitude = findViewById(R.id.tv_longitude2);
        lt = findViewById(R.id.tv_lt);
        lg = findViewById(R.id.tv_lg);
        ff = findViewById(R.id.ff);

        lat = getIntent().getStringExtra("lat");
        lon = getIntent().getStringExtra("lon");

        lol = Double.parseDouble(lat);
        loll = Double.parseDouble(lon);

        tvLatitude.setText(lat);
        tvLongitude.setText(lon);

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(Loc2.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Loc2.this,new String[]{
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

        lt.setText(String.valueOf(location.getLatitude()));
        lg.setText(String.valueOf(location.getLongitude()));

        l = String.valueOf(location.getLongitude());
        ll = String.valueOf(location.getLatitude());

        lat2 = location.getLatitude();
        lon2 = location.getLongitude();

    }

    private void chg() {

        distance = calculateDistanceInKilometer(lol,loll,lat2,lon2);
//        distance = calculateDistanceInKilometer(6.927079,79.861244,6.7132734,79.9160491);
        //distance = calculateDistanceInKilometer(6.7132624,79.9160567,6.7133020,79.9160156);

        ff.setText(String.valueOf(distance));

//        Intent intent = new Intent(Loc2.this,Loc3.class);
//        intent.putExtra("lat1",lat);
//        intent.putExtra("lon1",lon);
//        intent.putExtra("lat2",lat2);
//        intent.putExtra("lon2",lon2);
//        startActivity(intent);

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
    }




}