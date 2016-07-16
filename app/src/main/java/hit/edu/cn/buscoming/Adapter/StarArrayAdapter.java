package hit.edu.cn.buscoming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hit.edu.cn.buscoming.DB.Star;
import hit.edu.cn.buscoming.R;

/**
 * Created by rccoder on 2016/7/15.
 */

public class StarArrayAdapter extends ArrayAdapter<Star> {
    private int[] colors = new int[] { 0xff3cb371, 0xffa0a0a0 };
    private Context mContext;
    private int resource;

    public StarArrayAdapter(Context context, int resource, List<Star> data) {
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

            holder.title = (ImageView) convertView.findViewById(R.id.ItemTitle);
            holder.text = (TextView) convertView.findViewById(R.id.ItemText);
            holder.text2=(TextView)convertView.findViewById(R.id.ItemText2);

            // 将holder绑定到convertView
            convertView.setTag(holder);
        } else {
            holder = (StarArrayAdapter.ViewHolder) convertView.getTag();
        }

        holder.title.setImageResource(R.drawable.ic_menu_home);
        holder.text.setText(getItem(position).getLine_city());
        holder.text2.setText(getItem(position).getLine_line());


        convertView.setBackgroundColor(colors[position]);

        return convertView;
    }

    /**
     * ViewHolder类用以储存item中控件的引用
     */
    final class ViewHolder {
        //ImageView image;
        ImageView title;
        TextView text;
        TextView text2;
    }
}
