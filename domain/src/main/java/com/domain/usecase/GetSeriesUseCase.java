package com.domain.usecase;

import android.text.TextUtils;

import com.data.repository.characters.Repository;
import com.data.repository.characters.RepositoryCharacters;
import com.data.repository.characters.RepositoryCharactersImpl;
import com.data.repository.series.RepositorySeries;
import com.data.repository.series.RepositorySeriesImpl;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestList;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class GetSeriesUseCase<T> extends UseCaseR<RequestList, T> {

    private RepositorySeries repository;
    private RepositoryCharacters repositoryCharacters;

    public GetSeriesUseCase(UseCaseListener<T> listener) {
        super(listener);
    }

    @Override
    protected T processRequest(Repository.TYPE type) throws Exception {
        repository = new RepositorySeriesImpl(type);
        repositoryCharacters = new RepositoryCharactersImpl(type);
        if (getRequest().getId() == 0) {
            return (T) repository.getCharacters(getRequest());
        } else if (getRequest().getId() != 0 && !TextUtils.isEmpty(getRequest().getSection()) && getRequest() instanceof RequestCharacters)
            return (T) repositoryCharacters.getCharacter(getRequest());
        else
            return (T) repository.getCharacter(getRequest());
    }

    @Override
    protected String getTag() {
        return GetSeriesUseCase.class.getSimpleName();
    }
}
