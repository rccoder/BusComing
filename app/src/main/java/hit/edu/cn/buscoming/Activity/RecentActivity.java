package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import hit.edu.cn.buscoming.Adapter.RecentArrayAdapter;
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
    // actionBar back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DBManager db = new DBManager(RecentActivity.this);
        List<Recent> r = db.getRecent(sgetname(),1,2);
        ListView _listv = (ListView) findViewById(R.id.recent1);
        r.get(0).setLine_city("search"+r.get(0).getLine_city());
        RecentArrayAdapter _adapter = new RecentArrayAdapter(RecentActivity.this,R.layout.list_item,r);
        _listv.setAdapter(_adapter);

        List<Recent> r2 = db.getRecent(sgetname(),1,2);
        ListView _listv2 = (ListView) findViewById(R.id.recent2);
        RecentArrayAdapter _adapter2 = new RecentArrayAdapter(RecentActivity.this,R.layout.list_item,r2);
        _listv2.setAdapter(_adapter2);

    }
}
