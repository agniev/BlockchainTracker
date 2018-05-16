package com.example.blockchaintracker.presentation.tracker_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.fragments.BaseFragment;
import com.example.blockchaintracker.presentation.MainRouter;

import java.util.List;

import butterknife.BindView;

public class TrackerUserFragment extends BaseFragment<TrackerUserPresenter, MainRouter> implements TrackerUserView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.list_hint_empty)
    TextView emptyListHint;

    private TrackUserAdapter adapter;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (adapter == null) {
            adapter = new TrackUserAdapter();
        }

        refreshItemList(presenter().getData());

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    public void refreshItemList(List<TrackingData> trackingDataList) {
        adapter.update(trackingDataList);
        if (trackingDataList.isEmpty()) {
            emptyListHint.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyListHint.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
