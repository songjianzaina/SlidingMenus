package com.insworks.slidingmenus.three;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideMenuView extends FrameLayout {

	private View menuView;
	private View mMainView;
	private float mDownX;
	private Scroller mScroller;
	private float mDownY;

	public SlideMenuView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SlideMenuView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public SlideMenuView(Context context) {
		this(context, null);
	}

	private void init() {
		mScroller = new Scroller(getContext());
	}

	/**
	 * ���ÿؼ�����
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		mMainView = getChildAt(0);
		menuView = getChildAt(1);
		mMainView.layout(0, 0, mMainView.getMeasuredWidth(),
				mMainView.getMeasuredHeight());
		menuView.layout(-menuView.getMeasuredWidth(), 0, 0,
				menuView.getMeasuredHeight());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = event.getX();
			mDownY = event.getY();
			Log.e("haha", "onTouchEvent");
			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = event.getX();
			int deltaX = (int) ((moveX - mDownX) + 0.5f);
			Log.e("tag", getScrollX() + "");
			int newScrollX = getScrollX() - deltaX;
			if (newScrollX > 0) {
				newScrollX = 0;// ���Ʋ��ܳ����ұ�
			}
			if (newScrollX < -menuView.getMeasuredWidth()) {
				newScrollX = -menuView.getMeasuredWidth();
			}
			scrollTo(newScrollX, 0);
			mDownX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			if (getScrollX() > -menuView.getMeasuredWidth() / 2) {
				// �رղ˵�
				closeMenu();
			} else {
				// �����˵�
				openMenu();
			}
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * �����˵�
	 */
	private void openMenu() {
		mScroller.startScroll(getScrollX(), 0,
				-(menuView.getMeasuredWidth() + getScrollX()), 0, 350);
		invalidate();
	}

	/**
	 * �رղ˵�
	 */
	private void closeMenu() {
		mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0, 350);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int currX = mScroller.getCurrX();
			int currY = mScroller.getCurrY();
			scrollTo(currX, currY);
			// ��������invalide
			invalidate();
		}
	}

	public void switchMenu() {
		if (getScrollX() == 0) {
			// �����˵�
			openMenu();
		} else {
			// �رղ˵�
			closeMenu();
		}
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("haha", "onInterceptTouchEvent");
			mDownX = ev.getX();
			mDownY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = ev.getX();
			float moveY = ev.getY();
			if(Math.abs(moveX-mDownX)>Math.abs(moveY-mDownY)){
				//��ʾˮƽ����  ���ش����¼�
				return true; 
			}
			break;
		case MotionEvent.ACTION_UP:  
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
}
