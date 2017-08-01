//package com.wongxd.partymanage.partycontact.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.wongxd.partymanage.R;
//import com.wongxd.partymanage.partycontact.bean.ContactParty;
//
//import java.util.List;
//
///**
// * Created by zyj on 2017/7/20.
// */
//
//public class ContactPartyAdapter extends BaseAdapter {
//    List<ContactParty> partyList ;
//    private Context mContext;
//
//    public ContactPartyAdapter(List<ContactParty> partyList, Context mContext) {
//        this.partyList = partyList;
//        this.mContext = mContext;
//    }
//
//    @Override
//    public int getCount() {
//        return partyList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return partyList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if(convertView == null){
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contact_party, parent, false);
//            holder.month = (TextView) convertView.findViewById(R.id.list_month);
//            holder.persion = (TextView) convertView.findViewById(R.id.list_persion);
//            holder.action = (TextView) convertView.findViewById(R.id.list_action);
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.month.setText(partyList.get(position).getTime());
//        holder.persion.setText("联系对象：" + partyList.get(position).getPerson());
//        holder.action.setText( partyList.get(position).getHelpNotes());
//        return convertView;
//    }
//
//
//    class ViewHolder{
//        private TextView month;
//        private TextView persion;
//        private TextView action;
//    }
//}
