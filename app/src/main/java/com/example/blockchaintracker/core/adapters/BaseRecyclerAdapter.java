package com.example.blockchaintracker.core.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseRecyclerAdapter<T, V extends BaseRecyclerAdapter.BaseViewHolder> extends RecyclerView.Adapter {

    protected ArrayList<T> mData;

    public BaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutIdForType(viewType), parent, false);
        return getViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBind(mData.get(holder.getAdapterPosition()), (V) holder, holder.getAdapterPosition());
    }

    protected abstract void onBind(T bean, V holder, int properPosition);

    @LayoutRes
    protected abstract int getItemLayoutIdForType(int viewType);

    protected abstract V getViewHolder(View v);

    public void add(T item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    public void add(int position, T item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void append(List<T> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public <C extends Collection<? extends T>> void set(C items) {
        mData = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public <C extends T> void set(int position, C item) {
        mData.set(position, item);
        notifyItemChanged(position);
    }

    public <C extends Collection<? extends T>> void update(C items) {
        int oldSize = mData.size();
        mData.clear();
        mData.addAll(items);
        int newSize = mData.size();

        if (oldSize > newSize) {
            notifyItemRangeChanged(0, newSize);
            notifyItemRangeRemoved(newSize, oldSize - newSize);
        } else {
            notifyItemRangeChanged(0, oldSize);
            notifyItemRangeInserted(oldSize, newSize - oldSize);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ArrayList<T> getItems() {
        return mData;
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public View root;

        public BaseViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public T removeItem(int position) {
        final T data = mData.remove(position);
        notifyItemRemoved(position);
        return data;
    }

    public void clear() {
        final int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addItem(int position, T data) {
        mData.add(position, data);
        notifyItemInserted(position);
    }

    public void setItem(int position, T data) {
        mData.set(position, data);
        notifyItemChanged(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final T data = mData.remove(fromPosition);
        mData.add(toPosition, data);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<T> data) {
        applyAndAnimateRemovals(data);
        applyAndAnimateAdditions(data);
        applyAndAnimateMovedItems(data);
    }

    private void applyAndAnimateRemovals(List<T> newData) {
        for (int i = mData.size() - 1; i >= 0; i--) {
            final T data = mData.get(i);
            if (!newData.contains(data)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<T> newData) {
        for (int i = 0, count = newData.size(); i < count; i++) {
            final T data = newData.get(i);
            if (!mData.contains(data)) {
                addItem(i, data);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<T> newData) {
        for (int toPosition = newData.size() - 1; toPosition >= 0; toPosition--) {
            final T data = newData.get(toPosition);
            final int fromPosition = mData.indexOf(data);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}
