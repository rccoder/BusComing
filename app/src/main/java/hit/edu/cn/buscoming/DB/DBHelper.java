package hit.edu.cn.buscoming.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rccoder on 2016/7/11.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户表，存储登录用户
        db.execSQL("CREATE TABLE IF NOT EXISTS user " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR, password VARCHAR)");

        // 创建状态表，存储当前用户和当前选中的城市，并且插入初始化信息
        db.execSQL("CREATE TABLE IF NOT EXISTS state " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR, city VARCHAR)");
        db.execSQL("INSERT INTO state VALUES(0, 'busComing@hit.edu.cn', '哈尔滨')");
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }
}
