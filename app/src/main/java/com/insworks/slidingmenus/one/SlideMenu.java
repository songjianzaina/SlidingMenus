package com.insworks.slidingmenus.one;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.FloatEvaluator;

public class SlideMenu extends FrameLayout{
	private ViewDragHelper viewDragHelper;
	private View menuView;
	private View mainView;
	private int menuWidth,menuHeight;//菜单的宽高
	private int mainWidth,mainHeight;//主界面的宽高
	float dragRange;//拖拽范围
	
	private FloatEvaluator floatEvaluator;
	
	/**
	 * 定义枚举状态常量
	 * @author Administrator
	 *
	 */
	public enum DragState{
		Open,Close
	}
	private DragState mState = DragState.Close;//默认是关闭的状态
	
	public SlideMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlideMenu(Context context) {
		super(context);
		init();
	}
	public DragState getDragState() {
		return mState;
	}
	/**
	 * 初始化的方法
	 */
	private void init(){
		floatEvaluator = new FloatEvaluator();
		viewDragHelper = ViewDragHelper.create(this, callback);
	}
	
	/**
	 * 当布局被填充完执行，这个方法执行的时候就能够知道自己有几个子View了
	 * 就是当前view的结束标签解析完就表示填充完，一般在该方法中可以获取
	 * 自己的子View，但是当前方法执行的时候子View并没有完成测量，所以此时
	 * 宽高都是0
	 */
	@Override
	protected void onFinishInflate() {
		menuView = getChildAt(0);
		mainView = getChildAt(1);
	}
	/**
	 * 在执行完onMeasure之后调用，所以在该方法中可以获取子View的宽高了
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		menuWidth = menuView.getMeasuredWidth();
		menuHeight = menuView.getMeasuredHeight();
		mainWidth = mainView.getMeasuredWidth();
		mainHeight = mainView.getMeasuredHeight();
		
		//设定拖拽范围
		dragRange = mainWidth*0.6f;
	}
	
	
	
	/**
	 * 控制自己以及自己的子View的宽高
	 */
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		//测量子View
//		View menuView = getChildAt(0);
//		View mainView = getChildAt(1);
//		measureChild(menuView, widthMeasureSpec, heightMeasureSpec);
//		measureChild(mainView, widthMeasureSpec, heightMeasureSpec);
//	}

//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		//摆放子View
//		View menuView = getChildAt(0);
//		View mainView = getChildAt(1);
//		menuView.layout(-menuView.getMeasuredWidth(),0,0,menuView.getMeasuredHeight());
//		mainView.layout(0, 0, mainView.getMeasuredWidth(), mainView.getMeasuredHeight());
//	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//让ViewDragHelper帮助我们判断是否应该拦截
		boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
		return result;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//将触摸事件传递给ViewDragHelper来解析
		viewDragHelper.processTouchEvent(event);
		return true;
	}
	
	private Callback callback = new Callback() {
		
		/**
		 * 判断是否需要捕获child的触摸事件
		 * child ： 表示当前手指触摸的子View
		 * return: true:表示需要捕获      false：表示忽略不处理
		 */
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return child==mainView || child==menuView;
		}
		
		/**
		 * 当View被开始捕获触摸事件的回调，一般没有太大的用处
		 */
		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			super.onViewCaptured(capturedChild, activePointerId);
		}

		/**
		 * 获取View在水平方向拖拽的范围，但是目前并不能限制子VIew移动，目前会用在平滑移动
		 * 的动画时间计算上面，所以该方法最好不能返回0，按照正常返回
		 */
		@Override
		public int getViewHorizontalDragRange(View child) {
			return (int) dragRange;
		}
		
		/**
		 * 控制子View在水平方向的移动
		 * child: 当前触摸的子View
		 * left: 表示我们手指滑动之后ViewDragHelper认为我们向变成的left,left=child.getLeft()+dx
		 * dx: 表示手指在水平方向移动的距离
		 * return : 表示我们真正想让View的left变成的值
		 */
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			if(child==mainView){
				//对mainView的移动进行限制了
				if(left>dragRange){
					left = (int) dragRange;
				}
				if(left<0){
					left = 0;
				}
			}
