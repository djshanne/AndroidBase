package com.app.marvel.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


import com.app.marvel.views.Controller;

import butterknife.ButterKnife;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public abstract class BaseFragment extends Fragment implements Controller {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getPresenter() != null) {
            getPresenter().register();
        }

        if (getPresenterList() != null) {
            getPresenterList().register();
        }

    }

    @Override
    public void hideRightMenu() {

    }

    @Override
    public void showToolBarTitleFade() {

    }

    @Override
    public void hideToolBarTitle() {

    }

    @Override
    public void hideToolBarTitleFade() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getPresenter() != null)
            getPresenter().unRegister();

        if (getPresenterList() != null)
            getPresenterList().unRegister();
    }


    @Override
    public void setToolbarTitle(String title) {

    }

    @Override
    public void setToolbarSubTitle(String title) {

    }

    @Override
    public PresenterList getPresenterList() {
        return null;
    }

    @Override
    public Context getCtx() {
        return getActivity();
    }

    public abstract String getTagName();

    @Override
    public void setUpLeftIconToolbar(int id) {

    }

    @Override
    public void setUpLeftIconToolbar(int id, int color) {

    }

}
