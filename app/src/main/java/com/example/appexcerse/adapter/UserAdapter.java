package com.example.appexcerse.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appexcerse.R;
import com.example.appexcerse.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<User> userList;

    public UserAdapter(Activity activity, int layout, List<User> userList) {
        this.activity = activity;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textUsername;
        TextView textRole;
        if(convertView == null){
        convertView =    activity.getLayoutInflater().inflate(R.layout.customlistview_admin_user_list,null);
        textUsername = convertView.findViewById(R.id.txt3);
        textRole = convertView.findViewById(R.id.txtRole);
        convertView.setTag(R.id.txt3,textUsername);
        convertView.setTag(R.id.txtRole,textRole);

        }else{
            textUsername = (TextView) convertView.getTag(R.id.txt3);
            textRole = (TextView) convertView.getTag(R.id.txtRole);
        }
        User user = userList.get(position);
        textUsername.setText(user.getUserName());
        textRole.setText(user.getRole());

        return convertView;
    }
}
