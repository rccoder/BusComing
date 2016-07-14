package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hit.edu.cn.buscoming.Base.BaseActivity;
import hit.edu.cn.buscoming.DB.DBManager;
import hit.edu.cn.buscoming.DB.Recent;
import hit.edu.cn.buscoming.R;
public class RecentActivity extends BaseActivity {

    public String sgetname(){
        SharedPreferences sharedPreferences = getSharedPreferences("ussss", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user","unknown");
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        DBManager db = new DBManager(RecentActivity.this);
        List<Recent> r = db.getRecent(sgetname(),1,2);
        ListView _listv = (ListView) findViewById(R.id.recent1);
        ArrayAdapter<Recent> _adapter = new ArrayAdapter<Recent>(RecentActivity.this,android.R.layout.simple_list_item_1,r);
        _listv.setAdapter(_adapter);



    }
}
