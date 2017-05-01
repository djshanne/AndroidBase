package com.app.marvel.base;

import com.app.marvel.views.Controller;
import com.data.service.request.Request;

/**
 * Created by juan.delgado on 31/03/2017.
 */

public abstract class PresenterList extends Presenter {

    protected boolean hasMoreItems;

    public PresenterList(Controller controller) {
        super(controller);
    }

    public abstract void getData(Request request);

    public abstract LoadMoreItemsListener getLoadMoreListener();


    public void handleOnSuccess(ListFragmentController controller, boolean hasMoreItems) {
        controller.hideProgressBar(true);
        this.hasMoreItems = hasMoreItems;
        controller.tryToShowEmptyView();
    }

    public void handleOnError(ListFragmentController controller) {
        hasMoreItems = true;
        controller.hideProgressBar(true);
        controller.hideLazyLoadingProgressView();
        controller.tryToShowRetryView();
    }

}
