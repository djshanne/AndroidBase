package com.app.marvel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.app.marvel.base.BaseActivity;
import com.app.marvel.views.Controller;


/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class HomeActivity extends BaseActivity implements Controller {

    private HomeFragment homeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        homeFragment = HomeFragment.getInstance(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View inflateMainView() {
        return LayoutInflater.from(this).inflate(R.layout.home_activity, null);
    }

    @Override
    protected int getMainFragmentId() {
        return homeFragment.getId();
    }

    @Override
    protected Fragment getMainFragment() {
        return homeFragment;
    }

    @Override
    protected int getMainIdContainer() {
        return R.id.home_main_container;
    }

    @Override
    protected String getMainFragmentTag() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    protected void afterMainViewIsInflated(Bundle savedInstanceState) {
    }


    @Override
    public HomeActivityPresenter getPresenter() {
        if (presenter == null) {
            presenter = new HomeActivityPresenter(this);
        }
        return (HomeActivityPresenter) presenter;
    }

    @Override
    protected int getLogoRsc() {
        return R.drawable.marvel_logo;
    }

    @Override
    protected int getLeftCornerIcon() {
        return 0;
    }

    @Override
    protected boolean leftIconShouldBeShownWhenDrawableZero() {
        return false;
    }

    @Override
    public void showProgressBar(boolean b) {
        super.showProgressBar(b);
    }

    @Override
    public void hideProgressBar(boolean b) {
        super.hideProgressBar(b);
    }

}
