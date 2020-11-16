package com.example.appexcerse.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appexcerse.R;
import com.example.appexcerse.model.Exercise;

import java.util.List;

public class ExerciseAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Exercise> exerciseList;

    public ExerciseAdapter(Activity activity, int layout, List<Exercise> exerciseList) {
        this.activity = activity;
        this.layout = layout;
        this.exerciseList = exerciseList;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtName;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.customlistview_exercise,null);
        imageView = convertView.findViewById(R.id.imgView);
        txtName = convertView.findViewById(R.id.txtName);
        convertView.setTag(R.id.imageView, imageView);
        convertView.setTag(R.id.txtName, txtName);
        }else{
            imageView = (ImageView) convertView.getTag(R.id.imgView);
            txtName = (TextView) convertView.getTag(R.id.txtName);
        }
        Exercise exercise = exerciseList.get(position);
        txtName.setText(exercise.getName());

        Glide.with(convertView).load(exercise.getImgUrl()).into(imageView);

        return convertView;
    }
}
