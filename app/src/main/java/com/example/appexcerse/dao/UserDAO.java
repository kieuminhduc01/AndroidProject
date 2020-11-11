package com.example.appexcerse.dao;

import com.example.appexcerse.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private String path = "User";
    private List<User> allUsers = new ArrayList<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child(path);

    public UserDAO() {

    }

    public List<User> getAllUsers() {
        return allUsers;
    }
    public void updateUser(User user){
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(user.getId()),user);
        database.getReference().child(path).updateChildren(hashMap);

    }
}
