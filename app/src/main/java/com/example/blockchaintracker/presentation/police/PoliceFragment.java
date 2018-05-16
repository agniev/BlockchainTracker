package com.example.blockchaintracker.presentation.police;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.fragments.BaseFragment;
import com.example.blockchaintracker.presentation.MainRouter;

import butterknife.BindView;
import butterknife.OnClick;

public class PoliceFragment extends BaseFragment<PolicePresenter, MainRouter> implements PoliceView {

    @BindView(R.id.result)
    TextView result;

    public static PoliceFragment newInstance() {
        return new PoliceFragment();
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_police;
    }

    @Override
    protected void setToolbar(ActionBar actionBar, Toolbar toolbar) {
        toolbar.setTitle(R.string.police);
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

    @Override
    public void setResult(String result) {
        this.result.setText(result);
    }

    @OnClick(R.id.check)
    void onCheckClick() {
        presenter().check();
    }
}
