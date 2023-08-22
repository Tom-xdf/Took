package com.example.took;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.took.util.ViewUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login;
    private EditText et_password;
    private Button btn_ok;
    private String userName = "xudongfeng20032559";
    private String userPassword = "032559";
    private Button btn_jump;
    private ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("登录页面");
        et_login = findViewById(R.id.et_login);
        et_password = findViewById(R.id.et_password);
        btn_ok = findViewById(R.id.btn_ok);
//        设置文本监听
        btn_ok.setOnClickListener(this);
//        点击跳转到注册页面
        btn_jump = findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(this);
//        创建bundle意图
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//                回传信息是经过这里
        });
//        隐藏输入法键盘的方法：
//        当输入框达到指定的位数时，自动隐藏键盘
        et_login.addTextChangedListener(new HideTextWatcher(et_login,18));
        et_password.addTextChangedListener(new HideTextWatcher(et_password,6));
    }

    @Override
    public void onClick(View v) {
        String desc = et_login.getText().toString();
        String desc1 = et_password.getText().toString();

        switch (v.getId()){
            case R.id.btn_ok:
                if (desc.equals(userName)){
                    if (desc1.equals(userPassword)){
                        Toast.makeText(MainActivity.this, "正在登录！！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, HeadeActivity.class);
//                密码以及账号都正确的情况下才会登录成功
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        创建包裹
//                        Bundle bundle = new Bundle();
//                        bundle.putString("user",userName);
//                        intent.putExtras(bundle);
//                        register.launch(intent);
//                        finish();
//                        利用putExtra把用户名传输过去
                        intent.putExtra("user",userName);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "密码不正确！！", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "账户密码不正确 ，登录不成功", Toast.LENGTH_LONG).show();
                }
                break;
//                跳转到登录注册页面，
            case R.id.btn_jump:
                Intent intent = new Intent(this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }

    }
//    隐藏输入法键盘的方法，
    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        public HideTextWatcher(EditText v, int maxLength) {
            this.mView=v;
            this.mMaxLength=maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == mMaxLength){
//                隐藏输入法键盘
                ViewUtil.hideOneInputMethod(MainActivity.this, mView);
            }
        }
    }
}