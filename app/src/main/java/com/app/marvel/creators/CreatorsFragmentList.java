package com.app.marvel.creators;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.marvel.base.BaseItemAdapter;
import com.app.marvel.base.FragmentList;
import com.app.marvel.base.ItemListener;
import com.app.marvel.base.Presenter;
import com.app.marvel.base.PresenterList;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Creatorss;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class CreatorsFragmentList extends FragmentList<BaseResponse<Data<Creatorss>>, Creatorss, CreatorsViewHolder> {


    private CreatorsFragmentPresenter presenter;

    public CreatorsFragmentList() {
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new BaseItemAdapter<Creatorss, CreatorsViewHolder>(idItemView, loadingView, retryIdLayout, getPresenterList().getLoadMoreListener(), canLoadMore) {
            @Override
            protected Creatorss getLoadingToken() {
                return new Creatorss();
            }

            @Override
            protected Creatorss getRetryToken() {
                return new Creatorss();
            }

            @Override
            public CreatorsViewHolder getMainViewHolderInstance(View inflate) {
                return new CreatorsViewHolder(inflate);
            }

            @Override
            protected boolean handled(RecyclerView.ViewHolder holder, Creatorss item, ItemListener<Creatorss> onItemClickedListener) {
                if (holder instanceof CreatorsViewHolder) {
                    CreatorsViewHolder CreatorsViewHolder = (CreatorsViewHolder) holder;
                    CreatorsViewHolder.bind(item, onItemClickedListener);
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
        return CreatorsFragmentList.class.getSimpleName();
    }


    @Override
    public void setData(BaseResponse<Data<Creatorss>> data) {
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
            presenter = new CreatorsFragmentPresenter(this);
        }
        return presenter;
    }

    public static CreatorsFragmentList getInstance(int titleStyle, int title, int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable) {
        CreatorsFragmentList fragment = new CreatorsFragmentList();
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

    public static CreatorsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager) {
        CreatorsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static CreatorsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst, int title, int titleStyle) {
        CreatorsFragmentList fragment = getInstance(titleStyle, title, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static CreatorsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst) {
        CreatorsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}
