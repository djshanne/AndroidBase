package com.data.repository;

import android.text.TextUtils;

import com.data.BuildConfig;
import com.data.service.error.BadRequestException;
import com.data.service.error.InternalConflictException;
import com.data.service.error.NotFoundException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/1/2017.
 */

public class RepositoryServiceCloudBase {

    private static int counter = 0;
    private String ts;

    protected Object handleResponse(Response r) throws Exception {
//        testError();
//        Thread.sleep(5000);
        switch (r.code()) {
            case 500:
            case 503:
                throw new InternalConflictException(r.errorBody().string());
            default:
                return r.body();
        }


    }

    protected void testError() throws BadRequestException, NotFoundException {
        counter++;
        if (counter > 2 && counter < 6) {
            throw new BadRequestException("Intentional Error");
        }
        if (counter > 7 && counter < 10) {
            throw new NotFoundException("Intentional Error");
        }
        if (counter > 13 && counter < 16) {
            throw new NotFoundException("Intentional Error");
        }

    }


    public String getTs() {
        if (TextUtils.isEmpty(ts)) {
            Long tsLong = System.currentTimeMillis() / 1000;
            ts = tsLong.toString();
        }
        return ts;
    }


    private String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    protected String getApiKey() {
        return BuildConfig.API_KEY_PUBLIC;
    }


    protected String getHash() {
        return md5(getTs() + BuildConfig.API_KEY_PRIVATE + BuildConfig.API_KEY_PUBLIC);
    }

}
