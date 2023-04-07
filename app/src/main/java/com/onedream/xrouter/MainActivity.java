package com.onedream.xrouter;

import android.view.View;

import com.onedream.xrouter.base.BaseActivity;
import com.onedream.xrouter.export.two.TwoRouterUtils;


public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwoRouterUtils.navigationTwoPage("vam_jdallen_WeDoX");
            }
        });

    }

}
