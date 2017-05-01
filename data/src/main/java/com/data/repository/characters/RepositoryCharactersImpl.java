package com.data.repository.characters;

import com.data.repository.RepositoryServiceCloudCharacters;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by juan.delgado on 30/01/2017.
 */

public class RepositoryCharactersImpl extends Repository implements RepositoryCharacters {

    private final RepositoryServiceCloudCharacters cloud;

    public RepositoryCharactersImpl(TYPE type) {
        super(type);
        cloud = new RepositoryServiceCloudCharacters();
    }

    @Override
    public BaseResponse<Data<Result>> getCharacters(RequestList RequestList) throws Exception {
        switch (type) {
            default:
                return cloud.getCharacters(RequestList);
        }
    }

    @Override
    public BaseResponse<Data<Result>> getCharacter(RequestList RequestList) throws Exception {
        switch (type) {
            default:
                return cloud.getCharacter(RequestList);
        }
    }

    @Override
    public void setCharacters(BaseResponse<Data<Result>> profile) throws Exception {
    }
}
