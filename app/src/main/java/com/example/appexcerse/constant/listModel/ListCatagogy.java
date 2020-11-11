package com.example.appexcerse.constant.listModel;

import com.example.appexcerse.constant.model.Catagory;
import com.example.appexcerse.model.Catagoty;

import java.util.ArrayList;
import java.util.List;

public class ListCatagogy {
    public static List<Catagoty> getAll(){
        List<Catagoty> catList = new ArrayList<>();
        catList.add(Catagory.clothes);
        catList.add(Catagory.dietarySupplement);
        catList.add(Catagory.equipemt);
        catList.add(Catagory.weight);
        return catList;
    }
    public static Catagoty getCatrgory (long id){
        List<Catagoty> getAll = getAll();
        for(Catagoty category : getAll){
            if(category.getId() == id) return category;
        }
        return null;
    }
}
