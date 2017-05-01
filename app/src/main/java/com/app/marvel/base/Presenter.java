package com.app.marvel.base;

import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.app.marvel.views.Controller;


/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class Presenter {

    private final Controller controller;
    protected boolean canOperateOnView;


    public Presenter(Controller controller) {
        this.controller = controller;
        canOperateOnView = true;
    }


    public void unRegister() {
        canOperateOnView = false;
    }

    public void register() {
        canOperateOnView = true;
    }


    public static void showKeyBoard(EditText editText) {
        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }, 500);

    }

    public static void hideKeyBoard(EditText editText) {
        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }, 500);
    }
}
