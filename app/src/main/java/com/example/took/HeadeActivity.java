package com.example.took;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HeadeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private View ib_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heade);
        tv_result = findViewById(R.id.tv_result);
        ib_calculator = findViewById(R.id.ib_calculator);
        ib_calculator.setOnClickListener(this);
//        给title设置文本
        getSupportActionBar().setTitle("鸽鸽工具页面");
//        给跳转时间选择器的按钮设置时间监听
        findViewById(R.id.ib_time).setOnClickListener(this);
//        接收登录用户的账户信息
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String desc = "欢迎非常非常尊敬且尊贵的用户：";
//        String desc = String.format("欢迎非常非常尊敬且尊贵的用户：", user);
        tv_result.setText(desc+user);
//        设置走马灯内容由主程序控制调用
        tv_result.setSelected(true);
//        从上一个页面传过来的意图中获取包裹
//        Bundle bundle = getIntent().getExtras();
//        String user = bundle.getString("user");
//        String desc = String.format("欢迎尊敬的用户：\n",user);
//        tv_result.setText(desc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_calculator:
                //        接收登录计算机返回过来的实例
                Intent intent = new Intent(this,CalculatorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.ib_time:

                Intent intent1 = new Intent(this,TimePickerActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
        }


    }
}