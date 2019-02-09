package com.insworks.slidingmenus.one;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.insworks.slidingmenus.one.SlideMenu.DragState;

public class MyLinearLayout extends LinearLayout {
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MyLinearLayout(Context context) {
		super(context);
	}
	private SlideMenu slideMenu;
	public void setSlideMenu(SlideMenu slideMenu){
		this.slideMenu = slideMenu;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//如果当前的slideMenu是处于打开的状态，那么就拦截，并且消费掉
		if(slideMenu!=null && slideMenu.getDragState()== DragState.Close){
			//如果按下就直接让SlideMenu关闭
			if(ev.getAction()==MotionEvent.ACTION_DOWN){
				slideMenu.close();
			}
			return true;//表示拦截
		}
		return super.onInterceptTouchEvent(ev);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//如果当前的slideMenu处于打开的状态，则消费掉
		if(slideMenu!=null && slideMenu.getDragState()==DragState.Open){
			
			return true;//表示消费掉
		}
		return super.onTouchEvent(event);
	}
	
}
