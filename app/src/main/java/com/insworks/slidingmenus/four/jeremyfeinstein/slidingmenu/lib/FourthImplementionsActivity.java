package com.insworks.slidingmenus.four.jeremyfeinstein.slidingmenu.lib;

import android.os.Bundle;

import com.insworks.slidingmenus.R;
import com.insworks.slidingmenus.four.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class FourthImplementionsActivity extends SlidingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        setBehindContentView(R.layout.fragment_drawer_menu);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
//        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setFadeDegree(0.35f);
//        slidingMenu.setBehindWidthRes(R.dimen.behind_width);

    }
}