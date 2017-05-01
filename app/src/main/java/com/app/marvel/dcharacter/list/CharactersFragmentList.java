package com.app.marvel.dcharacter.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.marvel.R;
import com.app.marvel.base.BaseItemAdapter;
import com.app.marvel.base.FragmentList;
import com.app.marvel.base.ItemListener;
import com.app.marvel.base.Presenter;
import com.app.marvel.base.PresenterList;
import com.app.marvel.dcharacter.DetailActivity;
import com.data.service.request.RequestCharacters;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class CharactersFragmentList extends FragmentList<BaseResponse<Data<Result>>, Result, CharacterViewHolder> {


    private CharactersFragmentPresenter presenter;

    public CharactersFragmentList() {
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new BaseItemAdapter<Result, CharacterViewHolder>(idItemView, loadingView, retryIdLayout, getPresenterList().getLoadMoreListener(), canLoadMore) {
            @Override
            protected Result getLoadingToken() {
                return new Result();
            }

            @Override
            protected Result getRetryToken() {
                return new Result();
            }

            @Override
            public CharacterViewHolder getMainViewHolderInstance(View inflate) {
                return new CharacterViewHolder(inflate);
            }

            @Override
            protected boolean handled(RecyclerView.ViewHolder holder, Result item, ItemListener<Result> onItemClickedListener) {
                if (holder instanceof CharacterViewHolder) {
                    CharacterViewHolder CharacterViewHolder = (CharacterViewHolder) holder;
                    CharacterViewHolder.bind(item, onItemClickedListener);
                    return true;
                } else {
                    return false;
                }
            }
        };
        mAdapter.setOnItemClickedListener(new ItemListener<Result>() {
            @Override
            public void onItemClicked(Result item) {
                DetailActivity.startWithId(getContext(), item.getId());
            }
        });

        mAdapter.setOnOnRetryClickedListener(() -> mAdapter.removeRetryView());
    }

    public String getTagName() {
        return CharactersFragmentList.class.getSimpleName();
    }


    @Override
    public void setData(BaseResponse<Data<Result>> data) {
        mAdapter.addData(data.getData().getResults());
        getRequest().setPage(mAdapter.getData().size());
        getRequest().setPageSize(data.getData().getLimit());
        mAdapter.setMaxPages(data.getData().getTotal()/data.getData().getLimit());
        mProgressBar.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);

    }


    @Override
    public PresenterList getPresenterList() {
        if (presenter == null) {
            presenter = new CharactersFragmentPresenter(this);
        }
        return presenter;
    }

    public static CharactersFragmentList getInstance(int titleStyle, int title, int loadingView, int idItemView, int retryIdLayout, RequestCharacters RequestCharacters, boolean canLoadMore, boolean mainLoadingIsEnable) {
        CharactersFragmentList fragment = new CharactersFragmentList();
        fragment.setLoadingView(loadingView);
        fragment.setIdItemView(idItemView);
        fragment.setRetryIdLayout(retryIdLayout);
        fragment.setRequest(RequestCharacters);
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

    public static CharactersFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestCharacters RequestCharacters, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager) {
        CharactersFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestCharacters, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(true);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static CharactersFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestCharacters RequestCharacters, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst, int title, int titleStyle) {
        CharactersFragmentList fragment = getInstance(titleStyle, title, loadingView, idItemView, retryIdLayout, RequestCharacters, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    public static CharactersFragmentList getInstance(int loadingView, int idItemView, int retryIdLayout, RequestCharacters RequestCharacters, boolean canLoadMore, boolean mainLoadingIsEnable, int layOutManager, boolean shouldLoadDataAtFirst) {
        CharactersFragmentList fragment = getInstance(0, 0, loadingView, idItemView, retryIdLayout, RequestCharacters, canLoadMore, mainLoadingIsEnable);
        fragment.setShouldLoadDataAtFirst(shouldLoadDataAtFirst);
        fragment.setLayOutManager(layOutManager);
        return fragment;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}
