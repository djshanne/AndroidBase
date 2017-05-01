package com.app.marvel.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.marvel.R;
import com.app.marvel.base.BaseFragment;
import com.app.marvel.base.Presenter;
import com.app.marvel.dcharacter.list.CharactersFragmentList;
import com.app.marvel.views.Controller;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestList;

import butterknife.BindView;

/**
 * Created by juan.delgado on 17/02/2017.
 */

public class InfoFragment extends BaseFragment implements InfoController {
    private InfoController controller;


    @BindView(R.id.tabDetailContainer)
    TabsDetailView tabsDetailContainer;

    private CharactersFragmentList bottomList;
    private CharactersFragmentList bottomList4;
    private CharactersFragmentList bottomList5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomList = CharactersFragmentList.getInstance(R.layout.item_wide_loading, R.layout.item_wide_view, R.layout.item_wide_retry, new RequestCharacters(controller.getIdInfo(), RequestList.COMICS), true, false, LinearLayoutManager.VERTICAL, true);
        bottomList.setTitleTab(getString(R.string.comics));
        bottomList4 = CharactersFragmentList.getInstance(R.layout.item_wide_loading, R.layout.item_wide_view, R.layout.item_wide_retry, new RequestCharacters(controller.getIdInfo(), RequestList.EVENTS), true, false, LinearLayoutManager.VERTICAL, true);
        bottomList4.setTitleTab(getString(R.string.events));
        bottomList5 = CharactersFragmentList.getInstance(R.layout.item_wide_loading, R.layout.item_wide_view, R.layout.item_wide_retry, new RequestCharacters(controller.getIdInfo(), RequestList.SERIES), true, false, LinearLayoutManager.VERTICAL, true);
        bottomList5.setTitleTab(getString(R.string.series));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_main_screen, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabsDetailContainer.initializeViewPager(getChildFragmentManager());
        tabsDetailContainer.addPage(bottomList);
        tabsDetailContainer.addPage(bottomList4);
        tabsDetailContainer.addPage(bottomList5);
    }


    @Override
    public String getTagName() {
        return InfoFragment.class.getSimpleName();
    }

    public static InfoFragment getInstance(InfoController controller) {
        InfoFragment fragment = new InfoFragment();
        fragment.setController(controller);
        return fragment;
    }

    public void setController(InfoController controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void showProgressBar(boolean b) {

    }

    @Override
    public void hideProgressBar(boolean b) {

    }

    @Override
    public Integer getIdInfo() {
        return controller.getIdInfo();
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }
}