//			else if (child==menuView) {
//				left = 0;
//			}
			return left;
		}
		/**
		 * 控制子View在垂直方向的移动
		 * child: 当前触摸的子View
		 * top: 表示我们手指滑动之后ViewDragHelper认为我们想要变成的top,top=child.getTop()+dy
		 * dy: 表示手指在垂直方向移动的距离
		 * return : 表示我们真正想让View的top变成的值
		 */
		public int clampViewPositionVertical(View child, int top, int dy) {
			return 0;
		}
		
		/**
		 * 子View移动之后的回调，在该方法中可以获取子VIew移动之后最新的left和top,该方法一般用来做
		 * 伴随移动的
		 * left: 表示changedView最新的left
		 * top: 表示changedView最新的top
		 * dx: 表示changedView水平移动的距离
		 * dy: 表示changedView垂直移动的距离
		 */
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			super.onViewPositionChanged(changedView, left, top, dx, dy);
//			Log.e("tag", "left: "+left  +"  dx:"+dx);
			
			//如果当前移动的是menuView，则让mainView跟随移动
			if(changedView==menuView){
				//固定住menuView，不让它动
				menuView.layout(0, 0,menuWidth, menuHeight);
				
				//手动让mainView进行伴随移动
				int newLeft = mainView.getLeft()+dx;
				//对newLeft进行限制
				if(newLeft>dragRange){
					//限制右边
					newLeft = (int) dragRange;
				}
				if(newLeft<0){
					newLeft = 0;//限制左边
				}
				mainView.layout(newLeft, mainView.getTop(),newLeft+mainWidth, 
						mainView.getBottom());
			}
			
			//1.计算mainView滑动的百分比
			float fraction = mainView.getLeft()/dragRange;
			//2.根据滑动的百分比的值，去执行伴随的动画
			executeAnim(fraction);
			
			//3.回调监听器的方法
			if(fraction==0f && mState!=DragState.Close){
				mState = DragState.Close;
				//说明关闭了
				if(listener!=null){
					listener.onClose();
				}
			}else if (fraction==1f && mState!=DragState.Open) {
				mState = DragState.Open;
				//说明打开了
				if(listener!=null){
					listener.onOpen();
				}
			}
			//设定拖拽中的事件可以让外界一直收到
			if(listener!=null){
				listener.onDraging(fraction);
			}
			
		}
		/**
		 * 手指抬起的时候会执行
		 * xvel:x方向的移动的速度
		 * yvel：y方向的移动的速度
		 */
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			super.onViewReleased(releasedChild, xvel, yvel);
			if(mainView.getLeft()>dragRange/2){
				//说明应该打开
				open();
			}else {
				//应该关闭
				close();
			}
			
			if(xvel>200){
				//向右滑动
				open();
			}else if (xvel<-100) {
				//向左关闭
				close();
			}
			
//			Log.e("tag", "xvel:"+xvel);
		}
	};
	/**
	 * 执行伴随动画
	 * @param fraction
	 */
	protected void executeAnim(float fraction) {
		//fraction: 0 - 1
		//1.让mainView进行缩放
		//value: 10  ->   110 
		//value = startValue + (endValue-startValue)*fraction;
		//scale:1(startValue) - 0.8(endValue)
//		float scale = (float) (1 + (0.8-1)*fraction);
		float scale = floatEvaluator.evaluate(fraction, 1f, 0.8f);
		ViewCompat.setScaleX(mainView, scale);
		ViewCompat.setScaleY(mainView, scale);
		
		//其他的旋转效果
//		float rotationX = 0 + (180-0)*fraction;
//		ViewCompat.setRotation(mainView, rotationX);
//		ViewCompat.setRotationX(mainView, rotationX);
//		ViewCompat.setRotationY(mainView, rotationX);
//		ViewCompat.setTranslationY(mainView, rotationX);
		
		//2.让menuView进行缩放，和平移,透明度的变化
//		float scale2 = 0.3f + (1-0.3f)*fraction;
		float scale2 = floatEvaluator.evaluate(fraction, 0.3f, 1f);
		ViewCompat.setScaleX(menuView, scale2);
		ViewCompat.setScaleY(menuView, scale2);
//		float translationX =  -menuWidth/2 + (0+menuWidth/2)*fraction;
		float translationX =  floatEvaluator.evaluate(fraction, -menuWidth/2, 0);
		ViewCompat.setTranslationX(menuView,translationX);
//		float alpha = 0.3f + (1f-0.3f)*fraction;
		float alpha = floatEvaluator.evaluate(fraction, 0.3f, 1f);
		ViewCompat.setAlpha(menuView, alpha);
		
		//3.给SlideMenu的背景添加颜色遮罩
		if(getBackground()!=null){
			int color = (Integer) ColorUtil.evaluateColor(fraction,Color.BLACK,Color.TRANSPARENT);
			getBackground().setColorFilter(color,Mode.SRC_OVER);
		}
	}
	/**
	 * 关闭的方法
	 */
	public void close() {
		viewDragHelper.smoothSlideViewTo(mainView,0, mainView.getTop());
		//刷新整个View
		ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
	}

	/**
	 * 打开的方法
	 */
	public void open() {
		viewDragHelper.smoothSlideViewTo(mainView,(int) dragRange,mainView.getTop());
		//刷新整个View
		ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
	}
	@Override
	public void computeScroll() {
		//用Scroller的写法
//		if(Scroller.computeOffset()){
//			scrollTo(Scroller.getCurrX(),0);
//			invalidate();
//		}
		
		//判断动画有没有结束
		if(viewDragHelper.continueSettling(true)){
			//刷新整个给View
			ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
		}
		
	}

	
	private OnSwipeListener listener;
	public void setOnSwipeListener(OnSwipeListener listener){
		this.listener = listener;
	}
	public interface OnSwipeListener{
		/**
		 * 打开的回调
		 */
		void onOpen();
		/**
		 * 关闭的回调
		 */
		void onClose();
		/**
		 * 拖拽过程中的回调
		 */
		void onDraging(float fraction);
	}
	
	
}
