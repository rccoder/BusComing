package hit.edu.cn.buscoming.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import hit.edu.cn.buscoming.R;

public class LineActivity extends BaseActivity {
    public List<Map<String, Object>> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String city = intent.getStringExtra("extra");

        final EditText editTextcity = (EditText)findViewById(R.id.inputcity);
        final EditText editTextline = (EditText)findViewById(R.id.inputline);

        editTextcity.setText(city);

        Button button = (Button)findViewById(R.id.linesearch);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(LineActivity.this, "searching", Toast.LENGTH_SHORT).show();
                new Thread() {
                    public void run() {

                        String city = editTextcity.getText().toString();
                        String line = editTextline.getText().toString();
                        Log.e("TAGG", "Thread is RUn");
                        RequestQueue requestQueue = Volley.newRequestQueue(LineActivity.this);
                        //StringRequest stringRequest = new StringRequest("http://api.juheapi.com/bus/citys?key=dfe24b2fc63686cf2a0b87cc47d050dd",
                        StringRequest jsonObjectRequest = new StringRequest(
                                //JsonObjectRequest jsonobjectrequest = new JsonObjectRequest(
                                //Request.Method.GET,"http://api.juheapi.com/bus/line?key=dfe24b2fc63686cf2a0b87cc47d050dd&city="+city+"&q="+line,null,

                                Request.Method.GET,"http://api.juheapi.com/bus/line?key=dfe24b2fc63686cf2a0b87cc47d050dd&city="+city+"&q="+line,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Log.d("TAGG", response.toString());
                                        Gson gson = new Gson();
                                        Res res;
                                        res = gson.fromJson(response,Res.class);

                                        ListView _listv = (ListView) findViewById(R.id.linesearchresult);
                                        List<String> _data = new ArrayList<String>();

                                        _data.add(res.getResult().get(0).get_id());
                                        _data.add(res.getResult().get(0).getInfo()                                          );

                                        res.getResult().get(0).getStats();

                                        for (stats sta : res.getResult().get(0).getStats()) {
                                            _data.add(sta.get_id());
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
