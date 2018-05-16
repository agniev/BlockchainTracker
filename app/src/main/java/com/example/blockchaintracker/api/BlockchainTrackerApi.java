package com.example.blockchaintracker.api;

import com.example.blockchaintracker.api.dto.AddBlockRq;
import com.example.blockchaintracker.api.dto.EmptyRs;
import com.example.blockchaintracker.api.dto.TestRs;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface BlockchainTrackerApi {

    @GET("get_block/1")
    Observable<TestRs> getTest();

    @POST("add_block")
    Observable<EmptyRs> addBlock(@Body AddBlockRq request);
}