package com.data.repository;

import android.text.TextUtils;

import com.data.repository.characters.RepositoryCharacters;
import com.data.service.Cloud;
import com.data.service.request.RequestList;
import com.data.utils.Utils;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class RepositoryServiceCloudCharacters extends RepositoryServiceCloudBase implements RepositoryCharacters {

    public RepositoryServiceCloudCharacters() {
    }

    @Override
    public BaseResponse<Data<Result>> getCharacters(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Result>>> r = Cloud.getApiApp().getCharacters(getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return new Utils<Result>().resultFiltered((BaseResponse<Data<Result>>) handleResponse(r));
        }
    }

    @Override
    public BaseResponse<Data<Result>> getCharacter(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Result>>> r;

        if (!TextUtils.isEmpty(RequestList.getSection()))
            r = Cloud.getApiApp().getCharacterSection(RequestList.getId(), RequestList.getSection(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        else
            r = Cloud.getApiApp().getCharacter(RequestList.getId(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Result>>) handleResponse(r);
        }
    }


    @Override
    public void setCharacters(BaseResponse<Data<Result>> Characters) throws Exception {

    }


}
