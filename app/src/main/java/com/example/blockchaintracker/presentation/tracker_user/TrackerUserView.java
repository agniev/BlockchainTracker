package com.example.blockchaintracker.presentation.tracker_user;

import com.example.blockchaintracker.core.view.BaseView;

import java.util.List;

public interface TrackerUserView extends BaseView {
    void refreshItemList(List<TrackingData> trackingDataList);
}
