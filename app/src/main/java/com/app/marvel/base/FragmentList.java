package com.app.marvel.base;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.marvel.R;
import com.app.marvel.utils.Utils;
import com.app.marvel.views.Controller;
import com.app.marvel.views.TextViewCustom;
import com.data.service.request.RequestList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Juan Delgado on 2/3/2017.
 */

public abstract class FragmentList<Response, AdapterIem, ItemViewHolder extends RecyclerView.ViewHolder> extends BaseFragment implements ListFragmentController<Response> {

    protected Controller controller;
    @BindView(R.id.generic_list_title)
    public
    TextViewCustom mTitle;
    @BindView(R.id.generic_list)
    protected
    RecyclerView mRecyclerView;
    @BindView(R.id.generic_list_progress)
    protected
    View mProgressBar;
    @BindView(R.id.generic_list_empty)
    protected
    ImageView mEmptyView;
    @BindView(R.id.generic_list_retry)
    ImageView mRetryView;

    protected BaseItemAdapter<AdapterIem, ItemViewHolder> mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected int idItemView;
    protected int loadingView;
    protected RequestList request;
    protected boolean canLoadMore;
    protected boolean mainLoadingIsEnable;
    protected int layOutManager;
    protected int retryIdLayout;
    protected boolean shouldLoadDataAtFirst;
    protected int title;
    protected int titleStyle;
    private boolean canModifyTitle;
    private boolean nesterScrollEnabled;
    private String titleTab;

    public FragmentList() {
    }

    public void setNestedScrollEnable(boolean b) {
        nesterScrollEnabled = b;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generic_recycler_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.setIconColor(getContext(), android.R.color.white, R.drawable.ic_replay_black_24dp, mRetryView);
        Utils.setIconColor(getContext(), android.R.color.white, R.drawable.empty_white_box, mEmptyView);
        setUpProgressBar();
        setUpView();
        if (shouldLoadDataAtFirst)
            getPresenterList().getData(getRequest());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initController();
    }


    protected abstract void initializeAdapter();

    protected void setUpView() {
        mLayoutManager = new LinearLayoutManager(getContext(), layOutManager, false);
        mRecyclerView.setNestedScrollingEnabled(nesterScrollEnabled);
        mRecyclerView.setLayoutManager(mLayoutManager);
        initializeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mEmptyView.setVisibility(View.GONE);
        mRetryView.setVisibility(View.GONE);

        if (titleStyle != 0)
            applyStyle();

        mTitle.setFont(R.string.Marvel);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,getResources().getDimension(R.dimen.home_text_size_header));

        if (title != 0) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
        } else
            mTitle.setVisibility(View.GONE);
    }

    protected void setListTitle(String s) {
        if (canModifyTitle) {
            mTitle.setText(s);
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setCanModifyTitle(boolean canModifyTitle) {
        this.canModifyTitle = canModifyTitle;
    }

    protected void applyStyle() {
        mTitle.setTextAppearance(getContext(), titleStyle);
        // The attributes you want retrieved
        int[] attrs = {android.R.attr.background, android.R.attr.layout_margin};
        // Parse MyCustomStyle, using Context.obtainStyledAttributes()
        TypedArray ta = getActivity().getTheme().obtainStyledAttributes(titleStyle, attrs);
        // Fetching the colors defined in your style
        try {
            mTitle.setBackgroundColor(ta.getColor(0, Color.TRANSPARENT));
        } catch (Exception e) {
        }
        try {
            mTitle.setBackgroundDrawable(ta.getDrawable(0));
        } catch (Exception e) {
        }
        ta.recycle();
    }


    protected void initController() {
        if (controller == null)
            controller = (Controller) getActivity();
    }

    protected void setUpProgressBar() {
        mProgressBar.setOnTouchListener((view, motionEvent) -> true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    public String getTagName() {
        return FragmentList.class.getSimpleName();
    }

    @Override
    public void showProgressBar(boolean b) {
//        controller.showProgressBar();
        if (mainLoadingIsEnable || mAdapter.getItemCount() == 0)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(boolean b) {
//        controller.hideProgressBar();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideLazyLoadingProgressView() {
        mAdapter.tryToRemoveLoadingView();
    }

    @Override
    public void tryToShowEmptyView() {
        if (mAdapter.getItemCount() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void tryToShowRetryView() {
        if (mAdapter.getItemCount() == 0) {
            mRetryView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.generic_list_retry)
    public void onRetryClicked() {
        mRetryView.setVisibility(View.GONE);
        getPresenterList().getData(getRequest());
    }


    public void setRetryIdLayout(int retryIdLayout) {
        this.retryIdLayout = retryIdLayout;
    }

    public void setTitleStyle(int titleStyle) {
        this.titleStyle = titleStyle;
    }

    public void setLayOutManager(int layOutManager) {
        this.layOutManager = layOutManager;
    }

    public void setMainLoadingIsEnable(boolean mainLoadingIsEnable) {
        this.mainLoadingIsEnable = mainLoadingIsEnable;
    }

    public void setIdItemView(int idItemView) {
        this.idItemView = idItemView;
    }


    public void setRequest(RequestList request) {
        this.request = request;
    }

    public RequestList getRequest() {
        return request;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public void loadFragmentById(Uri navigation) {
        mEmptyView.setVisibility(View.GONE);
        mRetryView.setVisibility(View.GONE);
        request.resetPage();
        mAdapter.clearData();
        request.setNavigation(navigation);

        getPresenterList().getData(request);
    }

    public void setLoadingView(int loadingView) {
        this.loadingView = loadingView;
    }


    public void setShouldLoadDataAtFirst(boolean shouldLoadDataAtFirst) {
        this.shouldLoadDataAtFirst = shouldLoadDataAtFirst;
    }

    public boolean isShouldLoadDataAtFirst() {
        return shouldLoadDataAtFirst;
    }

    public void setTitle(int title) {
        this.title = title;
    }


    public void resetTitle() {
        if (canModifyTitle) {
            mTitle.setText("...");
        }
    }

    public void setTitleTab(String titleTab) {
        this.titleTab = titleTab;
    }

    public String getTitleTab() {
        return titleTab;
    }
}
