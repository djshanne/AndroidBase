package com.app.marvel.comics;

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
import com.model.bean.characters.comics.Comics;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class ComicsFragmentList extends FragmentList<BaseResponse<Data<Comics>>, Comics, ComicsViewHolder> {


    private ComicsFragmentPresenter presenter;

    public ComicsFragmentList() {
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new BaseItemAdapter<Comics, ComicsViewHolder>(idItemView, loadingView, retryIdLayout, getPresenterList().getLoadMoreListener(), canLoadMore) {
            @Override
            protected Comics getLoadingToken() {
                return new Comics();
            }

            @Override
            protected Comics getRetryToken() {
                return new Comics();
            }

            @Override
            public ComicsViewHolder getMainViewHolderInstance(View inflate) {
                return new ComicsViewHolder(inflate);
            }

            @Override
            protected boolean handled(RecyclerView.ViewHolder holder, Comics item, ItemListener<Comics> onItemClickedListener) {
                if (holder instanceof ComicsViewHolder) {
                    ComicsViewHolder ComicsViewHolder = (ComicsViewHolder) holder;
                    ComicsViewHolder.bind(item, onItemClickedListener);
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
        return ComicsFragmentList.class.getSimpleName();
    }


    @Override
    public void setData(BaseResponse<Data<Comics>> data) {
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
            presenter = new ComicsFragmentPresenter(this);
        }
        return presenter;
    }

    public static ComicsFragmentList getInstance(int titleStyle, int title, int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable) {
        ComicsFragmentList fragment = new ComicsFragmentList();
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

    public static ComicsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager) {
        ComicsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static ComicsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst, int title, int titleStyle) {
        ComicsFragmentList fragment = getInstance(titleStyle, title, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static ComicsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst) {
        ComicsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}
