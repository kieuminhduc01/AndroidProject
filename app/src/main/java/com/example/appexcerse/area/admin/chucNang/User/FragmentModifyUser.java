package com.example.appexcerse.area.admin.chucNang.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appexcerse.R;
import com.example.appexcerse.constant.listModel.ListRole;
import com.example.appexcerse.dao.UserDAO;
import com.example.appexcerse.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModifyUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModifyUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User user;
    private Spinner spinnerRole;
    private TextView txtUsername;
    private Button btnUpdate;
    public FragmentModifyUser() {
        // Required empty public constructor
    }
    public FragmentModifyUser(User user) {
        // Required empty public constructor
        this.user = user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentModifyUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentModifyUser newInstance(String param1, String param2) {
        FragmentModifyUser fragment = new FragmentModifyUser();
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
        return inflater.inflate(R.layout.fragment_modify_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerRole = view.findViewById(R.id.spinnerRole);
        txtUsername = view.findViewById(R.id.txtUsername);
        btnUpdate = view.findViewById(R.id.btnUpdate);


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ListRole.getAll());
        spinnerRole.setAdapter(spinnerAdapter);
      txtUsername.setText(user.getUserName());
        spinnerRole.setSelection( spinnerAdapter.getPosition(user.getRole()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              user.setRole(spinnerRole.getSelectedItem().toString());
              new UserDAO().push(user);

            }
        });
    }
}