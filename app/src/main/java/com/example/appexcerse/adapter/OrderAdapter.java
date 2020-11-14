package com.example.appexcerse.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appexcerse.R;
import com.example.appexcerse.area.admin.logicUtil.Util;
import com.example.appexcerse.model.Exercise;
import com.example.appexcerse.model.Order;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Order> orderList;

    public OrderAdapter(Activity activity, int layout, List<Order> orderList) {
        this.activity = activity;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtId;
        TextView txtCreatedDate;
        TextView txtTotalAmount;
        TextView txtStatus;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.customlistview_order,null);

            txtId = convertView.findViewById(R.id.txtId);
            txtCreatedDate = convertView.findViewById(R.id.txtCreatedDate);
            txtTotalAmount = convertView.findViewById(R.id.txtTotalAmount);
            txtStatus = convertView.findViewById(R.id.txtStatus);


            convertView.setTag(R.id.txtId,txtId);
            convertView.setTag(R.id.txtStatus,txtStatus);
            convertView.setTag(R.id.txtTotalAmount,txtTotalAmount);
            convertView.setTag(R.id.txtCreatedDate,txtCreatedDate);
        }else{
            txtId = (TextView) convertView.getTag(R.id.txtId);
            txtTotalAmount = (TextView) convertView.getTag(R.id.txtTotalAmount);
            txtCreatedDate = (TextView) convertView.getTag(R.id.txtCreatedDate);
            txtStatus = (TextView) convertView.getTag(R.id.txtStatus);
        }

        Order order = orderList.get(position);
        txtId.setText(order.getId());
        txtCreatedDate.setText(Util.dateToHumanReadableString(Util.ParseISO_8601_FORMATToDate(order.getCreatedDate())));
        txtStatus.setText(order.getStatus());
        txtTotalAmount.setText(String.valueOf(order.getTotalAmount()));
        return convertView;
    }
}
