package com.insworks.slidingmenus.three;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.insworks.slidingmenus.R;

public class ThirdImplementionsActivity extends Activity implements OnClickListener {

	private ImageView mIvBack;
	private SlideMenuView mIv_home_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third_implementions);

		initView();
		initData();
	}

	/**
	 *
	 */
	private void initView() {
		mIvBack = (ImageView) findViewById(R.id.iv_head);
		mIv_home_back = (SlideMenuView) findViewById(R.id.smv_slide);
		mIvBack.setOnClickListener(this);
	}

	private void initData() {

	}

	@Override
	public void onClick(View v) {
		mIv_home_back.switchMenu();
	}

}
