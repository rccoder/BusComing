package hit.edu.cn.buscoming.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import hit.edu.cn.buscoming.Base.BaseActivity;
import hit.edu.cn.buscoming.City.Res;
import hit.edu.cn.buscoming.Collector.ActivityCollector;
import hit.edu.cn.buscoming.R;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            public void run() {
                Log.d("TAGG", "Thread is RUn");
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest("http://api.juheapi.com/bus/citys?key=636418115dd805e55190d3edc2548210",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Res _res = new Res();
                            _res = gson.fromJson(response, Res.class);
                            Log.d("JSON",  _res.getReason());
                            Log.d("TAGG", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("TAGG", error.getMessage(), error);
                        }
                    }
                );
                requestQueue.add(stringRequest);
            }

        }.start();

        /*
        ListView _listv = (ListView)findViewById(R.id.home_listview);

        final List<Map<String, Object>> _data = new ArrayList<Map<String, Object>>();

        Map<String, Object> row1 = new HashMap<String, Object>();
        row1.put("name", "你好");
        row1.put("age", "19");
        row1.put("img", R.drawable.ic_menu_about);
        _data.add(row1);

        Map<String, Object> row2 = new HashMap<String, Object>();
        row2.put("name", "你好");
        row2.put("age", "19");
        row2.put("img", R.drawable.ic_menu_about);
        _data.add(row2);

        Map<String, Object> row3 = new HashMap<String, Object>();
        row3.put("name", "你好");
        row3.put("age", "19");
        row3.put("img", R.drawable.ic_menu_about);
        _data.add(row3);

        SimpleAdapter _simpleAdapter = new SimpleAdapter(this,
                _data, R.layout.activity_city_select_item,
                new String[]{"name", "age", "img"},
                new int[]{R.id.name, R.id.age, R.id.img}
        );
        _listv.setAdapter(_simpleAdapter);
        */
        /*
        List<String> _data = new ArrayList<String>();
        _data.add("1");
        _data.add("1");
        _data.add("1");
        _data.add("1");
        ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _data);
        _listv.setAdapter(_adapter);

        _listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                adapterView.getChildAt(position);
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        } else if (id == R.id.nav_exit) {
            Log.d("Now", "exit");
            ActivityCollector.finishAll();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
