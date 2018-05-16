package com.example.blockchaintracker.presentation.police;

import com.example.blockchaintracker.api.dto.AddGeotagRs;
import com.example.blockchaintracker.api.dto.GeotagRq;
import com.example.blockchaintracker.api.requests.AddGeotag;
import com.example.blockchaintracker.api.requests.CheckGuilty;
import com.example.blockchaintracker.core.presenter.BasePresenter;
import com.example.blockchaintracker.core.presenter.EmptyState;
import com.example.blockchaintracker.utils.HashUtils;

import io.reactivex.observers.DisposableObserver;

public class PolicePresenter extends BasePresenter<PoliceView, EmptyState> {

    private CheckGuilty checkGuilty;

    @Override
    public void viewCreated() {
        super.viewCreated();
        checkGuilty = new CheckGuilty(view().context());
    }

    @Override
    public void destroyView() {
        super.destroyView();
        checkGuilty.dispose();
    }

    public void check(String userId, String gpsLongitude, String gpsLatitude, String date) {
        view().showLoading();
        view().setResultVisibility(false);
        String hash = HashUtils.applySha256(userId +" "+ gpsLatitude +" "+ gpsLongitude +" "+ date);
        GeotagRq request = new GeotagRq(hash);
        checkGuilty.execute(new CheckGuiltyObserver(), CheckGuilty.Params.forQuery(request));
    }

    private class CheckGuiltyObserver extends DisposableObserver<Boolean> {

        @Override
        public void onNext(Boolean response) {
            if (view() != null) {
                view().setResultVisibility(true);
                view().setResult(response);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (view() != null) {
                view().showError(e.getMessage());
                view().hideLoading();
            }
        }

        @Override
        public void onComplete() {
            if (view() != null) {
                view().hideLoading();
            }
        }
    }
}
