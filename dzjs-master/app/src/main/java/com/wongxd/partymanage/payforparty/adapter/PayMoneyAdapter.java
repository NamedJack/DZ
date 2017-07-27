package com.wongxd.partymanage.payforparty.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.payforparty.bean.MoneyOfParty;

import java.util.List;

/**
 * Created by zyj on 2017/7/22.
 */

public class PayMoneyAdapter extends BaseAdapter {
    private List<MoneyOfParty> moneyList;
    private Context mContext;

    public PayMoneyAdapter(List<MoneyOfParty> moneyList, Context mContext) {
        this.moneyList = moneyList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return moneyList.size();
    }

    @Override
    public Object getItem(int position) {
        return moneyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_of_money, parent, false);
            holder = new MyViewHolder();
            holder.month = (TextView) convertView.findViewById(R.id.item_pay_money_month);
            holder.day = (TextView) convertView.findViewById(R.id.month_of_year);
            holder.totalMoney = (TextView) convertView.findViewById(R.id.money_of_should_pay);
            holder.paidMoney = (TextView) convertView.findViewById(R.id.money_have_paid);
            holder.isPaid = (ImageView) convertView.findViewById(R.id.icon_is_paid);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.month.setText(moneyList.get(position).getTime().split("-")[1] + "月党费缴纳");
        holder.day.setText(moneyList.get(position).getTime());
        holder.totalMoney.setText(moneyList.get(position).getPayable() + "  (元)");
        holder.paidMoney.setText(moneyList.get(position).getPaid()+ "  (元)");
        Log.e("msg","缴费状态" + moneyList.get(position).getState());
        if(2 == moneyList.get(position).getState() || 3 == moneyList.get(position).getState()){
            holder.isPaid.setImageResource(R.drawable.icon_monry_paid_img);
        }else if( 1 == moneyList.get(position).getState()){
            holder.isPaid.setImageResource(R.drawable.icon_monry_not_pay_img);
        }
        return convertView;
    }

    class MyViewHolder {
        TextView month;
        TextView day;
        TextView totalMoney;
        TextView paidMoney;
        TextView payAddress;
        ImageView isPaid;
    }
}
