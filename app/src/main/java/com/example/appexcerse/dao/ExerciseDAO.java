package com.example.appexcerse.dao;

import com.example.appexcerse.model.Exercise;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class ExerciseDAO {
    private String path = "Exercise";
    private List<Exercise> exerciseList;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child(path);

    public ExerciseDAO() {
    }

    public List<Exercise> getAll() {
        return exerciseList;
    }

    public void insert(Exercise exercise) {
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(exercise.getId()), exercise);
        database.getReference().child(path).updateChildren(hashMap);
    }

    public void update(Exercise exercise) {
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(exercise.getId()), exercise);
        database.getReference().child(path).updateChildren(hashMap);
    }

    public void delete(String id) {
        database.getReference().child(path).child(id).removeValue();
    }
}
