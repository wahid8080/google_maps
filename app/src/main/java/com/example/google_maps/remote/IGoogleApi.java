package com.example.google_maps.remote;


import com.example.google_maps.Common;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IGoogleApi{
    @GET("api/location/device/352621109471182/last")
    Call<Common> getDataFromGoogleApi();

}