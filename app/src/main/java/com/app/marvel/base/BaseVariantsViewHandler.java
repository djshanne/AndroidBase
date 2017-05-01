package com.app.marvel.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Juan Delgado on 3/29/2017.
 */

public abstract class BaseVariantsViewHandler {

    protected View view;

    public void setView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
    }

    public View getView() {
        return view;
    }

}
