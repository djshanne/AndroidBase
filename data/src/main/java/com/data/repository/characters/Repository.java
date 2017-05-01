package com.data.repository.characters;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public abstract class Repository {
    protected final TYPE type;

    public Repository(TYPE type) {
        this.type = type;
    }

    public enum TYPE {CLOUD, DISK, SHARED, LOGIC, DISK_PRIORITY, CLOUD_PRIORITY}
}
