package com.wongxd.partymanage.partycontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wongxd.partymanage.R;

import java.util.List;

/**
 * Created by zyj on 2017/7/21.
 */

public class PopupAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> timeList;

    public PopupAdapter(Context mContext, List<String> timeList) {
        this.mContext = mContext;
        this.timeList = timeList;
    }

    @Override
    public int getCount() {
        return timeList.size();
    }

    @Override
    public Object getItem(int position) {
        return timeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_popupwindow_list, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.time_list_item);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(timeList.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
