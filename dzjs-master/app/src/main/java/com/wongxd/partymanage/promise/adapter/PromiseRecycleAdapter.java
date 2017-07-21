package com.wongxd.partymanage.promise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.promise.bean.PromiseBean;

import java.util.List;

/**
 * Created by json on 2017/7/11.
 */

public class PromiseRecycleAdapter extends RecyclerView.Adapter<PromiseRecycleAdapter.MyViewHolder>
        implements View.OnClickListener {
    private List<PromiseBean> list;
    private Context mContext;
    private OnItemClickListener itemClickListener = null;

    public PromiseRecycleAdapter(List<PromiseBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        holder.partyName.setText(list.get(position).getName());
//        holder.partyDepartment.setText(list.get(position).getDepartment());
//        String text = Html.fromHtml("<big>学习目标：</big>") + list.get(position).getPartyStudy();
//        holder.partyStudy.setText(text);
//        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if(itemClickListener != null){
            itemClickListener.onItemClickListener(v, (Integer) v.getTag());
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView partyName;
        TextView partyDepartment;
        TextView partyStudy;
        TextView partySeaDeteail;

        public MyViewHolder(View itemView) {
            super(itemView);
//            partyName = (TextView) itemView.findViewById(R.id.party_name);
//            partyDepartment = (TextView) itemView.findViewById(R.id.party_department);
//            partyStudy = (TextView) itemView.findViewById(R.id.party_study);
//            partySeaDeteail = (TextView) itemView.findViewById(R.id.tv_sea_detail);
        }
    }


    public static interface OnItemClickListener{
        void onItemClickListener(View view, int postion);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
