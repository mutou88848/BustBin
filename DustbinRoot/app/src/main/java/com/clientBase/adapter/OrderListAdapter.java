package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dustbin.R;
import com.clientBase.listener.RecommendListner;
import com.clientBase.model.OrderBean;

import java.util.List;

public class OrderListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<OrderBean> list_result;
    private int posIndex;
    private Context mContext;
    private RecommendListner mRecommendListner;

    public OrderListAdapter(Context context, List<OrderBean> list_result) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.list_result = list_result;
    }

    @Override
    public int getCount() {
        return list_result.size();
    }

    @Override
    public Object getItem(int position) {
        return list_result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_friendmessage, null);
            holder = new ViewHolder();
            holder.startAddress = (TextView) convertView.findViewById(R.id.startAddress);
            holder.endAddress = (TextView) convertView.findViewById(R.id.endAddress);
            holder.moneyInfor = (TextView) convertView.findViewById(R.id.moneyInfor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.startAddress.setText(list_result.get(position).getOrderAddressStart());
        holder.endAddress.setText(list_result.get(position).getOrderAddressEnd());
        holder.moneyInfor.setText("￥"+list_result.get(position).getOrderMoney());
        return convertView;

    }

    private class ViewHolder {
        private TextView startAddress;
        private TextView endAddress;
        private TextView moneyInfor;
    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
