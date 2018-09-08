package com.example.blockchaintracker.presentation.tracker_user;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.ViewGroup;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.api.dto.AddGeotagRs;
import com.example.blockchaintracker.api.dto.GeotagRq;
import com.example.blockchaintracker.api.requests.AddGeotag;
import com.example.blockchaintracker.core.presenter.BasePresenter;
import com.example.blockchaintracker.core.presenter.EmptyState;
import com.example.blockchaintracker.presentation.police.PolicePresenter;
import com.example.blockchaintracker.utils.HashUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.observers.DisposableObserver;

public class TrackerUserPresenter extends BasePresenter<TrackerUserView, EmptyState> implements LocationListener {

    private static final String TAG = TrackerUserPresenter.class.getSimpleName();

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault());
    private static final DecimalFormat df = new DecimalFormat("#.##");

    private List<TrackingData> dataList = new ArrayList<>();
    private AddGeotag addGeotag;
    private Timer timer;
    private PermissionListener gpsPermissionListener;
    private PermissionRequestErrorListener errorListener;
    private Location currentLocation;
    private TrackingData currentTrackingData;

    @Override
    public void viewCreated() {
        super.viewCreated();
        addGeotag = new AddGeotag(view().context());
        createPermissionListeners();
        getGpsWithPermission();
        startCollectingData();
    }

    @Override
    public void destroyView() {
        addGeotag.dispose();
        view().context().runOnUiThread(this::stopTimer);
        super.destroyView();
    }

    private void getGpsWithPermission() {
        Dexter.withActivity(view().context())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(gpsPermissionListener)
                .withErrorListener(errorListener)
                .check();
    }

    private void createPermissionListeners() {
        gpsPermissionListener = new CompositePermissionListener(
                new BasePermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        getGps();
                    }
                },
                SnackbarOnDeniedPermissionListener.Builder.with((ViewGroup) view().context().findViewById(android.R.id.content),
                        R.string.permission_gps_snackbar)
                        .withOpenSettingsButton(R.string.permission_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build(),
                DialogOnDeniedPermissionListener.Builder
                        .withContext(view().context())
                        .withTitle(R.string.permission_gps_rationale_title)
                        .withMessage(R.string.permission_gps_rationale)
                        .withButtonText(R.string.permission_ok)
                        .build());

        errorListener = error -> Log.d(TAG, error.toString());
    }

    public void startCollectingData() {
        startTimer();
    }

    private void createData() {
        int id = 12345;
        String date = simpleDateFormat.format(new Date());

        String gpsLongitude = "0";
        String gpsLatitude = "0";
        if (currentLocation != null) {
            gpsLongitude = df.format(currentLocation.getLongitude());
            gpsLatitude = df.format(currentLocation.getLatitude());
        }
        String hash = HashUtils.applySha256(id +" "+ gpsLatitude +" "+ gpsLongitude +" "+ date);
        currentTrackingData = new TrackingData(id, gpsLongitude, gpsLatitude, date, hash);
        Log.d(TAG, id + " " + gpsLatitude +" "+ gpsLongitude +" "+ date);
        sendData(hash);
    }

    private void getGps() {
        LocationManager locationManager = (LocationManager) view().context().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    public List<TrackingData> getData() {
        return dataList;
    }

    private void startTimer() {
        if (timer == null) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (view() != null) {
                        view().context().runOnUiThread(() -> createData());
                    }
                }
            };
            timer = new Timer();
            timer.scheduleAtFixedRate(task, 0, 10000);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        timer = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void sendData(String hash) {
        view().showLoading();
        GeotagRq request = new GeotagRq(hash);
        addGeotag.execute(new TrackerUserPresenter.AddGeotagObserver(), AddGeotag.Params.forQuery(request));
    }

    private class AddGeotagObserver extends DisposableObserver<AddGeotagRs> {

        @Override
        public void onNext(AddGeotagRs response) {
            if (view() != null) {
                currentTrackingData.setBlockId(response.getId());
                currentTrackingData.setNonce(response.getNonce());
                currentTrackingData.setBlockHash(response.getHash());
                dataList.add(currentTrackingData);
                view().refreshItemList(dataList);
                view().showError("Блок добавлен");
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
