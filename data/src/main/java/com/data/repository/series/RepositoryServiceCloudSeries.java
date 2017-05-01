package com.data.repository.series;

import com.data.repository.RepositoryServiceCloudBase;
import com.data.service.Cloud;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Seriess;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class RepositoryServiceCloudSeries extends RepositoryServiceCloudBase implements RepositorySeries {

    public RepositoryServiceCloudSeries() {
    }

    @Override
    public BaseResponse<Data<Seriess>> getCharacters(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Seriess>>> r = Cloud.getApiApp().getSeries(getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return resultFiltered((BaseResponse<Data<Seriess>>) handleResponse(r));
        }
    }

    @Override
    public BaseResponse<Data<Seriess>> getCharacter(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Seriess>>> r = Cloud.getApiApp().getSerie(RequestList.getId(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Seriess>>) handleResponse(r);
        }
    }

    protected BaseResponse<Data<Seriess>> resultFiltered(BaseResponse<Data<Seriess>> resultBaseResponse) {
        List<Seriess> results = resultBaseResponse.getData().getResults();
        List<Seriess> resultsFiltered = new ArrayList<>();
        for (Seriess result : results) {
            if (!result.getThumbnail().getPath().contains("image_not_available")) {
                resultsFiltered.add(result);
            }
        }
        resultBaseResponse.getData().setResults(resultsFiltered);
        return resultBaseResponse;
    }


    @Override
    public void setCharacters(BaseResponse<Data<Seriess>> Characters) throws Exception {

    }


}
