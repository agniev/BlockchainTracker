package com.example.blockchaintracker.api.requests;

import android.content.Context;

import com.example.blockchaintracker.api.dto.AddBlockRq;
import com.example.blockchaintracker.api.dto.EmptyRs;
import com.example.blockchaintracker.core.network.BaseAsyncRequest;

import io.reactivex.Observable;

/**
 * Created by Роман on 16.05.2018.
 */

public class AddBlock extends BaseAsyncRequest<EmptyRs, AddBlock.Params> {

    public AddBlock(Context context) {
        super(context);
    }

    @Override
    public Observable<EmptyRs> buildObservableRequest(Params params) {
        return getApi().addBlock(params.addBlockRq);
    }

    public final static class Params {
        private final AddBlockRq addBlockRq;

        private Params(AddBlockRq addBlockRq) {
            this.addBlockRq = addBlockRq;
        }

        public static Params forQuery(AddBlockRq addBlockRq) {
            return new Params(addBlockRq);
        }
    }
}
