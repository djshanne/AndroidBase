package com.data.service.request;

import com.data.BuildConfig;
import com.google.gson.annotations.Expose;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class Request {

    protected String companySlug = BuildConfig.API_COMPANY_NAME;

    public String getCompanySlug() {
        return companySlug;
    }

    public void setCompanySlug(String companySlug) {
        this.companySlug = companySlug;
    }
}
