package com.domain.usecase;

import android.text.TextUtils;

import com.data.repository.characters.Repository;
import com.data.repository.characters.RepositoryCharacters;
import com.data.repository.characters.RepositoryCharactersImpl;
import com.data.repository.creators.RepositoryCreators;
import com.data.repository.creators.RepositoryCreatorsImpl;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestList;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class GetCreatorsUseCase<T> extends UseCaseR<RequestList, T> {

    private RepositoryCreators repository;
    private RepositoryCharacters repositoryCharacters;

    public GetCreatorsUseCase(UseCaseListener<T> listener) {
        super(listener);
    }

    @Override
    protected T processRequest(Repository.TYPE type) throws Exception {
        repository = new RepositoryCreatorsImpl(type);
        repositoryCharacters = new RepositoryCharactersImpl(type);
        if (getRequest().getId() == 0) {
            return (T) repository.getCreators(getRequest());
        } else if (getRequest().getId() != 0 && !TextUtils.isEmpty(getRequest().getSection())&& getRequest() instanceof RequestCharacters) {
            return (T) repositoryCharacters.getCharacter(getRequest());
        }
            return (T) repository.getCreator(getRequest());

    }

    @Override
    protected String getTag() {
        return GetCreatorsUseCase.class.getSimpleName();
    }
}
