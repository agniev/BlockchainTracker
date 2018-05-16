package com.example.blockchaintracker.presentation.police;

import android.graphics.Color;
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

    @BindView(R.id.result_text)
    TextView resultText;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.tracker_user_id)
    TextView userId;
    @BindView(R.id.gps_length)
    TextView gpsLength;
    @BindView(R.id.gps_width)
    TextView gpsWidth;
    @BindView(R.id.date)
    TextView date;


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
    public void setResultVisibility(boolean isVisible) {
        if (isVisible) {
            resultText.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
        } else {
            resultText.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setResult(boolean result) {
        if (result) {
            this.result.setText("Присутствовал");
            this.result.setTextColor(Color.RED);
        } else {
            this.result.setText("Отсутствовал");
            this.result.setTextColor(Color.GREEN);
        }
    }

    @OnClick(R.id.check)
    void onCheckClick() {
        presenter().check(userId.getText().toString(), gpsLength.getText().toString(),
                gpsWidth.getText().toString(), date.getText().toString());
    }
}
