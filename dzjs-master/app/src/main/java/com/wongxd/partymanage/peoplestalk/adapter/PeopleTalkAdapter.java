package com.wongxd.partymanage.peoplestalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.peoplestalk.bean.PeopleTalk;

import java.util.List;

/**
 * Created by zyj on 2017/7/19.
 */

public class PeopleTalkAdapter extends BaseAdapter {
    private List<PeopleTalk> talkList;
    private Context mContext;


    public PeopleTalkAdapter(List<PeopleTalk> talkList, Context mContext) {
        this.talkList = talkList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return talkList.size();
    }

    @Override
    public Object getItem(int position) {
        return talkList.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tlak_list,parent,false);
            holder.tlakPersion = (TextView) convertView.findViewById(R.id.talk_persion);
            holder.persionPoltics = (TextView) convertView.findViewById(R.id.poltics_persion);
            holder.advicePersion = (TextView) convertView.findViewById(R.id.advice_persion);
            holder.selfPersion = (TextView) convertView.findViewById(R.id.slft_persion);
            holder.tlakTime = (TextView) convertView.findViewById(R.id.tlak_years);
//            holder.tlakDay = (TextView) convertView.findViewById(R.id.tlak_day);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tlakTime.setText(talkList.get(position).getTlakTime());
        holder.persionPoltics.setText("政治面貌：" + talkList.get(position).getPersionPoltics());
        holder.tlakPersion.setText("谈心对象：" + talkList.get(position).getTlakPersion());
        holder.selfPersion.setText("自我认识及措施：" + talkList.get(position).getSelfPersion());
        holder.advicePersion.setText("意见和建议：" + talkList.get(position).getAdvicePersion());
//        holder.tlakYears.setText(talkList.get(position).getTlakYears());

        return convertView;
    }


    class  ViewHolder{

        TextView tlakPersion;
        TextView persionPoltics;
        TextView advicePersion;
        TextView selfPersion;
        TextView tlakTime;
//        TextView tlakDay;

    }
}
