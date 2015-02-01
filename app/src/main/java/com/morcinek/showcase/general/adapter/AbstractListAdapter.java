package com.morcinek.showcase.general.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractListAdapter<T> extends BaseAdapter {

    protected final Context context;

    private final LayoutInflater layoutInflater;

    protected List<T> list;

    public AbstractListAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * This method needs to be invoked in any overriding method.
     * This method already contains invocation of <code>notifyDataSetChanged()</code>,
     * so it shouldn't be explicitly invoked from UI.
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public final int getCount() {
        if( list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public final T getItem(int position) {
        return list.get(position);
    }

    @Override
    public final long getItemId(int position) {
        return list.indexOf(list.get(position));
    }

    public LayoutInflater getLayoutInflater(){
        return layoutInflater;
    }
}
