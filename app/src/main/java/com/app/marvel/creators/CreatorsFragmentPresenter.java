package com.app.marvel.creators;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.data.service.request.RequestList;
import com.domain.usecase.GetCreatorsUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Creatorss;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class CreatorsFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Creatorss>>> controller;
    private RequestList RequestComics;
    private GetCreatorsUseCase<BaseResponse<Data<Creatorss>>> getCharactersUseCase;

    public CreatorsFragmentPresenter(ListFragmentController<BaseResponse<Data<Creatorss>>> controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetCreatorsUseCase<BaseResponse<Data<Creatorss>>>(new UseCaseListener<BaseResponse<Data<Creatorss>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Creatorss>> t) {
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
