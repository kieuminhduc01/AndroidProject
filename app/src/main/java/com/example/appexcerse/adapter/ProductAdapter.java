package com.example.appexcerse.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appexcerse.R;
import com.example.appexcerse.model.Product;

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
        TextView txtQuantity;

        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.customlistview_product,null);
            txtName = convertView.findViewById(R.id.txtName);
            txtQuantity = convertView.findViewById(R.id.txtQuantity);
            imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(R.id.txtName,txtName);
            convertView.setTag(R.id.txtQuantity,txtQuantity);
            convertView.setTag(R.id.imageView,imageView);
        }else{
            txtName = (TextView) convertView.findViewById(R.id.txtName);
            txtQuantity = (TextView) convertView.findViewById(R.id.txtQuantity);
            imageView = (ImageView) convertView.findViewById(R.id.imageView);

        }
        Product product = productList.get(position);
        txtName.setText(product.getName());
        txtQuantity.setText(String.valueOf(product.getQuantiy()));
        System.out.println(product.getName());
        Glide.with(convertView).load(product.getImgUrl()).into(imageView);

   return  convertView;
    }

}
