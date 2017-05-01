package com.app.marvel.views;

import android.content.Context;

import com.app.marvel.base.Presenter;
import com.app.marvel.base.PresenterList;
import com.app.marvel.base.ProgressDialog;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public interface Controller extends ProgressDialog {

    public Presenter getPresenter();

    public PresenterList getPresenterList();

    public void setToolbarTitle(String title);

    public void setToolbarSubTitle(String title);

    public void setUpLeftIconToolbar(int id);

    public void setUpLeftIconToolbar(int id, int color);

    Context getCtx();

    void hideToolBarTitle();

    void showToolBarTitleFade();

    void hideToolBarTitleFade();

    void hideRightMenu();


//    public void onDataLoaded();

}
