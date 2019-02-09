package com.insworks.slidingmenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.insworks.slidingmenus.one.FirstImplementionsActivity;
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
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_first, R.id.btn_second,R.id.btn_third,R.id.btn_fourth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_first://自定义slidingMenu仿QQ侧滑
                startActivity(new Intent(getApplicationContext(), FirstImplementionsActivity.class));
                break;
            case R.id.btn_second://使用谷歌自带DrawerLayout 包裹两个fragment
                startActivity(new Intent(getApplicationContext(), SecondImplementionsActivity.class));
                break;
 /*           case R.id.btn_third://用RadioButton实现底部导航栏
                startActivity(new Intent(getApplicationContext(), ThirdImplementionsActivity.class));
                break;
            case R.id.btn_fourth://adaptablebottomnavigation
                startActivity(new Intent(getApplicationContext(), AdaptableActivity.class));
                break;*/
        }
    }
}
