package com.app.marvel.series;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.data.service.request.RequestList;
import com.domain.usecase.GetSeriesUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Seriess;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class SeriesFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Seriess>>> controller;
    private RequestList RequestComics;
    private GetSeriesUseCase<BaseResponse<Data<Seriess>>> getCharactersUseCase;

    public SeriesFragmentPresenter(ListFragmentController<BaseResponse<Data<Seriess>>> controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetSeriesUseCase<BaseResponse<Data<Seriess>>>(new UseCaseListener<BaseResponse<Data<Seriess>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Seriess>> t) {
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
