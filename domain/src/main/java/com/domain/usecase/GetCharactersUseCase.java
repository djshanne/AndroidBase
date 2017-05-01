package com.domain.usecase;

import com.data.repository.characters.Repository;
import com.data.repository.characters.RepositoryCharacters;
import com.data.repository.characters.RepositoryCharactersImpl;
import com.data.service.request.RequestList;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class GetCharactersUseCase<T> extends UseCaseR<RequestList, T> {

    private RepositoryCharacters repository;

    public GetCharactersUseCase(UseCaseListener<T> listener) {
        super(listener);
    }

    @Override
    protected T processRequest(Repository.TYPE type) throws Exception {
        repository = new RepositoryCharactersImpl(type);
        if (getRequest().getId() == 0) {
            return (T) repository.getCharacters(getRequest());
        } else
            return (T) repository.getCharacter(getRequest());
    }

    @Override
    protected String getTag() {
        return GetCharactersUseCase.class.getSimpleName();
    }
}
