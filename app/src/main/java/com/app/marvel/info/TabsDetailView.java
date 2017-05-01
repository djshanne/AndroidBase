package com.app.marvel.info;

import android.content.Context;
import android.util.AttributeSet;

import com.app.marvel.R;


/**
 * Created by juan.delgado on 27/02/2017.
 */

public class TabsDetailView extends TabsBaseView {


    public TabsDetailView(Context context) {
        super(context);
    }

    public TabsDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabsDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getTabLayout() {
        return R.layout.tabs_detail_layout;
    }
}
