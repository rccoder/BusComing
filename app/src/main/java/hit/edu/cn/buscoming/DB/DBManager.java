package hit.edu.cn.buscoming.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rccoder on 2016/7/11.
 */

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * 添加用户
     * @param user
     * @return boolean
     */
    public boolean addUser(User user) {

        if(!exitUser(user)) {
            Log.d("haha", "not exit user");
            ContentValues cv = new ContentValues();
            cv.put("email", user.email);
            cv.put("city","北京");
            cv.put("password", user.password);
            db.insert("user", null, cv);
            return true;
        } else {
            Log.d("haha", "exit user");
            return false;
        }
    }

    /**
     * 检查用户是否存在
     * @param user
     * @return boolean
     */
    public boolean exitUser(User user) {
        Cursor cursor = db.query("user", null, "email=?", new String[]{user.email}, null, null, null);
        while(cursor.moveToNext()) {
            Log.d("exiteUser2", user.email);
            return true;
        }
        Log.d("notExitUser2", user.email);
        return false;
    }

    /**
     * 用户登录
     * @param user
     * @return boolean
     */
    public boolean loginUser(User user) {
        Cursor cursor = db.query("user", null, "email=? AND password=?", new String[]{user.email, user.password}, null, null, null);
        while(cursor.moveToNext()) {
            Log.d("Login Success", user.email);
            return true;
        }
        Log.d("Login Error", user.email);
        return false;
    }
    /*
     * 查看当前城市
     */
    public String getCityState() {
        Cursor cursor = db.rawQuery("SELECT city from user", null);
        while(cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex("city"));
        }
        return cursor.getString(cursor.getColumnIndex("city"));
    }
    /*
     * 查看当前用户
     */
    public String getEmailState() {
        Cursor cursor = db.rawQuery("SELECT email from state", null);
        while(cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex("email"));
        }
        return cursor.getString(cursor.getColumnIndex("city"));
    }
    /*
     * 存储当前用户选择的城市
     * @param user[email, city]
     * @return void
     */
    public void updateCity(User user){
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("city",user.city);
            db.update("user",cv,"email=?",new String[]{user.email});
        }finally {
            db.endTransaction();
        }
    }


    /**
     * 更新状态
     * @param state
     */
    public void updateState(State state) {
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("_id", 0);
            db.update("state", cv, "email=? AND city=?", new String[]{state.email, state.city});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    /*
     * 保存最近查询记录
     * @param Recent recent(must have email and flag and one of threes)
     * @return boolean
     */
    public boolean saveRecent(Recent recent) {
        ContentValues cv = new ContentValues();
        cv.put("email", recent.email);
        cv.put("flag", recent.flag);

        if(recent.flag == 1) {
            cv.put("line_city", recent.line_city);
            cv.put("line_line", recent.line_line);
            db.insert("recent", null, cv);
            return true;
        } else if (recent.flag == 2) {
            cv.put("stop_city", recent.stop_city);
            cv.put("stop_stop", recent.stop_stop);
            db.insert("recent", null, cv);
            return true;
        } else if (recent.flag == 3) {
            cv.put("des_city", recent.des_city);
            cv.put("des_src", recent.des_src);
            cv.put("des_des", recent.des_des);
            db.insert("recent", null, cv);
            return true;
        }
        return false;
    }

    /*
     *  查看最近记录
     *  @param String email
     *  @param int flag
     *  @param int num
     *
     *  @return List<Recent> recentList
     */
    public List<Recent> getRecent(String email, int flag, int num) {
        Cursor cursor = db.query("recent", null, "email=? AND flag=?", new String[]{email, String.valueOf(flag)}, null, null, "_id desc", String.valueOf(num));
        List recentList = new ArrayList();
        while(cursor.moveToNext()) {
            Recent recent = new Recent();
            recent.setFlag(cursor.getInt(cursor.getColumnIndex("flag")));
            recent.setLine_city(cursor.getString(cursor.getColumnIndex("line_city")));
            recent.setLine_line(cursor.getString(cursor.getColumnIndex("line_line")));
            recent.setStop_city(cursor.getString(cursor.getColumnIndex("stop_city")));
            recent.setStop_stop(cursor.getString(cursor.getColumnIndex("stop_stop")));
            recent.setDes_city(cursor.getString(cursor.getColumnIndex("des_city")));
            recent.setDes_src(cursor.getString(cursor.getColumnIndex("des_src")));
            recent.setDes_des(cursor.getString(cursor.getColumnIndex("des_des")));

            recentList.add(recent);
        }
        return recentList;
    }

    /*
     * 保存最近收藏记录
     * @param Star star(must have email and flag and one of threes)
     * @return boolean
     */
    public boolean saveStar(Star star) {
        ContentValues cv = new ContentValues();
        cv.put("email", star.email);
        cv.put("flag", star.flag);

        if(star.flag == 1) {
            cv.put("line_city", star.line_city);
            cv.put("line_line", star.line_line);
            db.insert("star", null, cv);
            return true;
        } else if (star.flag == 2) {
            cv.put("stop_city", star.stop_city);
            cv.put("stop_stop", star.stop_stop);
            db.insert("star", null, cv);
            return true;
        } else if (star.flag == 3) {
            cv.put("des_city", star.des_city);
            cv.put("des_src", star.des_src);
            cv.put("des_des", star.des_des);
            db.insert("star", null, cv);
            return true;
        }
        return false;
    }


    /*
     *  查看最近收藏
     *  @param String email
     *  @param int flag
     *  @param int num
     *
     *  @return List<Star> starList
     */
    public List<Star> getStar(String email, int flag, int num) {
        Cursor cursor = db.query("star", null, "email=? AND flag=?", new String[]{email, String.valueOf(flag)}, null, null, "_id desc", String.valueOf(num));
        List starList = new ArrayList();
        while(cursor.moveToNext()) {
            Star star = new Star();
            star.setFlag(cursor.getInt(cursor.getColumnIndex("flag")));
            star.setLine_city(cursor.getString(cursor.getColumnIndex("line_city")));
            star.setLine_line(cursor.getString(cursor.getColumnIndex("line_line")));
            star.setStop_city(cursor.getString(cursor.getColumnIndex("stop_city")));
            star.setStop_stop(cursor.getString(cursor.getColumnIndex("stop_stop")));
            star.setDes_city(cursor.getString(cursor.getColumnIndex("des_city")));
            star.setDes_src(cursor.getString(cursor.getColumnIndex("des_src")));
            star.setDes_des(cursor.getString(cursor.getColumnIndex("des_des")));

            starList.add(star);
        }
        return starList;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
