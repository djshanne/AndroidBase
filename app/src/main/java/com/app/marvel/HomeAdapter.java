package com.app.marvel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan.delgado on 24/02/2017.
 */

public class HomeAdapter extends FragmentPagerAdapter {

    List<Fragment> sections = new ArrayList<>();

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return sections.size();
    }


    @Override
    public Fragment getItem(int position) {
        return sections.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setData(List<Fragment> data) {
        this.sections.clear();
        this.sections.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        sections.clear();
        notifyDataSetChanged();
    }


    public List<Fragment> getData() {
        return sections;
    }

    public void addData(List<Fragment> t) {
        sections.addAll(t);
        notifyDataSetChanged();
    }

}
