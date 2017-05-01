package com.data.repository.creators;

import com.data.repository.characters.Repository;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Creatorss;

/**
 * Created by juan.delgado on 30/01/2017.
 */

public class RepositoryCreatorsImpl extends Repository implements RepositoryCreators {

    private final RepositoryServiceCloudCreators cloud;

    public RepositoryCreatorsImpl(TYPE type) {
        super(type);
        cloud = new RepositoryServiceCloudCreators();
    }

    @Override
    public BaseResponse<Data<Creatorss>> getCreators(RequestList requestProfile) throws Exception {
        switch (type) {
            default:
                return cloud.getCreators(requestProfile);
        }
    }

    @Override
    public BaseResponse<Data<Creatorss>> getCreator(RequestList request) throws Exception {
        switch (type) {
            default:
                return cloud.getCreator(request);
        }
    }

    @Override
    public void setCreators(BaseResponse<Data<Creatorss>> profile) throws Exception {
    }
}
