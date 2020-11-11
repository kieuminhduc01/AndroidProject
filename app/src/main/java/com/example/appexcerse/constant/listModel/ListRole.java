package com.example.appexcerse.constant.listModel;

import com.example.appexcerse.constant.model.RoleUser;

import java.util.ArrayList;
import java.util.List;

public class ListRole {
    public static List<String> getAll() {
        List<String> roles = new ArrayList<>();
        roles.add(RoleUser.Admin);
        roles.add(RoleUser.User);
        return roles;
    }
}
