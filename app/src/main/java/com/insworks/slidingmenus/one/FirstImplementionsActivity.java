package com.insworks.slidingmenus.one;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.insworks.slidingmenus.R;
import com.insworks.slidingmenus.one.SlideMenu.OnSwipeListener;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.Random;

public class FirstImplementionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_implementions);
		//1.findView
		SlideMenu slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		final ImageView iv_head = (ImageView) findViewById(R.id.iv_head);
		ListView main_listview = (ListView) findViewById(R.id.main_listview);
		final ListView menu_listview = (ListView) findViewById(R.id.menu_listview);
		final MyLinearLayout my_layout = (MyLinearLayout) findViewById(R.id.my_layout);
		
		//2.填充数据
		main_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Constant.NAMES));
		menu_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Constant.sCheeseStrings){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView = (TextView) super.getView(position, convertView, parent);
				textView.setTextColor(Color.WHITE);
				return textView; 
			}
		});
		
		//3.设置拖拽的监听器
		slideMenu.setOnSwipeListener(new OnSwipeListener() {
			@Override
			public void onOpen() {
//				Log.e("tag", "onOpen");
				int position = new Random().nextInt(Constant.sCheeseStrings.length);
				menu_listview.smoothScrollToPosition(position);
			}
			@Override
			public void onDraging(float fraction) {
//				Log.e("tag", "onDraging  fraction:"+fraction); 
				ViewCompat.setAlpha(iv_head, 1-fraction);
			}
			@Override
			public void onClose() {
//				Log.e("tag", "onClose");
				ViewPropertyAnimator.animate(iv_head).translationX(30)
									.setInterpolator(new CycleInterpolator(4))
				                    .setDuration(1000)
				                    .start();
			}
		});
		
		//4,给MyLinearLayout设置SlideMenu
		my_layout.setSlideMenu(slideMenu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
