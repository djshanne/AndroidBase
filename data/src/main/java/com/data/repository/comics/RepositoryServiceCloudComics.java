package com.data.repository.comics;

import com.data.repository.RepositoryServiceCloudBase;
import com.data.service.Cloud;
import com.data.service.request.RequestList;
import com.data.utils.Utils;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;
import com.model.bean.characters.comics.Comics;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class RepositoryServiceCloudComics extends RepositoryServiceCloudBase implements RepositoryComics {

    public RepositoryServiceCloudComics() {
    }

    @Override
    public BaseResponse<Data<Comics>> getComics(RequestList requestCharacters) throws Exception {
        Response<BaseResponse<Data<Comics>>> r = Cloud.getApiApp().getComics(getApiKey(), getHash(), getTs(), requestCharacters.getPage()).execute();
        switch (r.code()) {
            default:
                return new Utils<Comics>().resultFiltered((BaseResponse<Data<Comics>>) handleResponse(r));
        }
    }

    @Override
    public BaseResponse<Data<Comics>> getComic(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Result>>> r;
        r = Cloud.getApiApp().getComic(RequestList.getId(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Comics>>) handleResponse(r);
        }
    }

    @Override
    public void setComics(BaseResponse<Data<Comics>> Characters) throws Exception {

    }


}
