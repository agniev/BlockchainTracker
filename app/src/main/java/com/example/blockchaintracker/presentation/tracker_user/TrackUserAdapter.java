package com.example.blockchaintracker.presentation.tracker_user;

import android.view.View;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.adapters.BaseRecyclerAdapter;

public class TrackUserAdapter extends BaseRecyclerAdapter<TrackingData, BlockModelViewHolder> {
    @Override
    protected void onBind(TrackingData bean, BlockModelViewHolder holder, int properPosition) {
        holder.id.setText(bean.getId());
        holder.gpsLength.setText(bean.getGpsLongitude());
        holder.gpsWidth.setText(bean.getGpsLatitude());
        holder.date.setText(bean.getDate());
        holder.hash.setText(bean.getHash());
        holder.previousHash.setText(bean.getPreviousHash());
        holder.blockId.setText(String.valueOf(bean.getBlockId()));
        holder.nonce.setText(String.valueOf(bean.getNonce()));
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
