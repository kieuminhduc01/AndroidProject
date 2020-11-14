package com.example.appexcerse.area.admin.chucNang.Shopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appexcerse.R;
import com.example.appexcerse.constant.listModel.ListLevel;
import com.example.appexcerse.constant.listModel.ListOrderStatus;
import com.example.appexcerse.model.Order;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentOrderView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOrderView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  Order order;
    private Spinner spinnerStatus;


    public FragmentOrderView() {
        // Required empty public constructor
    }
    public FragmentOrderView(Order order) {
        // Required empty public constructor
        this.order = order;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOrderView.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOrderView newInstance(String param1, String param2) {
        FragmentOrderView fragment = new FragmentOrderView();
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
        return inflater.inflate(R.layout.fragment_order_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerStatus = view.findViewById(R.id.spinnerStatus);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, ListOrderStatus.getAll());
        spinnerStatus.setAdapter(spinnerAdapter);
        spinnerStatus.setSelection(spinnerAdapter.getPosition(order.getStatus()));
    }
}