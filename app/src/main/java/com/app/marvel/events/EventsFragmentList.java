package com.app.marvel.events;

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
import com.model.bean.characters.Eventss;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class EventsFragmentList extends FragmentList<BaseResponse<Data<Eventss>>, Eventss, EventsViewHolder> {


    private EventsFragmentPresenter presenter;

    public EventsFragmentList() {
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new BaseItemAdapter<Eventss, EventsViewHolder>(idItemView, loadingView, retryIdLayout, getPresenterList().getLoadMoreListener(), canLoadMore) {
            @Override
            protected Eventss getLoadingToken() {
                return new Eventss();
            }

            @Override
            protected Eventss getRetryToken() {
                return new Eventss();
            }

            @Override
            public EventsViewHolder getMainViewHolderInstance(View inflate) {
                return new EventsViewHolder(inflate);
            }

            @Override
            protected boolean handled(RecyclerView.ViewHolder holder, Eventss item, ItemListener<Eventss> onItemClickedListener) {
                if (holder instanceof EventsViewHolder) {
                    EventsViewHolder EventsViewHolder = (EventsViewHolder) holder;
                    EventsViewHolder.bind(item, onItemClickedListener);
                    return true;
                } else {
                    return false;
                }
            }
        };
        mAdapter.setOnItemClickedListener(new ItemListener<Eventss>() {
            @Override
            public void onItemClicked(Eventss item) {
//                DetailActivity.startWithId(getContext(), item.getSlug(), getRequest().getCurrentNavigationPath() == null ? "" : getRequest().getCurrentNavigationPath().getPath().replaceFirst("/", ""));
            }
        });

        mAdapter.setOnOnRetryClickedListener(() -> mAdapter.removeRetryView());
    }

    public String getTagName() {
        return EventsFragmentList.class.getSimpleName();
    }


    @Override
    public void setData(BaseResponse<Data<Eventss>> data) {
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
            presenter = new EventsFragmentPresenter(this);
        }
        return presenter;
    }

    public static EventsFragmentList getInstance(int titleStyle, int title, int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable) {
        EventsFragmentList fragment = new EventsFragmentList();
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

    public static EventsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager) {
        EventsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static EventsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst, int title, int titleStyle) {
        EventsFragmentList fragment = getInstance(titleStyle, title, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static EventsFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestList RequestList, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst) {
        EventsFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestList, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}
