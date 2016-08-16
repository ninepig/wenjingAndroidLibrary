package uml.yangwenjing.wenjingandroidlibrary.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

/*
 这是android的导入界面，一般而言向导页面都是用来展示logo，初始化数据（copy数据到sd卡），提高用户体验，下载数据等
 这个类提供一个功能，就是当splashAct时，用户点击界面，可以直接进入主页的功能（同时移除延迟进入主页的功能）
 */


public class SplashActvity extends AppCompatActivity {


    private Handler mhandler;

    //用于移除事件
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mhandler = new Handler();

        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActvity.this, QQlistViewDemoAct.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        if(ev.getAction()==MotionEvent.ACTION_UP)
        {
            Intent intent = new Intent(SplashActvity.this, QQlistViewDemoAct.class);
            startActivity(intent);
            finish();

            /*
             添加这一行代码，可以让原先的runnable 事件被移除，也就是之前的3秒移除取消
             */
            if (runnable != null)
                mhandler.removeCallbacks(runnable);
        }

        return super.onTouchEvent(ev);
    }

}
