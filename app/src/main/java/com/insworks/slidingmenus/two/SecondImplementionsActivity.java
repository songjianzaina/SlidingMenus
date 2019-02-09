package com.insworks.slidingmenus.two;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.insworks.slidingmenus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondImplementionsActivity extends Activity {
    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_implement);
        bind = ButterKnife.bind(this);



    }
    @OnClick(R.id.titlebar_left_btn)
    protected void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
