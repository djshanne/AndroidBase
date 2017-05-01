package com.model.realm;

import com.model.bean.common.Environment;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by juan.delgado on 08/09/2016.
 */
public class EnviromentRealm extends RealmObject {

    @PrimaryKey
    private String name;
    private String url;
    private boolean production;


    public EnviromentRealm(Environment o) {
        if (o == null) {
            return;
        }
        this.name = o.getName();
        this.url = o.getUrl();
        this.production = o.isProduction();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public EnviromentRealm() {
    }

}
