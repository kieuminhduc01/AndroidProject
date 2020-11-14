package com.example.appexcerse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appexcerse.area.admin.logicUtil.Util;
import com.example.appexcerse.dao.OrderDAO;
import com.example.appexcerse.model.Order;
import com.example.appexcerse.model.OrderDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AdminDashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

    }

    public void loadExerciseActivity(View v) {
        Intent intent = new Intent(this,ExerciseManagementActivity.class);
        startActivity(intent);
    }

    public void loadOrderActivity(View v) {
        Intent intent = new Intent(this,OrderManagementActivity.class);
        startActivity(intent);
    }

    public void loadProductActivity(View v) {
        Intent intent = new Intent(this,ProductManagementActivity.class);
        startActivity(intent);
    }

    public void loadUserActivity(View v) {
        Intent intent = new Intent(this,UserManagerActivity.class);
        startActivity(intent);
    }

}