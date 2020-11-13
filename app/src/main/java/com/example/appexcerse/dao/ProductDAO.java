package com.example.appexcerse.dao;

import com.example.appexcerse.model.Product;
import com.example.appexcerse.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {
    private String path = "Product";
    private List<User> allUsers = new ArrayList<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child(path);
    public void update(Product product){
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(product.getId()),product);
        database.getReference().child(path).updateChildren(hashMap);
    }
    public void insert (Product product){

        database.getReference().child(path).push().setValue(product);
    }

}
