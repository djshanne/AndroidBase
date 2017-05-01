package com.model.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.model.realm.EnviromentRealm;

/**
 * Created by juan.delgado on 08/09/2016.
 */
public class Environment implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name="";

    @SerializedName("url")
    @Expose
    private String url="";

    @SerializedName("production")
    @Expose
    private boolean production = false;


    public Environment(EnviromentRealm o) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeByte(this.production ? (byte) 1 : (byte) 0);
    }

    public Environment() {
        name = "";
        url = "";
        production = false;
    }

    protected Environment(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.production = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Environment> CREATOR = new Parcelable.Creator<Environment>() {
        @Override
        public Environment createFromParcel(Parcel source) {
            return new Environment(source);
        }

        @Override
        public Environment[] newArray(int size) {
            return new Environment[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Environment that = (Environment) o;

        if (production != that.production) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;

    }

}
