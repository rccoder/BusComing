package hit.edu.cn.buscoming.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import hit.edu.cn.buscoming.Collector.ActivityCollector;

/**
 * Created by rccoder on 2016/7/8.
 */

/*
 *  继承 AppCompatActicity
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        // 加入 Activity 活动管理器
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 消除 Activity 活动管理器
        ActivityCollector.removeActivity(this);
    }
}
