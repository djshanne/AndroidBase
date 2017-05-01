package com.app.marvel;

import android.support.v4.app.Fragment;

import com.app.marvel.base.Presenter;
import com.app.marvel.comics.ComicsFragmentList;
import com.app.marvel.creators.CreatorsFragmentList;
import com.app.marvel.dcharacter.list.CharactersFragmentList;
import com.app.marvel.events.EventsFragmentList;
import com.app.marvel.series.SeriesFragmentList;
import com.app.marvel.views.Controller;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestComics;
import com.data.service.request.RequestEvents;
import com.data.service.request.RequestSeries;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by juan.delgado on 07/09/2016.
 */
public class HomeFragmentPresenter extends Presenter {

    private final Controller controller;
    private CharactersFragmentList topList;
    private ComicsFragmentList bottomList;
//    private CreatorsFragmentList bottomList3;
    private EventsFragmentList bottomList4;
    private SeriesFragmentList bottomList5;
    private List<Fragment> fragments;

    public HomeFragmentPresenter(final Controller controller) {
        super(controller);
        this.controller = controller;
        fragments = new ArrayList<>();
    }

    public void initializeFragments() {
        topList = CharactersFragmentList.getInstance(R.style.GenericTitles, R.string.chtrs, R.layout.item_loading, R.layout.item_view, R.layout.item_retry, new RequestCharacters(), true, false);
        bottomList = ComicsFragmentList.getInstance(R.style.GenericTitles, R.string.comics, R.layout.item_loading, R.layout.item_view, R.layout.item_retry, new RequestComics(), false, false);
//        bottomList3 = CreatorsFragmentList.getInstance(R.style.GenericTitles, R.string.creators, R.layout.item_loading, R.layout.item_view, R.layout.item_retry, new RequestCreators(), true, false);
        bottomList4 = EventsFragmentList.getInstance(R.style.GenericTitles, R.string.events, R.layout.item_loading, R.layout.item_view, R.layout.item_retry, new RequestEvents(), true, false);
        bottomList5 = SeriesFragmentList.getInstance(R.style.GenericTitles, R.string.series, R.layout.item_loading, R.layout.item_view, R.layout.item_retry, new RequestSeries(), true, false);
    }

    public List<Fragment> getFragments() {
        fragments.clear();
        fragments.add(topList);
        fragments.add(bottomList);
//        fragments.add(bottomList3);
        fragments.add(bottomList4);
        fragments.add(bottomList5);
        return fragments;
    }
}
