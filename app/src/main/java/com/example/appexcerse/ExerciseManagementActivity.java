package com.example.appexcerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.appexcerse.area.admin.chucNang.Excercise.FragmentAddExercise;
import com.example.appexcerse.area.admin.chucNang.Excercise.FragmentListExercise;
import com.example.appexcerse.area.admin.chucNang.Product.FragmentAddProduct;
import com.example.appexcerse.area.admin.chucNang.Product.FragmentListProduct;

public class ExerciseManagementActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_management);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentListExercise fragmentListExercise = new FragmentListExercise();
        transaction.add(R.id.frameLayout,fragmentListExercise);
        transaction.commit();
    }

    public void btnAddClick(View view) {
       FragmentAddExercise fragmentAddExercise = new FragmentAddExercise();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentAddExercise);
        transaction.commit();
        transaction.addToBackStack(null);
    }
}