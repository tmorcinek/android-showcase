package com.morcinek.showcase.general.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractRecyclerViewAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    protected Context context;

    private List<T> items = new ArrayList<>();

    private OnItemClickListener<T> itemClickListener;

    public AbstractRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setItemClickListener(OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    final protected T getItem(int position) {
        return items.get(position);
    }

    final public void setList(List<T> list) {
        items.clear();
        items.addAll(list);
        notifyItemRangeChanged(0, list.size());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    protected void initializeOnClickListener(H holder, final T item) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(item);
            }
        });
    }

    public interface OnItemClickListener<T> {

        void onItemClicked(T item);
    }
}