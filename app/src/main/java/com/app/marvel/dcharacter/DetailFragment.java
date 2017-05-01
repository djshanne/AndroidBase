package com.app.marvel.dcharacter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.marvel.R;
import com.app.marvel.base.BaseFragment;
import com.app.marvel.dcharacter.imgpager.ImagePagerView;
import com.model.bean.characters.Result;
import com.model.bean.characters.Thumbnail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class DetailFragment extends BaseFragment implements DetailFragmentController {

    private DetailController controller;
    private DetailFragmentPresenter presenter;
    @BindView(R.id.img_pager_view)
    ImagePagerView imagePagerView;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.main_appbar)
    AppBarLayout appBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    HeaderDetailViewHandler headerDetailViewHandler;


    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerDetailViewHandler = new HeaderDetailViewHandler(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_main_screen, null);
    }

    @Override
    public DetailFragmentPresenter getPresenter() {
        if (presenter == null) {
            presenter = new DetailFragmentPresenter(this);
        }
        return presenter;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setToolbar(toolbar);
        headerDetailViewHandler.setView(view);
        getPresenter().getDetail();
    }


    @Override
    public void renderView(Result data) {
        controller.setToolbarTitle(data.getName());
        headerDetailViewHandler.renderView(data);
        List<Thumbnail> list = new ArrayList<>();
        list.add(data.getThumbnail());
        imagePagerView.setData(list);
    }


    @Override
    public Integer getSlug() {
        return controller.getSlug();
    }


    public void setController(DetailController controller) {
        this.controller = controller;
    }


    public static DetailFragment getInstance(DetailController controller) {
        DetailFragment fragment = new DetailFragment();
        fragment.setController(controller);
        return fragment;
    }


    public String getTagName() {
        return DetailFragment.class.getSimpleName();
    }

    @Override
    public void showProgressBar(boolean b) {
    }

    @Override
    public void hideProgressBar(boolean b) {
//        controller.hideProgressBar(true);
    }

    @Override
    public void dismiss() {
        controller.dismiss();
    }

    public boolean isTabUp() {
        return (appBar.getHeight() - appBar.getBottom()) == 0;
    }

    public void dragTabsToBottom() {
        appBar.setExpanded(true, true);
    }
}
