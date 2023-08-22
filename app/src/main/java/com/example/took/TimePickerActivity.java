package com.example.took;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_time;
    private TimePicker tp_time;
    private String userName = "XDF20032559";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        tv_time = findViewById(R.id.tv_time);
        tp_time = findViewById(R.id.tp_time);
        findViewById(R.id.btn_back).setOnClickListener(this);
//        设置title
        getSupportActionBar().setTitle("鸽鸽时间选择器");
//        设置为24小时制
        tp_time.is24HourView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
//                给当前实例设置事件监听
                String desc = String.format("当前时间是%d时%d分",tp_time.getHour(),tp_time.getMinute());
                tv_time.setText(desc);
                break;
            case R.id.btn_back:
                //        给返回按钮设置返回事件监听
                Intent intent = new Intent(this,HeadeActivity.class);
//        当跳转到当前实例时，结束上方实例
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user",userName);
                startActivity(intent);
                break;
        }


    }
}