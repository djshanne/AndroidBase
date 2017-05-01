package com.app.marvel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.marvel.base.BaseFragment;
import com.app.marvel.comics.ComicsFragmentList;
import com.app.marvel.creators.CreatorsFragmentList;
import com.app.marvel.dcharacter.list.CharactersFragmentList;
import com.app.marvel.events.EventsFragmentList;
import com.app.marvel.series.SeriesFragmentList;
import com.app.marvel.utils.Utils;
import com.app.marvel.views.Controller;
import com.app.marvel.views.VerticalViewPager;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestComics;
import com.data.service.request.RequestEvents;
import com.data.service.request.RequestSeries;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class HomeFragment extends BaseFragment implements Controller {

    private Controller controller;
    private HomeFragmentPresenter presenter;

    @BindView(R.id.pager_cover_flow_horizontal)
    VerticalViewPager sections;
    @BindView(R.id.generic_list_empty)
    ImageView emptySections;
    @BindView(R.id.sections_list_retry)
    ImageView retrySection;
    @BindView(R.id.generic_list_progress)
    ProgressBar loadingSection;
    private HomeAdapter mAdapterSections;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapterSections = new HomeAdapter(getChildFragmentManager());
        getPresenter().initializeFragments();
        hideProgressBar(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_main_screen, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
    }


    private void setUpView(View view) {
        Utils.setIconColor(getContext(), android.R.color.white, R.drawable.ic_replay_black_24dp, retrySection);
        Utils.setIconColor(getContext(), android.R.color.white, R.drawable.empty_white_box, emptySections);
        loadingSection.setVisibility(View.GONE);
        retrySection.setVisibility(View.GONE);
        emptySections.setVisibility(View.GONE);
        sections.setVisibility(View.VISIBLE);
        mAdapterSections.setData(getPresenter().getFragments());
        sections.setAdapter(mAdapterSections);
        sections.setOffscreenPageLimit(mAdapterSections.getCount());
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }


    public String getTagName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public void showProgressBar(boolean b) {
    }

    @Override
    public void hideProgressBar(boolean b) {
        controller.hideProgressBar(b);
    }

    public static HomeFragment getInstance(Controller controller) {
        HomeFragment fragment = new HomeFragment();
        fragment.setController(controller);
        return fragment;
    }

    @Override
    public HomeFragmentPresenter getPresenter() {
        if (presenter == null) {
            presenter = new HomeFragmentPresenter(this);
        }
        return presenter;
    }

    @Override
    public void setUpLeftIconToolbar(int id) {
        controller.setUpLeftIconToolbar(id);
    }

    @Override
    public void setUpLeftIconToolbar(int id, int color) {
        controller.setUpLeftIconToolbar(id, color);
    }

}
