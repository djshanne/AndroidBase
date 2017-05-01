package com.app.marvel.dcharacter;

import com.app.marvel.base.Presenter;
import com.data.repository.characters.Repository;
import com.data.service.request.RequestCharacters;
import com.domain.usecase.GetCharactersUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class DetailFragmentPresenter extends Presenter {


    private final DetailFragmentController controller;
    private RequestCharacters requestCharacters;
    private GetCharactersUseCase<BaseResponse<Data<Result>>> getCharactersUseCase;

    public DetailFragmentPresenter(final DetailFragmentController controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetCharactersUseCase<BaseResponse<Data<Result>>>(new UseCaseListener<BaseResponse<Data<Result>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Result>> t) {
                controller.hideProgressBar(true);
                if (!t.getData().getResults().isEmpty()) {
                    controller.renderView(t.getData().getResults().get(0));
                } else {
                    controller.dismiss();
                }
            }

            @Override
            public void onFailure(Exception error) {
                controller.hideProgressBar(true);
//                controller.dismiss();
            }
        }) {
            @Override
            public RequestCharacters getRequest() {
                return requestCharacters;
            }

            @Override
            public boolean canOperateOnView() {
                return canOperateOnView;
            }
        };
    }


    public void getDetail() {
        controller.showProgressBar(false);
        requestCharacters = new RequestCharacters(controller.getSlug());
        getCharactersUseCase.execute(Repository.TYPE.CLOUD);
    }

}
