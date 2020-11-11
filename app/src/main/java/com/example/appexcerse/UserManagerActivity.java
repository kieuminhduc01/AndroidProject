package com.example.appexcerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.appexcerse.adapter.UserAdapter;
import com.example.appexcerse.area.admin.chucNang.User.FragmentListUser;
import com.example.appexcerse.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagerActivity extends AppCompatActivity {
 private FrameLayout frameLayout;
 private FragmentManager manager;
 private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentListUser fragmentListUser = new FragmentListUser();
        transaction.add(R.id.frameLayout,fragmentListUser);
        transaction.commit();

    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment)
                .commit();
        
    }
}