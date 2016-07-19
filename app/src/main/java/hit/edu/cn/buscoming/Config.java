package hit.edu.cn.buscoming;

import android.app.Application;

/**
 * Created by Kimi on 2016/7/15.
 */

public class Config extends Application {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public  void onCreate(){
        key = "2524dba6367ba5aa7d7bed6b3caa424a";
        super.onCreate();
    }
}
