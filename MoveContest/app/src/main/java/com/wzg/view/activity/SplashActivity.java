package com.wzg.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.wzg.R;

public class SplashActivity extends AppCompatActivity {

    private final int PLAH_DISPLAY = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // 3秒钟跳转到主页面
        jumpMainPage();


    }


    /**
     * 3秒钟之后跳转到主页面
     */
    private void jumpMainPage() {

        /**
         * postDelayed方法：这是一种创建多线程消息的函数
         *
         *  1. 首先创建一个Handler对象
         *  2. 然后创建一个Runnable对象
         *  3. 使用postDelayed方法，两秒调用此Runnable
         *  4. 如果想关闭此定时器，可以这样操作
         *      hander.removeCallbacks(runnable);
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //   要执行的代码
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        }, PLAH_DISPLAY);

    }


    /**
     *
     * 在SplashActivity禁止使用返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
