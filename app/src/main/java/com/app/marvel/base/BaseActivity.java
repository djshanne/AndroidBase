package com.app.marvel.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.marvel.R;
import com.app.marvel.utils.Utils;
import com.app.marvel.views.Controller;
import com.app.marvel.views.TextViewCustom;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements Controller {

    @BindView(R.id.main_progress)
    public View mProgressBar;
    private static final String MAIN_FRAGMENT_ID_KEY = "01";
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Nullable
    @BindView(R.id.tool_bar_title)
    public TextViewCustom titleToolBar;

    @Nullable
    @BindView(R.id.tool_bar_logo)
    public ImageView logoToolbar;

    protected Presenter presenter;
    private Menu currentMenu;


    @Override
    public PresenterList getPresenterList() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_activity);
        ((ViewGroup) getWindow().getDecorView().getRootView().findViewById(R.id.main_container)).addView(inflateMainView());


        if (getMainFragmentTag() != null && getMainFragment() != null)
            if (savedInstanceState == null || savedInstanceState.getInt(MAIN_FRAGMENT_ID_KEY, 0) == 0)
                getSupportFragmentManager().beginTransaction().
                        add(getMainIdContainer(), getMainFragment(), getMainFragmentTag()).
                        commitAllowingStateLoss();
        if (getMainFragmentTag() != null && getMainFragment() != null)
            if (savedInstanceState != null && getSupportFragmentManager().findFragmentById(savedInstanceState.getInt(MAIN_FRAGMENT_ID_KEY, 0)) != null && getMainFragmentTag() != null) {
                while (getSupportFragmentManager().popBackStackImmediate() && getSupportFragmentManager().getBackStackEntryCount() > 0)
                    ;
                getSupportFragmentManager().beginTransaction().
                        remove(getSupportFragmentManager().findFragmentById(savedInstanceState.getInt(MAIN_FRAGMENT_ID_KEY, 0))).
                        add(getMainIdContainer(), getMainFragment(), getMainFragmentTag()).commitNow();
            }
        afterMainViewIsInflated(savedInstanceState);
        ButterKnife.bind(this);

        setToolbarTitleStr(getTitleToolBar());
        setUpToolbar();
        setUpProgressBar();
        hideProgressBar(true);
    }


    @Override
    public Context getCtx() {
        return this;
    }

    protected abstract String getMainFragmentTag();

    protected abstract int getMainIdContainer();

    protected abstract Fragment getMainFragment();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MAIN_FRAGMENT_ID_KEY, getMainFragmentId());
        super.onSaveInstanceState(outState);
    }

    protected abstract int getMainFragmentId();


    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null)
            getPresenter().register();
        if (getPresenterList() != null)
            getPresenterList().register();
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null)
            getPresenter().unRegister();
        if (getPresenterList() != null)
            getPresenterList().unRegister();
        super.onDestroy();
    }

    protected String getTitleToolBar() {
        return "";
    }

    private void setUpProgressBar() {
        mProgressBar.setOnTouchListener((view, motionEvent) -> true);
    }

    @Override
    public void setToolbarSubTitle(String title) {
        setToolbarSubTitleStr(title);
    }

    private void setUpToolbar() {
        setUpLeftIconToolbar(getLeftCornerIcon());
        setUpLogoToolbar(getLogoRsc());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getRightMenu() == 0)
            return true;
        getMenuInflater().inflate(getRightMenu(), menu);
        currentMenu = menu;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.getItem(i);
            if (m.getIcon() != null) {
                m.getIcon().setColorFilter(getTintColor(), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }


    protected int getRightMenu() {
        return 0;
    }

    protected int getLogoRsc() {
        return 0;
    }

    protected int getTintColor() {
        return getResources().getColor(android.R.color.white);
    }


    protected void setUpLogoToolbar(int id) {
        if (mToolbar != null && logoToolbar != null) {
            if (id != 0) {
                logoToolbar.setImageDrawable(Utils.setIconColor(this, android.R.color.white, id));
                logoToolbar.setVisibility(View.VISIBLE);
            } else {
                logoToolbar.setVisibility(View.GONE);
            }
        }
    }

    public void setUpLeftIconToolbar(int id) {
        setUpLeftIconToolbar(id, 0);
    }

    public void setUpLeftIconToolbar(int id, int color) {
//                getSupportActionBar().setIcon(id);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isBackHomeButtonEnable());
            if (id != 0) {
                Drawable drawable = getResources().getDrawable(id);
                drawable.setColorFilter(color != 0 ? getResources().getColor(color) : getTintColor(), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(drawable);
            } else
                getSupportActionBar().setDisplayHomeAsUpEnabled(leftIconShouldBeShownWhenDrawableZero());
        }
    }

    protected boolean leftIconShouldBeShownWhenDrawableZero() {
        return true;
    }


    protected int getLeftCornerIcon() {
        return 0;
    }

    protected boolean isBackHomeButtonEnable() {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!mProgressBar.isShown())
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this); needs to define the tag parent at manifest
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void hideRightMenu() {
        if (currentMenu != null) {
            for (int i = 0; i < currentMenu.size(); i++) {
                MenuItem menuItem = currentMenu.getItem(i);
                menuItem.setTitle("");
                if (menuItem.getIcon() != null) {
                    menuItem.setIcon(null);
                }
                menuItem.setEnabled(false);
            }
        }
    }

    protected abstract void afterMainViewIsInflated(Bundle savedInstanceState);

    protected abstract View inflateMainView();

    protected Context getContext() {
        return this;
    }


    @Override
    public void showProgressBar(boolean b) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(boolean b) {
        mProgressBar.setVisibility(View.GONE);
    }

    public static void startClearStack(Context context, Class<?> activity) {
        Intent i = new Intent(context, activity);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }

    public static void start(Context context, Class<?> activity) {
        Intent i = new Intent(context, activity);
        context.startActivity(i);
    }

    public static void startWithFlags(Context context, Class<?> activity, int[] flag) {
        Intent i = new Intent(context, activity);
        for (int i1 : flag) {
            i.addFlags(i1);
        }
        context.startActivity(i);
    }

    public static void start(Context context, Class<?> activity, Bundle bundle) {
        Intent i = new Intent(context, activity);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    protected void setToolbarTitleStr(String id) {
        if (mToolbar != null && mToolbar.findViewById(R.id.tool_bar_title) != null) {
            titleToolBar = (TextViewCustom) mToolbar.findViewById(R.id.tool_bar_title);
            titleToolBar.setText(id);
            titleToolBar.setFont(R.string.Marvel);
        }
    }


    protected void setToolbarSubTitleStr(String id) {
        if (mToolbar != null) {
            mToolbar.setTitle(id);
            mToolbar.setSubtitle(id);
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        setToolbarTitleStr(title);
    }

    public void dismiss() {
        finish();
    }

    protected void setUpToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
        setUpToolbar();
    }


    @Override
    public void showToolBarTitleFade() {
        if (mToolbar != null && mToolbar.findViewById(R.id.tool_bar_title) != null) {
            titleToolBar = (TextViewCustom) mToolbar.findViewById(R.id.tool_bar_title);
            titleToolBar.animate().alpha(1).setDuration(100).start();
        }
    }

    @Override
    public void hideToolBarTitleFade() {
        if (mToolbar != null && mToolbar.findViewById(R.id.tool_bar_title) != null) {
            titleToolBar = (TextViewCustom) mToolbar.findViewById(R.id.tool_bar_title);
            titleToolBar.animate().alpha(0).setDuration(0).start();
        }
    }

    @Override
    public void hideToolBarTitle() {
        if (mToolbar != null && mToolbar.findViewById(R.id.tool_bar_title) != null) {
            titleToolBar = (TextViewCustom) mToolbar.findViewById(R.id.tool_bar_title);
            titleToolBar.animate().alpha(0).setDuration(0).start();
        }
    }


}
