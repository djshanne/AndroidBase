package com.domain.usecase;

import android.text.TextUtils;

import com.data.repository.characters.Repository;
import com.data.repository.characters.RepositoryCharacters;
import com.data.repository.characters.RepositoryCharactersImpl;
import com.data.repository.events.RepositoryEvents;
import com.data.repository.events.RepositoryEventsImpl;
import com.data.service.request.RequestCharacters;
import com.data.service.request.RequestList;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class GetEventsUseCase<T> extends UseCaseR<RequestList, T> {

    private RepositoryEvents repository;
    private RepositoryCharacters repositoryCharacters;

    public GetEventsUseCase(UseCaseListener<T> listener) {
        super(listener);
    }

    @Override
    protected T processRequest(Repository.TYPE type) throws Exception {
        repository = new RepositoryEventsImpl(type);
        repositoryCharacters = new RepositoryCharactersImpl(type);
        if (getRequest().getId() == 0) {
            return (T) repository.getEvents(getRequest());
        } else if (getRequest().getId() != 0 && !TextUtils.isEmpty(getRequest().getSection()) && getRequest() instanceof RequestCharacters)
            return (T) repositoryCharacters.getCharacter(getRequest());
        else
            return (T) repository.getEvent(getRequest());
    }

    @Override
    protected String getTag() {
        return GetEventsUseCase.class.getSimpleName();
    }
}
