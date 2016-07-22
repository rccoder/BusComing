package hit.edu.cn.buscoming.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hit.edu.cn.buscoming.DB.Recent;
import hit.edu.cn.buscoming.R;

/**
 * Created by rccoder on 2016/7/15.
 */

public class RecentArrayAdapter extends ArrayAdapter<Recent> {
    //private int[] colors = new int[] { 0xffc0c4db, 0xfffce3ed };
    private Context mContext;
    private int resource;

    public RecentArrayAdapter(Context context, int resource, List<Recent> data) {
        super(context, resource, data);
        this.mContext = context;
        this.resource = resource;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(resource, null);

            holder.image = (ImageView) convertView.findViewById(R.id.ItemTitle);
            holder.text = (TextView) convertView.findViewById(R.id.ItemText);
            holder.text2=(TextView)convertView.findViewById(R.id.ItemText2);
            holder.text3=(TextView)convertView.findViewById(R.id.ItemText3);

            // 存在问题，需要判断是空的情况
            // 将holder绑定到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(R.drawable.streetsign);
        if(getItem(position).getFlag()==1)
        {
            if(getItem(position).getLine_line()!=null)
            {
                holder.text.setText("城市："+getItem(position).getLine_city());
                holder.text2.setText(getItem(position).getLine_line()+"路");

            }
            else
            {
                holder.text.setText(getItem(position).getLine_city());
                holder.text2.setText(getItem(position).getLine_line());
            }
        }
        else if(getItem(position).getFlag()==2)
        {
            if(getItem(position).getStop_stop()!=null)
            {
                holder.text.setText("城市：" +getItem(position).getStop_city());
                holder.text2.setText(getItem(position).getStop_stop());
            }
            else
            {
                holder.text.setText(getItem(position).getStop_city());
                holder.text2.setText(getItem(position).getStop_stop());

            }
        }
        else if(getItem(position).getFlag()==3)
        {
            if(getItem(position).getDes_src()!=null)
            {
                holder.text.setText("城市："+getItem(position).getDes_city());
                holder.text2.setText("始发站："+getItem(position).getDes_src());
                holder.text3.setText("目的地："+getItem(position).getDes_des());
            }
            else
            {
                holder.text.setText(getItem(position).getDes_city());
                holder.text2.setText(getItem(position).getDes_src());
                holder.text3.setText(getItem(position).getDes_des());

            }
        }


        //convertView.setBackgroundColor(colors[position]);

        return convertView;
    }

    /**
     * ViewHolder类用以储存item中控件的引用
     */
    final class ViewHolder {
        //ImageView image;
        ImageView image;
        TextView text;
        TextView text2;
        TextView text3;
    }
}