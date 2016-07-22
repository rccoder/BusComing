package hit.edu.cn.buscoming.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hit.edu.cn.buscoming.Base.BaseActivity;
import hit.edu.cn.buscoming.BusStatsObj.Res;
import hit.edu.cn.buscoming.BusStatsObj.stats;
import hit.edu.cn.buscoming.Config;
import hit.edu.cn.buscoming.DB.DBManager;
import hit.edu.cn.buscoming.DB.Recent;
import hit.edu.cn.buscoming.DB.Star;
import hit.edu.cn.buscoming.R;

public class LineActivity extends BaseActivity {

    public List<Map<String, Object>> mData;
    private String rc;
    private String rl;


    public String sgetname(){
        SharedPreferences sharedPreferences = getSharedPreferences("ussss", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user","unknown");
        return name;
    }


    // fab xml 调用
    public void star_content (View view)
    {
        DBManager db = new DBManager(LineActivity.this);

        // rencent 数据库里最近插入的一条记录
        List<Recent> r = db.getRecent(sgetname(),1,1);
        Star star = new Star(sgetname(), 1);

        star.setLine_city(r.get(0).getLine_city());
        star.setLine_line(r.get(0).getLine_line());

        if(db.saveStar(star)) {
            Toast.makeText(LineActivity.this, "收藏成功:"+r.get(0).getLine_city()+r.get(0).getLine_line(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LineActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String city = intent.getStringExtra("extra");

        rc = intent.getStringExtra("city");
        rl = intent.getStringExtra("line");

<<<<<<< HEAD

=======
>>>>>>> 621640fb2afdca8afa9d7322baa4f5fe7d04a2d9
        String line = intent.getStringExtra("extraline");


        final EditText editTextcity = (EditText)findViewById(R.id.inputcity);
        final EditText editTextline = (EditText)findViewById(R.id.inputline);

        if(rl!=null &&rl.isEmpty()==false)
        {
            editTextline.setText(rl);
        }
        editTextcity.setText(city);
        editTextline.setText(line);



        final Config config = (Config) getApplication();

        ImageButton button = (ImageButton)findViewById(R.id.linesearch);
        button.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(LineActivity.this, "searching", Toast.LENGTH_SHORT).show();
                new Thread() {
                    public void run() {

                        final String city = editTextcity.getText().toString();
                        final String line = editTextline.getText().toString();

                        Log.e("TAGG", "Thread is RUn");
                        RequestQueue requestQueue = Volley.newRequestQueue(LineActivity.this);

                        StringRequest jsonObjectRequest = new StringRequest(

//                                Request.Method.GET,"http://api.juheapi.com/bus/line?key="+config.getKey()+"&city=苏州&q=110",
                                Request.Method.GET,"http://api.juheapi.com/bus/line?key="+config.getKey()+"&city="+city+"&q="+line,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Log.d("TAGG", response.toString());
                                        Gson gson = new Gson();
                                        Res res;
                                        res = gson.fromJson(response,Res.class);

                                        DBManager db = new DBManager(LineActivity.this);


                                        Recent r = new Recent(sgetname(),1);
                                        r.setLine_city(city);
                                        r.setLine_line(line);
                                        db.saveRecent(r);




                                        ListView _listv = (ListView) findViewById(R.id.linesearchresult);
                                        List<String> _data = new ArrayList<String>();

                                        if (res.getError_code()==0)
                                        {
                                            _data.add(res.getResult().get(0).get_id());
                                            _data.add(res.getResult().get(0).getInfo()                                          );

                                            res.getResult().get(0).getStats();

                                            for (stats sta : res.getResult().get(0).getStats()) {
                                                _data.add(sta.get_id());
                                            }
                                        }
                                        else
                                        {
                                            _data.add("输入错误");
                                        }

                                        ArrayAdapter<String> _adapter = new ArrayAdapter<String>(LineActivity.this,android.R.layout.simple_list_item_1,_data);
                                        _listv.setAdapter(_adapter);

                                        //mTextView.setText(res.getResult().get(0).get_id() + "/n" + res.getResult().get(0).getInfo()+"/n");

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("TAGG", error.toString());
                            }
                        }
                        );
                        requestQueue.add(jsonObjectRequest);

                    }

                }.start();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
