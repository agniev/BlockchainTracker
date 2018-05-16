package com.example.blockchaintracker.presentation.tracker_user;

import android.view.View;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.adapters.BaseRecyclerAdapter;

public class TrackUserAdapter extends BaseRecyclerAdapter<BlockModel, BlockModelViewHolder> {
    @Override
    protected void onBind(BlockModel bean, BlockModelViewHolder holder, int properPosition) {

    }

    @Override
    protected int getItemLayoutIdForType(int viewType) {
        return R.layout.item_block_model;
    }

    @Override
    protected BlockModelViewHolder getViewHolder(View v) {
        return new BlockModelViewHolder(v);
    }
}
