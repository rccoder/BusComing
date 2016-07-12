package hit.edu.cn.buscoming.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        db.beginTransaction();  //开始事务
        try {
            if(!exitUser(user)) {
                ContentValues cv = new ContentValues();
                cv.put("email", user.email);
                cv.put("password", user.password);
                db.insert("user", null, cv);
            }
            db.setTransactionSuccessful();  //设置事务成功完成
            return true;
        } finally {
            db.endTransaction();    //结束事务
            return false;
        }
    }
    /**
     * 检查用户是否存在
     * @param user
     */
    public boolean exitUser(User user) {
        db.beginTransaction();
        try {
            Cursor cursor = db.query("user", null, "email=?", new String[]{user.email}, null, null, null);
            while(cursor.moveToNext()) {
                db.setTransactionSuccessful();
                Log.d("exitUser", user.email);
                return true;
            }
            db.setTransactionSuccessful();
            return false;
        } finally {
            db.endTransaction();
        }
    }
    /*
     * 查看当前城市
     */
    public String getCityState() {
        Cursor cursor = db.rawQuery("SELECT city from state", null);
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
    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
