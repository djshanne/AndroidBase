package com.app.marvel.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.app.marvel.R;
import com.app.marvel.base.BaseActivity;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class InfoActivity extends BaseActivity implements InfoController {


    private static final String ID_KEY = "01";
    private static final String NAME_KEY = "02";
    private InfoFragment info;
    private InfoActivityPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        info = InfoFragment.getInstance(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getMainFragmentId() {
        return info.getId();
    }

    @Override
    protected Fragment getMainFragment() {
        return info;
    }

    @Override
    protected int getMainIdContainer() {
        return R.id.info_main_container;
    }

    @Override
    protected String getMainFragmentTag() {
        return InfoFragment.class.getSimpleName();
    }


    @Override
    public InfoActivityPresenter getPresenter() {
        if (presenter == null) presenter = new InfoActivityPresenter(this);
        return presenter;
    }

    @Override
    protected int getRightMenu() {
        return R.menu.empty_menu;
    }

    @Override
    protected View inflateMainView() {
        return LayoutInflater.from(this).inflate(R.layout.info_activity, null);
    }

    @Override
    protected String getTitleToolBar() {
        return getIntent().getStringExtra(NAME_KEY);
    }

    @Override
    protected void afterMainViewIsInflated(Bundle savedInstanceState) {
    }

    public static void startWithId(Context context, Integer id, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, id);
        bundle.putString(NAME_KEY, name);
        start(context, InfoActivity.class, bundle);
    }

    @Override
    public Integer getIdInfo() {
        return getIntent().getIntExtra(ID_KEY, 0);
    }
}
