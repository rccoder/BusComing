package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hit.edu.cn.buscoming.Adapter.RecentArrayAdapter;
import hit.edu.cn.buscoming.Base.BaseActivity;
import hit.edu.cn.buscoming.DB.DBManager;
import hit.edu.cn.buscoming.DB.Recent;
import hit.edu.cn.buscoming.R;
public class RecentActivity extends BaseActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private List<String> mTitleList;
    private List<View> mViewList;

    private LayoutInflater mInflater;

    private View view1, view2, view3;
    private MyPagerAdapter mAdapter;
    private ListView lv;
    private TextView txt;


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

    private void initviews() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mTitleList = new ArrayList<>();
        mViewList = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);




        initviews();
        mInflater = LayoutInflater.from(RecentActivity.this);
        lv=(ListView)findViewById(R.id.recent1);
        txt=(TextView)findViewById(R.id.recentText);
        view1 = mInflater.inflate(R.layout.activity_recent, null);
        view2 = mInflater.inflate(R.layout.activity_recent, null);
        view3 = mInflater.inflate(R.layout.activity_recent, null);

        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mTitleList.add("路线查询");
        mTitleList.add("站点查询");
        mTitleList.add("两点查询");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//系统默认模式、
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));

        mAdapter = new MyPagerAdapter(RecentActivity.this, mViewList, mTitleList);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            DBManager db = new DBManager(RecentActivity.this);
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                {

                    List<Recent> r = db.getRecent(sgetname(),1,5);

                    r.get(0).setLine_city("城市:"+r.get(0).getLine_city());
                    r.get(0).setLine_line(r.get(0).getLine_line()+"路");
                    RecentArrayAdapter _adapter = new RecentArrayAdapter(RecentActivity.this,R.layout.list_item,r);
                    lv.setAdapter(_adapter);
                    txt.setText("最近查询路线");
                }
                else if(position==1)
                {
                    List<Recent> r = db.getRecent(sgetname(),2,5);
                    RecentArrayAdapter _adapter = new RecentArrayAdapter(RecentActivity.this,R.layout.list_item,r);
                    lv.setAdapter(_adapter);
                    txt.setText("最近查询站点");
                }
                else
                {
                    List<Recent> r = db.getRecent(sgetname(),3,5);
                    RecentArrayAdapter _adapter = new RecentArrayAdapter(RecentActivity.this,R.layout.list_item,r);
                    lv.setAdapter(_adapter);
                    txt.setText("最近查询两点路线");
                }

                //s=String.valueOf(position);
                //Log.d("pos",s);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }
}
