package com.example.blockchaintracker.presentation.tracker_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.fragments.BaseFragment;
import com.example.blockchaintracker.presentation.MainRouter;

public class TrackerUserFragment extends BaseFragment<TrackerUserPresenter, MainRouter> {

    public static TrackerUserFragment newInstance() {
        return new TrackerUserFragment();
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_tracker_user;
    }

    @Override
    protected void setToolbar(ActionBar actionBar, Toolbar toolbar) {
        toolbar.setTitle(R.string.tracker_user);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void create(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void created(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {

    }
}
