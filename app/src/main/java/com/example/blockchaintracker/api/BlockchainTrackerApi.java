package com.example.blockchaintracker.api;

import com.example.blockchaintracker.api.dto.GeotagRq;
import com.example.blockchaintracker.api.dto.AddGeotagRs;
import com.example.blockchaintracker.api.dto.EmptyRs;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface BlockchainTrackerApi {

    @POST("add_geotag")
    Observable<AddGeotagRs> addBlock(@Body GeotagRq request);

    @POST("check_guilty")
    Observable<Boolean> checkGuilty(@Body GeotagRq request);

}