package com.example.appexcerse.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appexcerse.R;
import com.example.appexcerse.constant.listModel.ListCatagogy;
import com.example.appexcerse.constant.model.Catagory;
import com.example.appexcerse.model.Product;
import com.example.appexcerse.model.User;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Product> productList;

    public ProductAdapter(Activity activity, int layout, List<Product> productList) {
        this.activity = activity;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtName;
        TextView txtCategory;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.customlistview_product,null);
            txtName = convertView.findViewById(R.id.txtName);
            txtCategory = convertView.findViewById(R.id.txtCatagory);
            imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(R.id.txtName,txtName);
            convertView.setTag(R.id.imageView,imageView);
            convertView.setTag(R.id.txtCatagory,txtCategory);
        }else{
            txtName = (TextView) convertView.findViewById(R.id.txtName);
            imageView = (ImageView) convertView.findViewById(R.id.imageView);
            txtCategory = (TextView) convertView.findViewById(R.id.txtCatagory);
        }
        Product product = productList.get(position);
        txtName.setText(product.getName());
        Glide.with(convertView).load(product.getImgUrl()).into(imageView);
        txtCategory.setText(ListCatagogy.getCatrgory(product.getId()).getName());

   return  convertView;
    }

}
