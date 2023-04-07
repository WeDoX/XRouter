package com.onedream.xrouter.module.two;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onedream.xrouter.R;
import com.onedream.xrouter.base.BaseActivity;
import com.onedream.xrouter.export.two.TwoRouterTable;
import com.onedream.xrouter_annotation.Route;

@Route(TwoRouterTable.PAGE_TWO)
public class TwoActivity extends BaseActivity {

    private TextView tv_back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_two;
    }

    @Override
    public void initView() {
        tv_back = findViewById(R.id.tv_back);
    }

    @Override
    public void initData() {
        String name = "";
        Intent intent = getIntent();
        if (null != intent) {
            name = intent.getStringExtra(TwoRouterTable.KEY_TWO_NAME);
        }
        //
        tv_back.setText("上个界面传过来的数据:\n\n\n" + name);
    }


    @Override
    public void initEvent() {

    }
}
