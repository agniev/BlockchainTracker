package com.example.blockchaintracker.api.requests;

import android.content.Context;

import com.example.blockchaintracker.api.dto.TestRs;
import com.example.blockchaintracker.core.network.BaseAsyncRequest;

import io.reactivex.Observable;

public class GetTest extends BaseAsyncRequest<TestRs, Void> {

    public GetTest(Context context) {
        super(context);
    }

    @Override
    public Observable<TestRs> buildObservableRequest(Void v) {
        return getApi().getTest();
    }
}
