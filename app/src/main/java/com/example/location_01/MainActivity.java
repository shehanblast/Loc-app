package com.example.location_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button btLocation,n;
    TextView tvLatitude,tvLongitude;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;
    private String l,ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btLocation = findViewById(R.id.bt_location);
        n = findViewById(R.id.bt_location1);
//        tvLatitude = findViewById(R.id.tv_latitude);
//        tvLongitude = findViewById(R.id.tv_longitude);
        
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
//                MainActivity.this
//        );
//
//
//        btLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ActivityCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//
//                    getCurrentLocation();
//
//                }else {
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION},
//                            100);
//                }
//
//
//            }
//        });

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
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


        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lol();
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


        l = String.valueOf(location.getLongitude());
        ll = String.valueOf(location.getLatitude());







    }

    public void lol(){
        Intent intent = new Intent(MainActivity.this,Loc2.class);
        intent.putExtra("lat",l);
        intent.putExtra("lon",ll);
        startActivity(intent);
    }





//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
//                == PackageManager.PERMISSION_GRANTED)) {
//                getCurrentLocation();
//        }else {
//            Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private void getCurrentLocation() {
//
//        LocationManager locationManager = (LocationManager) getSystemService(
//                Context.LOCATION_SERVICE
//        );
//
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
//            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//                @Override
//                public void onComplete(@NonNull Task<Location> task) {
//                    Location location = task.getResult();
//
//                    if (location != null){
////                        tvLatitude.setText(String.valueOf(location.getLatitude()));
////                        tvLongitude.setText(String.valueOf(location.getLongitude()));
//
//                        Intent intent = new Intent(MainActivity.this,Loc2.class);
//                        intent.putExtra("lat",String.valueOf(location.getLatitude()));
//                        intent.putExtra("lon",String.valueOf(location.getLongitude()));
//                        startActivity(intent);
//                    }
//                    else {
//                        LocationRequest locationRequest = new LocationRequest()
//                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                                .setInterval(10000)
//                                .setFastestInterval(1000)
//                                .setNumUpdates(1);
//
//                        LocationCallback locationCallback = new LocationCallback(){
//                            @Override
//                            public void onLocationResult(LocationResult locationResult) {
//                                Location location1 = locationResult.getLastLocation();
//
////                                tvLatitude.setText(String.valueOf(location1.getLatitude()));
////                                tvLongitude.setText(String.valueOf(location1.getLongitude()));
//
//                                Intent intent = new Intent(MainActivity.this,Loc2.class);
//                                intent.putExtra("lat",String.valueOf(location1.getLatitude()));
//                                intent.putExtra("lon",String.valueOf(location1.getLongitude()));
//                                startActivity(intent);
//
//                            }
//                        };
//
//                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
//                                locationCallback, Looper.myLooper());
//                    }
//                }
//            });
//
//        }else {
//            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        }
//    }



}



















