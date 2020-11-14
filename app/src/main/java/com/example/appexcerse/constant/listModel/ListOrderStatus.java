package com.example.appexcerse.constant.listModel;

import com.example.appexcerse.constant.model.OrderStatus;
import com.example.appexcerse.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ListOrderStatus {



    public static List<String> getAll(){
       List<String> result = new ArrayList<>();
       result.add(OrderStatus.Complete);
       result.add(OrderStatus.Aborted);
       result.add(OrderStatus.Approved);
       result.add(OrderStatus.Delivering);
       result.add(OrderStatus.Failed);
       result.add(OrderStatus.Paid);
       result.add(OrderStatus.Pending);
       result.add(OrderStatus.Processing);
       return result;
    }
}
