package com.example.appexcerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.appexcerse.area.admin.chucNang.Product.FragmentAddProduct;
import com.example.appexcerse.area.admin.chucNang.Product.FragmentListProduct;
import com.example.appexcerse.area.admin.chucNang.Shopping.FragmentListOrder;

public class OrderManagementActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management_activiry);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentListOrder fragmentListOrder = new FragmentListOrder();
        transaction.add(R.id.frameLayout,fragmentListOrder);
        transaction.commit();
    }

    public void btnAddClick(View view) {
        FragmentListOrder fragmentListOrder = new FragmentListOrder();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentListOrder);
        transaction.commit();
        transaction.addToBackStack(null);
    }
}