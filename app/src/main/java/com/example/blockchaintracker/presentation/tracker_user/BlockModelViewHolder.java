package com.example.blockchaintracker.presentation.tracker_user;

import android.view.View;
import android.widget.TextView;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.adapters.BaseRecyclerAdapter;

import butterknife.BindView;

public class BlockModelViewHolder extends BaseRecyclerAdapter.BaseViewHolder {

    @BindView(R.id.user_id)
    TextView id;
    @BindView(R.id.gps_width)
    TextView gpsWidth;
    @BindView(R.id.gps_length)
    TextView gpsLength;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.hash)
    TextView hash;
    @BindView(R.id.previous_hash)
    TextView previousHash;
    @BindView(R.id.nonce)
    TextView nonce;
    @BindView(R.id.block_id)
    TextView blockId;

    public BlockModelViewHolder(View itemView) {
        super(itemView);
    }
}
