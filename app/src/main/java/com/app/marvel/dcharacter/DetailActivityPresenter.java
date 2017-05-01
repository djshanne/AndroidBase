package com.app.marvel.dcharacter;

import android.content.Context;

import com.app.marvel.base.Presenter;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class DetailActivityPresenter extends Presenter {


    private final DetailController controller;

    public DetailActivityPresenter(final DetailController controller) {
        super(controller);
        this.controller = controller;
    }
}
