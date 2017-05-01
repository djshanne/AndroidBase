package com.data.repository.comics;

import com.data.repository.characters.Repository;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Comics;

/**
 * Created by juan.delgado on 30/01/2017.
 */

public class RepositoryComicsImpl extends Repository implements RepositoryComics {

    private final RepositoryServiceCloudComics cloud;

    public RepositoryComicsImpl(TYPE type) {
        super(type);
        cloud = new RepositoryServiceCloudComics();
    }

    @Override
    public BaseResponse<Data<Comics>> getComics(RequestList requestProfile) throws Exception {
        switch (type) {
            default:
                return cloud.getComics(requestProfile);
        }
    }


    @Override
    public BaseResponse<Data<Comics>> getComic(RequestList RequestList) throws Exception {
        switch (type) {
            default:
                return cloud.getComic(RequestList);
        }
    }

    @Override
    public void setComics(BaseResponse<Data<Comics>> profile) throws Exception {
    }
}
