package com.morcinek.showcase.home.navigation.drawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractListAdapter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class DrawerListAdapter extends AbstractListAdapter<DrawerItem> {

    public DrawerListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.drawer_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view;
            view.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) view.getTag());
        return view;
    }

    private void initializeViews(DrawerItem drawerItem, ViewHolder holder) {
        holder.textView.setText(drawerItem.getTitle());
        if (drawerItem.getIcon() != null) {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(drawerItem.getIcon(), 0, 0, 0);
        }
    }

    protected class ViewHolder {
        public TextView textView;
    }
}
