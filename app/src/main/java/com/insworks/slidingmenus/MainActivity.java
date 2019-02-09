package com.insworks.slidingmenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.insworks.slidingmenus.five.FifthImplementionsActivity;
import com.insworks.slidingmenus.four.jeremyfeinstein.slidingmenu.lib.FourthImplementionsActivity;
import com.insworks.slidingmenus.one.FirstImplementionsActivity;
import com.insworks.slidingmenus.three.ThirdImplementionsActivity;
import com.insworks.slidingmenus.two.SecondImplementionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_first)
    Button btnFirst;
    @BindView(R.id.btn_second)
    Button btnSecond;
    @BindView(R.id.btn_third )
    Button btnThird;
    @BindView(R.id.btn_fourth )
    Button btnFourth;
    @BindView(R.id.btn_fifth )
    Button btnFifth;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_first, R.id.btn_second,R.id.btn_third,R.id.btn_fourth,R.id.btn_fifth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_first://自定义仿QQ侧滑 include的布局
                startActivity(new Intent(getApplicationContext(), FirstImplementionsActivity.class));
                break;
            case R.id.btn_second://使用谷歌自带DrawerLayout 包裹两个fragment
                startActivity(new Intent(getApplicationContext(), SecondImplementionsActivity.class));
                break;
            case R.id.btn_third://自定义普通侧滑菜单 include布局
                startActivity(new Intent(getApplicationContext(), ThirdImplementionsActivity.class));
                break;
            case R.id.btn_fourth://使用第三方开源库SlidingMenu
                startActivity(new Intent(getApplicationContext(), FourthImplementionsActivity.class));
                break;
            case R.id.btn_fifth://使用谷歌自带DrawerLayout 包裹NavigationView
                startActivity(new Intent(getApplicationContext(), FifthImplementionsActivity.class));
                break;
        }
    }
}
