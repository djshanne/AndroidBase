package com.app.marvel.dcharacter;

import android.support.v7.widget.Toolbar;

import com.app.marvel.views.Controller;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public interface DetailController extends Controller {
    Integer getSlug();
    void dismiss();

    void setToolbar(Toolbar toolbar);

    void setToolbarTitle(String title);

}
