package com.wzg.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wzg.R;
import com.wzg.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean mIsFirstStartUpBoolean;
    private EditText mUserNameEdit;
    private EditText mPassWordEdit;
    private CheckBox mRemPwdCheck;
    private CheckBox mAutoLoginCheck;
    private Button mLoginBtn;
    private Button mRegisterBtn;
    private String mUserNameStr;
    private String mPassWordStr;
    private boolean mLoginSuccess;
    private boolean mAutoLoginBoolean;
    private boolean mRemPwdBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        isFirstStartUp();

        initControl();


    }

    /**
     * 创建存储对象,使用SharedPreferences进行存储数据
     */
    private void createStorage() {

        // 创建SharedPreferences对象
        mSharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

        // 创建SharedPreferences.Editor对象
        mEditor = mSharedPreferences.edit();
    }


    /**
     * 判断是否是第一次启动
     *
     * @return true：第一次启动，false：不是第一次启动
     */
    private void isFirstStartUp() {

        createStorage();

        mIsFirstStartUpBoolean = mSharedPreferences.getBoolean("isFirstStartUpBoolean", true);

        if (mIsFirstStartUpBoolean == true) {
            mEditor.putBoolean("isFirstStartUpBoolean", false);
            mEditor.apply();
            // 跳转到第一次启动页面
            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
        }

    }


    /**
     * 初始化所有控件
     */
    private void initControl() {

        mUserNameEdit = findViewById(R.id.un_txt);
        mPassWordEdit = findViewById(R.id.pw_txt);

        mRemPwdCheck = findViewById(R.id.remPwd_check);
        mAutoLoginCheck = findViewById(R.id.autoLogin_check);


        mLoginBtn = findViewById(R.id.login_btn);
        mRegisterBtn = findViewById(R.id.register_btn);

        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);

        mAutoLoginBoolean = mSharedPreferences.getBoolean("autoLoginBoolean", false);
        mRemPwdBoolean = mSharedPreferences.getBoolean("remPwdBoolean", false);
        String userName = mSharedPreferences.getString("UserName", "");
        String passWord = mSharedPreferences.getString("PassWord", "");

        if(mRemPwdBoolean){
            mUserNameEdit.setText(userName);
            mPassWordEdit.setText(passWord);
            mRemPwdCheck.setChecked(mRemPwdBoolean);
            mAutoLoginCheck.setChecked(mAutoLoginBoolean);
        }else{
            mUserNameEdit.setText("");
            mPassWordEdit.setText("");
        }

        if(mAutoLoginBoolean){

            // 跳转到主页面
            if("admin".equals(userName)){
                // 跳转到管理员页面

            }else{
                // 跳转到用户页面
                Toast.makeText(this, "用户界面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                finish();
            }

        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_btn:
                loginIdea();
                break;
            case R.id.register_btn:
                break;

        }
    }

    /**
     *
     * 登录
     *
     */
    private void loginIdea() {
        boolean loginSuccess = isLoginSuccess();
        if(loginSuccess){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MenuActivity.class));
            finish();
        }
    }


    /**
     * 判断登录是否成功
     *
     * @return true：成功   false：失败
     */
    private boolean isLoginSuccess(){
        mUserNameStr = mUserNameEdit.getText().toString().trim();
        mPassWordStr = mPassWordEdit.getText().toString().trim();
        String[] userNames = User.UserNames;
        for (int i = 0; i < userNames.length; i++) {
            Log.d(TAG, mUserNameStr + mPassWordStr);
            if (userNames[i].equals(mUserNameStr) && "123456".equals(mPassWordStr)) {
                mEditor.putBoolean("remPwdBoolean",mRemPwdCheck.isChecked());
                mEditor.putBoolean("autoLoginBoolean",mAutoLoginCheck.isChecked());
                mEditor.putString("UserName", mUserNameStr);
                mEditor.putString("PassWord", mPassWordStr);
                mEditor.apply();
                return true;
            } else if (mUserNameStr.equals("admin") && mPassWordStr.equals("admin")) {
                mEditor.putBoolean("remPwdBoolean",mRemPwdCheck.isChecked());
                mEditor.putBoolean("autoLoginBoolean",mAutoLoginCheck.isChecked());
                mEditor.putString("UserName", mUserNameStr);
                mEditor.putString("PassWord", mPassWordStr);
                mEditor.apply();
                return true;
            } else {
                mEditor.putBoolean("remPwdBoolean",false);
                mEditor.putBoolean("autoLoginBoolean",false);
                mEditor.putString("UserName", "");
                mEditor.putString("PassWord", "");
                mEditor.apply();
                Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();

                return false;
            }
        }
        return false;
    }



}
