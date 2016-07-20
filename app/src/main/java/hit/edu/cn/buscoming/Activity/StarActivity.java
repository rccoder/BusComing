package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hit.edu.cn.buscoming.Adapter.RecentArrayAdapter;
import hit.edu.cn.buscoming.Adapter.StarArrayAdapter;
import hit.edu.cn.buscoming.DB.DBManager;
import hit.edu.cn.buscoming.DB.Recent;
import hit.edu.cn.buscoming.DB.Star;
import hit.edu.cn.buscoming.R;
import hit.edu.cn.buscoming.Base.BaseActivity;

public class StarActivity extends BaseActivity {

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
        setContentView(R.layout.activity_star);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        initviews();
        mInflater = LayoutInflater.from(StarActivity.this);
        lv=(ListView)findViewById(R.id.star1);
        txt=(TextView)findViewById(R.id.starText);
        view1 = mInflater.inflate(R.layout.activity_star, null);
        view2 = mInflater.inflate(R.layout.activity_star, null);
        view3 = mInflater.inflate(R.layout.activity_star, null);

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

        mAdapter = new MyPagerAdapter(StarActivity.this, mViewList, mTitleList);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            DBManager db = new DBManager(StarActivity.this);
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                {

                    List<Star> s = db.getStar(sgetname(),1,5);
                    s.get(0).setLine_city("search"+s.get(0).getLine_city());
                    StarArrayAdapter _adapter = new StarArrayAdapter(StarActivity.this,R.layout.list_item,s);
                    lv.setAdapter(_adapter);
                    txt.setText("最近收藏路线查询");
                }
                else if (position==1)
                {
                    List<Star> s = db.getStar(sgetname(),2,5);
                    //s.get(0).setStop_city("search"+s.get(0).getStop_city());
                    StarArrayAdapter _adapter = new StarArrayAdapter(StarActivity.this,R.layout.list_item,s);
                    lv.setAdapter(_adapter);
                    txt.setText("最近收藏站点查询");
                }
                else
                {
                    List<Star> s = db.getStar(sgetname(),3,5);
                    //s.get(0).setDes_city("search"+s.get(0).getDes_city());
                    StarArrayAdapter _adapter = new StarArrayAdapter(StarActivity.this,R.layout.list_item,s);
                    lv.setAdapter(_adapter);
                    txt.setText("最近收藏两点查询");
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
