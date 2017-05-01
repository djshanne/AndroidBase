package com.model;

import com.model.bean.common.Environment;
import com.model.realm.EnviromentRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by juan.delgado on 27/05/2016.
 */
public class Mappers {

    public static List<Environment> mapEnviromentObject(RealmResults<EnviromentRealm> themes) {
        List<Environment> map = new ArrayList<>();
        if (themes != null) {
            for (EnviromentRealm t : themes) {
                map.add(new Environment(t));
            }
        }
        return map;
    }

    public static List<Environment> mapEnviromentObject(RealmList<EnviromentRealm> env) {
        List<Environment> map = new ArrayList<>();
        if (env != null) {
            for (EnviromentRealm t : env) {
                map.add(new Environment(t));
            }
        }
        return map;
    }

    public static RealmList<EnviromentRealm> mapEnviromentRealm(List<Environment> env) {
        RealmList<EnviromentRealm> map = new RealmList<>();
        if (env != null) {
            for (Environment t : env) {
                map.add(new EnviromentRealm(t));
            }
        }
        return map;
    }


}
