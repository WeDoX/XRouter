package com.onedream.xrouter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onedream.xrouter_annotation.Route;
import com.onedream.xrouter_api.XRouter;

@Route("/me/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnSend(view);
            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnGet(view);
            }
        });

    }

    private void showToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    public void onClickBtnSend(View view){
        showToast("click btn_send 我是计算机和"+view.getId());
    }

    public void onClickBtnGet(View view){
        showToast("我是第二个函数是"+view.getId());
        //
        XRouter.build("/me/two").withString("name","我是陈贵坚").navigation();
        //TwoActivity.actionStart(MainActivity.this);
    }


}
