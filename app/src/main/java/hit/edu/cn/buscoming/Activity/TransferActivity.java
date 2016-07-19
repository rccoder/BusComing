package hit.edu.cn.buscoming.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
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
import hit.edu.cn.buscoming.Busstation.Res;
import hit.edu.cn.buscoming.Config;
import hit.edu.cn.buscoming.R;
import hit.edu.cn.buscoming.Transfer.Res_transfer;
import hit.edu.cn.buscoming.Transfer.schema;
import hit.edu.cn.buscoming.Transfer.schemaObject;


public class TransferActivity extends BaseActivity {
    public List<Map<String, Object>> mData;
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String[] city = {intent.getStringExtra("extra")};

        spinner = (Spinner) findViewById(R.id.transferSpinner);
        data_list = new ArrayList<String>();
        data_list.add("0综合排序");
        data_list.add("1换乘次数");
        data_list.add("2步行距离");
        data_list.add("3时        间");
        data_list.add("4距        离");
        data_list.add("5地铁优先");
        arr_Adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_Adapter);

        final EditText editTextStartstat = (EditText)findViewById(R.id.transferStartET);
        final EditText editTextStarstat = (EditText)findViewById(R.id.transferEndET);
        final String[] rc = new String[1];

        final Config config = (Config) getApplication();

        //注册事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
               rc[0] = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rc[0] = "0综合排序";
            }
        });

        ImageButton button = (ImageButton)findViewById(R.id.transfersearch);
        button.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(TransferActivity.this, "searching", Toast.LENGTH_SHORT).show();
                new Thread() {
                    public void run() {

                        String startStat = editTextStartstat.getText().toString();
                        String endStat = editTextStarstat.getText().toString();

//                        startStat = "松涛街创苑路北";
//                        endStat = "海悦花园四区";
//                        city[0] = "苏州";
                        //通过站台查询获取经纬度
//                        SharedPreferences sharedPreferences = getSharedPreferences("ussss", Context.MODE_PRIVATE);
//                        final String city = sharedPreferences.getString("city","");
//                        System.out.println(city);
                        final String[] start_lat = new String[1];
                        final String[] start_lng = new String[1];
                        final String[] end_lat = new String[1];
                        final String[] end_lng = new String[1];

                        Log.e("TAGG", "Thread is RUn");

                        RequestQueue requestQueue1 = Volley.newRequestQueue(TransferActivity.this);

                        final String finalEndStat = endStat;
                        StringRequest jsonObjectRequest1 = new StringRequest(

                                Request.Method.GET,"http://api.juheapi.com/bus/stat?key="+config.getKey()+"&city="+ city[0] +"&q="+startStat,
                                //get the longitude and latitude of the start station
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Log.d("TAGG", response.toString());
                                        Gson gson1 = new Gson();
                                        Res res1;
                                        res1 = gson1.fromJson(response,Res.class);
                                        if(res1.getError_code() == 0) {
                                            start_lat[0] = res1.getResult().get(0).getLat();
                                            start_lng[0] = res1.getResult().get(0).getLng();
                                            System.out.println(start_lat[0] + " " + start_lng[0]);
                                        }
                                        else{
                                            Toast.makeText(TransferActivity.this, "起点输入错误", Toast.LENGTH_SHORT).show();
                                        }
                                        RequestQueue requestQueue2 = Volley.newRequestQueue(TransferActivity.this);

                                        StringRequest jsonObjectRequest2 = new StringRequest(


                                                Request.Method.GET,"http://api.juheapi.com/bus/stat?key="+config.getKey()+"&city="+ city[0] +"&q="+ finalEndStat,
                                                //get the longitude and latitude of the end station
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        //Log.d("TAGG", response.toString());
                                                        Gson gson2 = new Gson();
                                                        Res res2;
                                                        res2 = gson2.fromJson(response,Res.class);
                                                        if(res2.getError_code() == 0) {
                                                            end_lat[0] = res2.getResult().get(0).getLat();
                                                            end_lng[0] = res2.getResult().get(0).getLng();
                                                            System.out.println(end_lat[0] + " " + end_lng[0]);
                                                        }
                                                        else{
                                                            Toast.makeText(TransferActivity.this, "终点输入错误", Toast.LENGTH_SHORT).show();
                                                        }

                                                        RequestQueue requestQueue = Volley.newRequestQueue(TransferActivity.this);

                                                        StringRequest jsonObjectRequest = new StringRequest(

                                                                Request.Method.GET,"http://api.juheapi.com/bus/transfer?key="+config.getKey()+"&city="+ city[0] +"&start_lat="+ start_lat[0] +"&start_lng="+ start_lng[0] +"&end_lat="+ end_lat[0] +"&end_lng="+ end_lng[0] +"&rc="+rc[0].charAt(0),


                                                                new Response.Listener<String>() {
                                                                    @Override
                                                                    public void onResponse(String response) {
                                                                        //Log.d("TAGG", response.toString());
                                                                        Gson gson = new Gson();
                                                                        Res_transfer res;
                                                                        res = gson.fromJson(response,Res_transfer.class);

                                                                        ListView _listv = (ListView) findViewById(R.id.transfersearchresult);
                                                                        List<String> _data = new ArrayList<String>();

                                                                        if(res.getError_code() == 0) {
                                                                            System.out.println(res.getResult().getClass().getName());
                                                                            List<schema> result = res.getResult();
                                                                            for (int j = 0; j < result.size(); j++) {
                                                                                _data.add("换乘方案" + (j + 1));
                                                                                _data.add("步行距离：" + String.valueOf(result.get(j).getFootDist()) + "米");
                                                                                _data.add("耗        时：" + String.valueOf(result.get(j).getTime()) + "分钟");
                                                                                _data.add("总    距离：" + String.valueOf(result.get(j).getDist()) + "米");

//                                        System.out.println(res.getResult().get(0).getSegs().getClass().getName());
                                                                                ArrayList<schemaObject> seg = (ArrayList<schemaObject>) result.get(j).getSegs();
//                                        System.out.println(seg.getClass().getName());
                                                                                for (int i = 0; i < seg.size(); i++) {
                                                                                    _data.add("起始站台：" + seg.get(i).getStartStat());
                                                                                    _data.add("终点站台：" + seg.get(i).getEndStat());
                                                                                    _data.add("线        路："+seg.get(i).getLineName());
                                                                                    _data.add("经过站台："+seg.get(i).getStats());
                                                                                    _data.add("这次步行    距离：" + String.valueOf(seg.get(i).getFootDist()) + "米");
                                                                                    _data.add("这次换乘总距离：" + String.valueOf(seg.get(i).getLineDist()) + "米");
                                                                                }
                                                                                _data.add("最后一站需要步行距离：" + String.valueOf(result.get(j).getLastFootDist()) + "米");
                                                                                _data.add("");
                                                                            }
                                                                        }
                                                                        else{
                                                                            Toast.makeText(TransferActivity.this, "查询错误", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        ArrayAdapter<String> _adapter = new ArrayAdapter<String>(TransferActivity.this,android.R.layout.simple_list_item_1,_data);
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
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("TAGG", error.toString());
                                            }
                                        }
                                        );
                                        requestQueue2.add(jsonObjectRequest2);

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("TAGG", error.toString());
                            }
                        }
                        );
                        requestQueue1.add(jsonObjectRequest1);

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
