package com.example.location_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    Button btLocation,nxt1;
    TextView area,d1,d2;
    private String distance1,lon4,lat4,lon3,lat3;
    private int distance;
    private double lol,loll,lol1,loll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        distance1 = getIntent().getStringExtra("distance1");
        lat3 = getIntent().getStringExtra("lat3");
        lon3 = getIntent().getStringExtra("lon3");
        lat4 = getIntent().getStringExtra("lat4");
        lon4 = getIntent().getStringExtra("lon4");

        area = findViewById(R.id.area);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);

        d1.setText(distance1);

        int x = Integer.valueOf(distance1);

        lol = Double.valueOf(lat3);
        loll = Double.valueOf(lon3);
        lol1 = Double.valueOf(lat4);
        loll1 = Double.valueOf(lon4);

        distance = calculateDistanceInKilometer(lol,loll,lol1,loll1);
       //    distance = calculateDistanceInKilometer(6.713408110319169,79.91602709961393,6.713184955531217,79.91592511089544);

        int a = distance * x;

        d2.setText(String.valueOf(distance));

        area.setText(String.valueOf(a) + "m");

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
}