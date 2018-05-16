package com.example.blockchaintracker.api.requests;

import android.content.Context;

import com.example.blockchaintracker.api.dto.GeotagRq;
import com.example.blockchaintracker.core.network.BaseAsyncRequest;

import io.reactivex.Observable;

public class CheckGuilty extends BaseAsyncRequest<Boolean, CheckGuilty.Params> {

    public CheckGuilty(Context context) {
        super(context);
    }

    @Override
    public Observable<Boolean> buildObservableRequest(Params params) {
        return getApi().checkGuilty(params.geotagRq);
    }

    public final static class Params {
        private final GeotagRq geotagRq;

        private Params(GeotagRq geotagRq) {
            this.geotagRq = geotagRq;
        }

        public static Params forQuery(GeotagRq geotagRs) {
            return new Params(geotagRs);
        }
    }
}
