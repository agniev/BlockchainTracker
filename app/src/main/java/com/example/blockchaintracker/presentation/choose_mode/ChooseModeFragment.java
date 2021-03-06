package com.example.blockchaintracker.presentation.choose_mode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.fragments.BaseFragment;
import com.example.blockchaintracker.core.presenter.EmptyPresenter;
import com.example.blockchaintracker.presentation.MainRouter;

import butterknife.OnClick;

public class ChooseModeFragment extends BaseFragment<EmptyPresenter, MainRouter> {

    @NonNull
    public static ChooseModeFragment newInstance() {
        return new ChooseModeFragment();
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_choose_mode;
    }

    @Override
    protected void setToolbar(ActionBar actionBar, Toolbar toolbar) {
        toolbar.setTitle(R.string.choose_mode);
        actionBar.setDisplayHomeAsUpEnabled(false);
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

    @OnClick(R.id.tracker_user)
    void onTrackerUserModeClick() {
        router().navigateToTrackerUserFragment();
    }

    @OnClick(R.id.police)
    void onPoliceModeClick() {
        router().navigateToPoliceFragment();
    }
}
