package com.insworks.slidingmenus.two;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * Created by songjian on 2016/10/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {

    protected String TAG = getClass().getSimpleName();

    protected Activity mContext;
    protected boolean isDeleted=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = getContentView(inflater, container);
        ButterKnife.bind(this, view);
        return view;
    }

    public abstract View getContentView(LayoutInflater inflater, ViewGroup container);



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.setOnTouchListener(this);
    }

    //隐藏软键盘
    protected void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (mContext.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mContext.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(mContext.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hideSoftInputView();
        return false;
    }




}
