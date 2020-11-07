package com.example.appexcerse.constant.listModel;

import com.example.appexcerse.constant.model.Level;

import java.util.ArrayList;
import java.util.List;

public class ListLevel {
    public static List<String> getAll(){
        List<String> levels=new ArrayList<>();
        levels.add(Level.Intermediate);
        levels.add(Level.Advanced);
        levels.add(Level.Beginner);
        return levels;
    }
}
