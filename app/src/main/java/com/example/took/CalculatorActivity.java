package com.example.took;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_result;
    //    第一个操作数
    private  String firstNum="";
    //    运算符
    private  String operator="";
    //    第二个操作数
    private  String secondNum="";
    //    当前计算结果
    private String result="";
    //    显示结果
    private String showText = "";
    //        用户固定实时返回用户登录信息
    private String userName = "XDF20032559";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_add).setOnClickListener(this);//加法按钮
        findViewById(R.id.btn_cancel).setOnClickListener(this);//回退
        findViewById(R.id.btn_remove).setOnClickListener(this);//除法
        findViewById(R.id.btn_ride).setOnClickListener(this);//乘
        findViewById(R.id.btn_cs).setOnClickListener(this);//清空
        findViewById(R.id.btn_seven).setOnClickListener(this);//7
        findViewById(R.id.btn_aity).setOnClickListener(this);//8
        findViewById(R.id.btn_line).setOnClickListener(this);//9
        findViewById(R.id.btn_subtract).setOnClickListener(this);//减
        findViewById(R.id.btn_six).setOnClickListener(this);//6
        findViewById(R.id.btn_five).setOnClickListener(this);//5
        findViewById(R.id.btn_four).setOnClickListener(this);//4
        findViewById(R.id.btn_three).setOnClickListener(this);//3
        findViewById(R.id.btn_two).setOnClickListener(this);//2
        findViewById(R.id.btn_one).setOnClickListener(this);//1
        findViewById(R.id.btn_passive).setOnClickListener(this);//1/x
        findViewById(R.id.btn_zero).setOnClickListener(this);//0
        findViewById(R.id.btn_lot).setOnClickListener(this);//lot
        findViewById(R.id.btn_be).setOnClickListener(this);//==
        findViewById(R.id.ib_sqrt).setOnClickListener(this);//根号
//        返回到主页的按钮监听视图
        findViewById(R.id.btn_back).setOnClickListener(this);
//        设置title
        getSupportActionBar().setTitle("鸽鸽专用计算器");

    }

    @Override
    public void onClick(View v) {
        String inputText;
        if (v.getId()==R.id.ib_sqrt){
            inputText="✓";
        }else {
            inputText = ((TextView)v).getText().toString();
        }
        switch (v.getId()){
//            清空按钮
            case R.id.btn_cs:
                clear();
                break;
//          回退按钮
            case R.id.btn_cancel:
                break;
//          加减乘除按钮
            case R.id.btn_remove:
            case R.id.btn_ride:
            case R.id.btn_subtract:
            case R.id.btn_add:
                operator=inputText;
                refreshText(showText+operator);
                break;
//                等号按钮
            case R.id.btn_be:
//                加减乘除运算结果
                double calculate_result=calculateFour();
                refreshOperate(String.valueOf(calculate_result));
                refreshText(showText+"="+result);
                break;
//                根号按钮
            case R.id.ib_sqrt:
                double sqrt_result=Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrt_result));
                refreshText(showText+"✓="+result);
                break;
//                求倒数按钮
            case R.id.btn_passive:
                double passive_result=1.0/Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(passive_result));
                refreshText(showText+"/="+result);
                break;
//          包括数字小数点
            default:
                if (result.length()>0&&operator.equals("")){
                    clear();
                }
                if (operator.equals("")){
                    firstNum=firstNum+inputText;
                }else {
                    secondNum = secondNum+inputText;
                }
                if (showText.equals("0")&& !inputText.equals(".")){
                    refreshText(inputText);
                }else {
                    refreshText(showText+inputText);
                }
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
    //加减乘除运算结果返回的方法
    private double calculateFour() {
        switch (operator){
            case "+":
                return Double.parseDouble(firstNum)+Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum)-Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum)*Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum)/Double.parseDouble(secondNum);
        }
    }
    //清空并初始化
    private void clear() {
        refreshText("");
        refreshOperate("");
    }
    //    运算完接着计算的结果方法
    private void refreshOperate(String new_result){
        result = new_result;
        firstNum = result;
        operator = "";
        secondNum = "";

    }
    //保存运算结果方法
    private  void refreshText(String text){
        showText = text;
        tv_result.setText(showText);
    }

}