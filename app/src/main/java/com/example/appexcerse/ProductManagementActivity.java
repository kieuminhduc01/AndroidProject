package com.example.appexcerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.appexcerse.area.admin.chucNang.Product.FragmentListProduct;
import com.example.appexcerse.area.admin.chucNang.User.FragmentListUser;

public class ProductManagementActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentListProduct fragmentListProduct = new FragmentListProduct();
        transaction.add(R.id.frameLayout,fragmentListProduct);
        transaction.commit();
    }
}