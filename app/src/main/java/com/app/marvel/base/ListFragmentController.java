package com.app.marvel.base;

import com.app.marvel.views.Controller;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public interface ListFragmentController<Response> extends Controller {
    public void hideLazyLoadingProgressView();
    void setData(Response data);

    void tryToShowEmptyView();

    void tryToShowRetryView();
}
