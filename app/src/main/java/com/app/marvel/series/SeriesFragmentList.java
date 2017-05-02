package com.app.marvel.series;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.marvel.R;
import com.app.marvel.base.BaseItemAdapter;
import com.app.marvel.base.FragmentList;
import com.app.marvel.base.ItemListener;
import com.app.marvel.base.Presenter;
import com.app.marvel.base.PresenterList;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Seriess;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class SeriesFragmentList extends FragmentList<BaseResponse<Data<Seriess>>, Seriess, SeriesViewHolder> {


    private SeriesFragmentPresenter presenter;

    public SeriesFragmentList() {
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new BaseItemAdapter<Seriess, SeriesViewHolder>(idItemView, loadingView, retryIdLayout, getPresenterList().getLoadMoreListener(), canLoadMore) {
            @Override
            protected Seriess getLoadingToken() {
                return new Seriess();
            }

            @Override
            protected Seriess getRetryToken() {
                return new Seriess();
            }

            @Override
            public SeriesViewHolder getMainViewHolderInstance(View inflate) {
                return new SeriesViewHolder(inflate);
            }

            @Override
            protected boolean handled(RecyclerView.ViewHolder holder, Seriess item, ItemListener<Seriess> onItemClickedListener) {
                if (holder instanceof SeriesViewHolder) {
                    SeriesViewHolder SeriesViewHolder = (SeriesViewHolder) holder;
                    SeriesViewHolder.bind(item, onItemClickedListener);
                    return true;
                } else {
                    return false;
                }
            }
        };
        mAdapter.setOnItemClickedListener(item -> {

        });

        mAdapter.setOnOnRetryClickedListener(() -> mAdapter.removeRetryView());
    }

    public String getTagName() {
        return SeriesFragmentList.class.getSimpleName();
    }


    @Override
    public void setData(BaseResponse<Data<Seriess>> data) {
        mAdapter.addData(data.getData().getResults());
        getRequest().setPage(mAdapter.getData().size());
        getRequest().setPageSize(data.getData().getLimit());
        mAdapter.setMaxPages(data.getData().getTotal() / data.getData().getLimit());
        mProgressBar.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }


    @Override
    public PresenterList getPresenterList() {
        if (presenter == null) {
            presenter = new SeriesFragmentPresenter(this);
        }
        return presenter;
    }

    public static SeriesFragmentList getInstance(int titleStyle, int title, int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable) {
        SeriesFragmentList fragment = new SeriesFragmentList();
        fragment.setLoadingView(loadingView);
        fragment.setIdItemView(idItemView);
        fragment.setRetryIdLayout(retryIdLayout);
        fragment.setRequest(RequestList);
        fragment.setCanLoadMore(canLoadMore);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setTitle(title);
        fragment.setTitleStyle(titleStyle);
        fragment.setMainLoadingIsEnable(mainLoadingIsEnable);
        fragment.setLayOutManager(LinearLayoutManager.HORIZONTAL);
        return fragment;
    }

    public void setRetryIdLayout(int retryIdLayout) {
        this.retryIdLayout = retryIdLayout;
    }

    public static SeriesFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager) {
        SeriesFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static SeriesFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst, int title, int titleStyle) {
        SeriesFragmentList fragment = getInstance(titleStyle, title, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static SeriesFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst) {
        SeriesFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}
