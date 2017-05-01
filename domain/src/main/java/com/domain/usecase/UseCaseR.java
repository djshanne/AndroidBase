package com.domain.usecase;

import android.util.Log;

import com.data.repository.characters.Repository;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class UseCaseR<Request, Response> {
    protected UseCaseListener listener;

    private Task<Response> task;
    public abstract Request getRequest();

    public abstract boolean canOperateOnView();

    public void removeListener() {
        listener = null;
    }

    public UseCaseR(UseCaseListener listener) {
        this.listener = listener;
    }

    protected abstract Response processRequest(Repository.TYPE type) throws Exception;

    protected abstract String getTag();

    public UseCaseR execute(final Repository.TYPE type) {
        if (task == null || !task.isExecuting()) {
            getTask(type, 0).execute();
        } else {
            Log.e(getTag(), "Executing:" + task.isExecuting() + " Name:" + getTag());
        }
        return this;
    }

    private Task<Response> getTask(Repository.TYPE type, int i) {
        task = new Task<Response>(listener, type, i) {
            @Override
            protected Response prcsRqst(Repository.TYPE type) throws Exception {
                return processRequest(type);
            }

            @Override
            public boolean shouldDeliverCallback() {
                return canOperateOnView();
            }

            @Override
            protected String getTg() {
                return getTag();
            }

        };
        return task;
    }

    public UseCaseR execute(final Repository.TYPE type, int taskDelayedBy) {
        if (task == null || !task.isExecuting()) {
            getTask(type, taskDelayedBy).execute();
        } else {
            Log.e(getTag(), "Executing:" + task.isExecuting() + " Name:" + getTag());
        }
        return this;
    }

    public Response executeSync(Repository.TYPE type) {
        try {
            return processRequest(type);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(getTag(), ex.getMessage());
        }
        return null;
    }

}
