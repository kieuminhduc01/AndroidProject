package com.example.appexcerse.dao;

import com.example.appexcerse.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderDAO {
    private String path = "Order";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child(path);

    public void push(Order order) {
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(order.getId()), order);
        database.getReference().child(path).updateChildren(hashMap);
    }


    public void delete(String id) {
        database.getReference().child(path).child(id).removeValue();
    }
}
