package com.example.appexcerse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appexcerse.constant.model.RoleUser;
import com.example.appexcerse.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<User> listUser;
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private CheckBox cbxRememberUSer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                User user = data.getValue(User.class);
                    System.out.println(user.getId());
                    listUser.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                FancyToast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                System.out.println(username + "---" + password );
                switch (isUserExist(username,password)){
                    case -1 :
                        FancyToast.makeText(getApplicationContext(), "Account is not existed", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        break;
                    case 0:
                        FancyToast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show(); break;
                    case 1:
                        updateUI();
                        FancyToast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show(); break;

                }

            }
        });
    }



    private void updateUI() {
        Intent intent = new Intent(this, AdminDashBoardActivity.class);
        startActivity(intent);
    }


    private int isUserExist(String username, String password) {
        for (User user : listUser) {
            System.out.println(user.getRole());
            if (user.getUserName().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password) ) {
                if(user.getRole().equalsIgnoreCase(RoleUser.Admin)) return 1;
                return 0;
            }
        }
        return -1;
    }


}