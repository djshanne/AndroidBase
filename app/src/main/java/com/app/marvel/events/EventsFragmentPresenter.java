package com.app.marvel.events;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.data.service.request.RequestList;
import com.domain.usecase.GetEventsUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class EventsFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Eventss>>> controller;
    private RequestList RequestComics;
    private GetEventsUseCase<BaseResponse<Data<Eventss>>> getCharactersUseCase;

    public EventsFragmentPresenter(ListFragmentController<BaseResponse<Data<Eventss>>> controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetEventsUseCase<BaseResponse<Data<Eventss>>>(new UseCaseListener<BaseResponse<Data<Eventss>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Eventss>> t) {
                controller.setData(t);
                handleOnSuccess(controller, !t.getData().getResults().isEmpty());
            }

            @Override
            public void onFailure(Exception error) {
                handleOnError(controller);
            }
        }) {
            @Override
            public RequestList getRequest() {
                return RequestComics;
            }

            @Override
            public boolean canOperateOnView() {
                return canOperateOnView;
            }
        };
    }

    @Override
    public void getData(Request request) {
        this.RequestComics = (RequestList) request;
        controller.showProgressBar(false);
        getCharactersUseCase.execute(Repository.TYPE.CLOUD);
    }


    public LoadMoreItemsListener getLoadMoreListener() {
        return this;
    }

    @Override
    public void onLoadMore() {
        if (hasMoreItems) {
            controller.showProgressBar(false);
            getCharactersUseCase.execute(Repository.TYPE.CLOUD);
        }
    }

}
