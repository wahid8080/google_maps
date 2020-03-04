package com.example.google_maps;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.google_maps.remote.IGoogleApi;
import com.example.google_maps.remote.RetrofitClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap, googleMaps;
    IGoogleApi googleApi;
    Common commons = new Common();
    SupportMapFragment mapFragment;
    private TextView latLong;
    LatLng sydney;
    TextView speed, heading, engine, dateTime;
    Marker marker;

    RelativeLayout relativeLayout, relativeLayout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        speed = findViewById(R.id.speedId);
        heading = findViewById(R.id.headingId);
        engine = findViewById(R.id.engineId);
        dateTime = findViewById(R.id.dateTime);

        relativeLayout = findViewById(R.id.relativeId);
        relativeLayout2 = findViewById(R.id.relativeId2);


        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
                relativeLayout2.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (marker!=null)
        {
            marker.remove();
        }

        googleApi = RetrofitClient.getClient().create(IGoogleApi.class);
        Call<Common> listCall = googleApi.getDataFromGoogleApi();

        listCall.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                commons = response.body();

                speed.setText(commons.getSpeed() + "km/h");
                heading.setText(String.valueOf(commons.getHeading()));
                engine.setText(String.valueOf(commons.getEngine()));
                dateTime.setText(commons.getTimestamp());

                String latToString;
                String longToString;
                String[] arrOfStr = commons.getLocation().split(";");
                latToString = arrOfStr[0];
                longToString = arrOfStr[1];

                double latitute = Double.valueOf(latToString);
                double longatitute = Double.valueOf(longToString);

                sydney = new LatLng(latitute, longatitute);
                marker = mMap.addMarker(new MarkerOptions().position(sydney)
                        .title("Device ID : " + commons.getDevice_id())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_yuma_cng_on)));
                marker.setRotation(commons.getHeading());

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20));


            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void maxmize(View view) {
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout2.setVisibility(View.GONE);
    }


    class BackgroundThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                onMapReady(mMap);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
