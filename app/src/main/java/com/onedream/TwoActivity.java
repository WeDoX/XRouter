package com.onedream;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.onedream.xrouter_annotation.Route;
import com.onedream.xrouter.R;

@Route("/me/two")
public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        //
        String name ="";
        if(null != getIntent()){
            name = getIntent().getStringExtra("name");
        }
        //
        ((TextView)findViewById(R.id.tv_back)).setText("点击我关闭当前界面:"+name);
        //
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
