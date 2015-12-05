package com.morcinek.showcase.home.navigation.drawer.footer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class DrawerFooterAdapter extends AbstractRecyclerViewAdapter<DrawerFooterItem, DrawerFooterAdapter.ViewHolder> {

    public DrawerFooterAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.drawer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerFooterItem drawerItem = getItem(position);
        initializeOnClickListener(holder, drawerItem);
        holder.titleView.setText(drawerItem.getTitle());
        holder.imageView.setImageResource(drawerItem.getIcon());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }
    }
}
