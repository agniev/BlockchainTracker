package com.example.blockchaintracker;

import android.app.Application;

import com.example.blockchaintracker.api.BlockchainTrackerApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlockchainTrackerApplication extends Application {

    private BlockchainTrackerApi blockchainTrackerApi;

    @Override
    public void onCreate() {
        super.onCreate();
        createBlockchainTrackerApi();
    }

    public void createBlockchainTrackerApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.serverBaseUrl)) //todo set real endpoint
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        blockchainTrackerApi = retrofit.create(BlockchainTrackerApi.class);
    }

    public BlockchainTrackerApi getRestApi() {
        return blockchainTrackerApi;
    }
}
