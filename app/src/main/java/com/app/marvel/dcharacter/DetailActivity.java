package com.app.marvel.dcharacter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.app.marvel.R;
import com.app.marvel.base.BaseActivity;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class DetailActivity extends BaseActivity implements DetailController {

    private static final String KEY_SLUG = "01";
    private DetailFragment detailFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        detailFragment = DetailFragment.getInstance(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getMainFragmentTag() {
        return DetailFragment.class.getSimpleName();
    }

    @Override
    protected int getMainIdContainer() {
        return R.id.detail_main_container;
    }

    @Override
    protected Fragment getMainFragment() {
        return detailFragment;
    }

    @Override
    protected int getMainFragmentId() {
        return detailFragment.getId();
    }


    @Override
    protected View inflateMainView() {
        return LayoutInflater.from(this).inflate(R.layout.detail_main_container_layout, null);
    }

    @Override
    protected void afterMainViewIsInflated(Bundle savedInstanceState) {

    }

    @Override
    public DetailActivityPresenter getPresenter() {
        if (presenter == null) {
            presenter = new DetailActivityPresenter(this);
        }
        return (DetailActivityPresenter) presenter;
    }

    @Override
    protected int getRightMenu() {
        return R.menu.empty_menu;
    }

    public static void startWithId(Context context, Integer slug) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SLUG, slug);
        start(context, DetailActivity.class, bundle);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        super.setUpToolbar(toolbar);
    }

    @Override
    public void setToolbarTitle(String title) {
        this.setToolbarTitleStr(title);
    }

    @Override
    public Integer getSlug() {
        return getIntent().getIntExtra(KEY_SLUG, 0);
    }


}
