package com.app.marvel.info;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.marvel.base.FragmentList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan.delgado on 24/02/2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {


    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }


    List<FragmentList> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }

    public void setData(List<FragmentList> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitleTab();
    }
}
