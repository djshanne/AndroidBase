package com.data.repository.series;

import com.data.repository.characters.Repository;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Seriess;

/**
 * Created by juan.delgado on 30/01/2017.
 */

public class RepositorySeriesImpl extends Repository implements RepositorySeries {

    private final RepositoryServiceCloudSeries cloud;

    public RepositorySeriesImpl(TYPE type) {
        super(type);
        cloud = new RepositoryServiceCloudSeries();
    }

    @Override
    public BaseResponse<Data<Seriess>> getCharacters(RequestList RequestSeries) throws Exception {
        switch (type) {
            default:
                return cloud.getCharacters(RequestSeries);
        }
    }

    @Override
    public BaseResponse<Data<Seriess>> getCharacter(RequestList RequestSeries) throws Exception {
        switch (type) {
            default:
                return cloud.getCharacter(RequestSeries);
        }
    }

    @Override
    public void setCharacters(BaseResponse<Data<Seriess>> profile) throws Exception {
    }
}
