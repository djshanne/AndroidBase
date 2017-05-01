package com.app.marvel.base;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Delgado on 2/15/2017.
 */

public abstract class BaseItemAdapter<Item, MainViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Item> data = new ArrayList<>();
    private final int ITEM_TYPE = 1;
    private final int LOADING_TYPE = 2;
    private final int RETRY_TYPE = 3;
    private final int itemIdLayout;
    private final int loadingIdLayout;
    private final LoadMoreItemsListener loadMoreListener;
    private final Item retryToken;
    private final Item loadingToken;
    private boolean loadMoreEnabled;
    private boolean lockLoadMoreByEmptyResults;
    private boolean lockLoadMoreByError;
    private ItemListener<Item> onItemClickedListener;
    private RetryListener onOnRetryClickedListener;
    private int counter;
    private int retryIdLayout;
    private Integer maxPages;
    private Integer pagesCount;


    public BaseItemAdapter(int idLayout, int loadingView, int retryIdLayout, LoadMoreItemsListener listener, boolean canLoadMore) {
        this.itemIdLayout = idLayout;
        this.loadingIdLayout = loadingView;
        this.loadMoreListener = listener;
        this.retryIdLayout = retryIdLayout;
        loadMoreEnabled = canLoadMore;
        counter = 0;
        pagesCount = 0;
        retryToken = getRetryToken();
        loadingToken = getLoadingToken();
        clearData();
    }

    public void addData(List<Item> data) {
        removeLoadingView(data);
        this.data.addAll(data);
        pagesCount++;
        notifyDataSetChanged();
    }

    public List<Item> getData() {
        return data;
    }

    public void removeLoadingView(List<Item> data) {
        if (!this.data.isEmpty())
            this.data.remove(this.data.indexOf(loadingToken));
        lockLoadMoreByEmptyResults = data == null || data.isEmpty();
    }

    protected abstract Item getLoadingToken();

    protected abstract Item getRetryToken();

    public void removeRetryView() {
        counter = 0;
        lockLoadMoreByError = false;
        if (!this.data.isEmpty())
            this.data.remove(this.data.indexOf(retryToken));
        notifyDataSetChanged();
    }

    public void tryToRemoveLoadingView() {
        try {
            if (!this.data.isEmpty())
                this.data.remove(this.data.indexOf(loadingToken));
        } catch (ArrayIndexOutOfBoundsException a) {
            a.printStackTrace();
        }
        lockLoadMoreByEmptyResults = false;
        notifyDataSetChanged();
        handleLockByError();

    }

    private void handleLockByError() {
        if (counter >= 3) {
            lockLoadMoreByError = true;
            addRetryView();
        }
        counter++;
        if (data.isEmpty())
            counter = 0;
    }

    private void addRetryView() {
        this.data.add(retryToken);
        new Handler().post(() -> notifyDataSetChanged());
    }

    public void clearData() {
        this.pagesCount = 0;
        this.counter = 0;
        lockLoadMoreByError = false;
        lockLoadMoreByEmptyResults = false;
        tryToRemoveLoadingView();
        this.data.clear();
//        notifyDataSetChanged();
    }

    public abstract MainViewHolder getMainViewHolderInstance(View inflate);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE:
                return getMainViewHolderInstance(LayoutInflater.from(parent.getContext()).inflate(itemIdLayout, parent, false));
            case LOADING_TYPE:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(loadingIdLayout, parent, false));
            case RETRY_TYPE:
                return new RetryViewHolder(LayoutInflater.from(parent.getContext()).inflate(retryIdLayout, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (handled(holder, data.get(position), onItemClickedListener)) {
            handleLoading(holder.getAdapterPosition());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        } else if (holder instanceof RetryViewHolder) {
            RetryViewHolder retryViewHolder = (RetryViewHolder) holder;
            retryViewHolder.bind(onOnRetryClickedListener);
        }
    }

    protected abstract boolean handled(RecyclerView.ViewHolder holder, Item item, ItemListener<Item> onItemClickedListener);

    private void handleLoading(int position) {
        if (position == data.size() - 1 && loadMoreListener != null && loadMoreEnabled && !lockLoadMoreByEmptyResults && !lockLoadMoreByError && pagesCount < maxPages) {
            data.add(loadingToken);
            new Handler().post(() -> notifyDataSetChanged());
            loadMoreListener.onLoadMore();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).equals(loadingToken)) {
            return LOADING_TYPE;
        } else if (data.get(position).equals(retryToken)) {
            return RETRY_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }

    public void setOnItemClickedListener(ItemListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public void setOnOnRetryClickedListener(RetryListener onOnRetryClickedListener) {
        this.onOnRetryClickedListener = onOnRetryClickedListener;
    }

    public void setMaxPages(Integer maxPages) {
        this.maxPages = maxPages;
    }
}
