package com.app.marvel.info;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


import com.app.marvel.R;
import com.app.marvel.base.FragmentList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juan.delgado on 27/02/2017.
 */

public abstract class TabsBaseView extends FrameLayout {
    @BindView(R.id.tabs_pager)
    protected ViewPager pager;
    @BindView(R.id.tabs_layout_pager)
    protected TabLayout tabs;
    protected TabsAdapter imgAdapter;

    protected List<FragmentList> pages = new ArrayList();

    public TabsBaseView(Context context) {
        super(context);
        initView(context);
    }

    public TabsBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabsBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        LayoutInflater.from(context).inflate(getTabLayout(), this);
        ButterKnife.bind(this);
        tabs.setupWithViewPager(pager);
    }

    protected abstract int getTabLayout();

    public void addPage(FragmentList page) {
        pages.add(page);
        imgAdapter.setData(pages);
        pager.setOffscreenPageLimit(pages.size());
    }

    public void initializeViewPager(FragmentManager fm) {
        imgAdapter = new TabsAdapter(fm);
        pager.setAdapter(imgAdapter);
    }

}
