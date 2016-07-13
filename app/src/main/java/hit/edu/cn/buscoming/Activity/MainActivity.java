package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hit.edu.cn.buscoming.Base.BaseActivity;
import hit.edu.cn.buscoming.Collector.ActivityCollector;
import hit.edu.cn.buscoming.R;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View test = findViewById(R.id.test);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void fabline (View view)
    {
        String city="";
        if ("unknown".equals(sgetcity()))
        {
            city="";
        }
        else
        {
            city = sgetcity();
        }
        Intent intent = new Intent();
        intent.putExtra("extra",city);
        intent.setClass(MainActivity.this,LineActivity.class);
        startActivity(intent);
    }

    public void fabstop (View view)
    {

    }

    public void fabdes (View view)
    {

    }

    //保存当前登录的用户的用户名和偏好城市
    public void ssave(String user,String city){
        SharedPreferences sharedPreferences = getSharedPreferences("ussss", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user",user);
        editor.putString("city",city);
        editor.commit();
    }

    //当前登录的用户名
    public String sgetname(){
        SharedPreferences sharedPreferences = getSharedPreferences("ussss",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user","unknown");
        return name;
    }

    //当前选择的城市
    public String sgetcity(){
        SharedPreferences sharedPreferences = getSharedPreferences("ussss",Context.MODE_PRIVATE);
        String city = sharedPreferences.getString("city","unknown");
        return city;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Log.d("Create Menu", "Created");
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("Created", "Menu  Created");
        /*
        TextView textEmail = (TextView)findViewById(R.id.textEmail);
        if("unknown".equals(sgetname())) {
            textEmail.setText("unknow");
        } else {
            textEmail.setText(sgetname());
        }
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar activity_city_select_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view activity_city_select_item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_login) {
            Intent nav_login_intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(nav_login_intent);
        } else if (id == R.id.nav_city) {
            Intent nav_select_city_intent = new Intent(MainActivity.this, CitySelectActivity.class);
            startActivity(nav_select_city_intent);
        } else if(id == R.id.nav_about) {
            Intent nav_about_intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(nav_about_intent);
        }else if(id == R.id.nav_logout) {
            ssave("unknown","unknown");
            Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_exit) {
            Log.d("Now", "exit");
            ActivityCollector.finishAll();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
