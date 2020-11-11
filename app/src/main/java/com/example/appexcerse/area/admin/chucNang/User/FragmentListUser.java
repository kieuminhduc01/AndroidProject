package com.example.appexcerse.area.admin.chucNang.User;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appexcerse.R;
import com.example.appexcerse.UserManagerActivity;
import com.example.appexcerse.adapter.UserAdapter;
import com.example.appexcerse.constant.model.RoleUser;
import com.example.appexcerse.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UserAdapter adapter;
    private ListView listView;
    private List<User> userList;

    public FragmentListUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListUser newInstance(String param1, String param2) {
        FragmentListUser fragment = new FragmentListUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_user, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userList =  new ArrayList<>();
        userList.add(new User("Datnvhe130670","Nguyendat99", RoleUser.Admin));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        userList.add(new User("danhn","Nguyendat99",RoleUser.Customer));
        listView = view.findViewById(R.id.listView);
        adapter = new UserAdapter(getActivity(),R.layout.customlistview_admin_user_list,userList);
        listView.setAdapter(adapter);
        Activity currentActivity =  getActivity();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentModifyUser fragmentModifyUser = new FragmentModifyUser(userList.get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, fragmentModifyUser)
                        .commit();
                transaction.addToBackStack(null);
            }
        });
    }
}