package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import hit.edu.cn.buscoming.Adapter.StarArrayAdapter;
import hit.edu.cn.buscoming.DB.DBManager;
import hit.edu.cn.buscoming.DB.Star;
import hit.edu.cn.buscoming.R;
import hit.edu.cn.buscoming.Base.BaseActivity;

public class StarActivity extends BaseActivity {


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
        setContentView(R.layout.activity_star);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DBManager db = new DBManager(StarActivity.this);
        List<Star> s = db.getStar(sgetname(),1,2);
        ListView _listv = (ListView) findViewById(R.id.star1);
        s.get(0).setLine_city("search"+s.get(0).getLine_city());
        StarArrayAdapter _adapter = new StarArrayAdapter(StarActivity.this,R.layout.list_item,s);
        _listv.setAdapter(_adapter);

        List<Star> s2 = db.getStar(sgetname(),2,2);
        ListView _listv2 = (ListView) findViewById(R.id.star2);
        StarArrayAdapter _adapter2 = new StarArrayAdapter(StarActivity.this,R.layout.list_item,s2);
        _listv2.setAdapter(_adapter2);

    }
}
