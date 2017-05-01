package com.data.service.request;

import android.net.Uri;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class RequestList extends Request {

    protected int page;
    protected int navigation;
    protected int pageSize = 10;
    protected Uri currentNavigationPath;
    protected int id;

    public static String COMICS = "comics";
    public static String EVENTS = "events";
    public static String SERIES = "series";
    public static String STORIES = "stories";

    public RequestList(int id, String section) {
        this.section = section;
        this.id = id;
        page = 0;
        navigation = 1;
    }



    public RequestList(int id) {
        this.id = id;
        page = 0;
        navigation = 1;
    }


    private String section;

    //    @Query("from") int from, @Query("size") int size, @Query("nodes[]") String nodes
    public RequestList() {
        super();
        page = 0;
        navigation = 1;
    }

    public String getSection() {
        return section;
    }

    public int getId() {
        return id;
    }


    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void resetPage() {
        page = 1;
    }

    public void setNavigation(Uri currentNavigationPath) {
        this.currentNavigationPath = currentNavigationPath;
    }

    public Uri getCurrentNavigationPath() {
        return currentNavigationPath;
    }

    public void setNavigation(int navigation) {
        this.navigation = navigation;
    }

    public int getNavigation() {
        return navigation;
    }
}
