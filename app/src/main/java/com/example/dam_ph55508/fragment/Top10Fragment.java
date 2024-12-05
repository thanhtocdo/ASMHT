package com.example.dam_ph55508.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam_ph55508.DAO.Top10DAO;
import com.example.dam_ph55508.R;
import com.example.dam_ph55508.adapter.Top10Adapter;
import com.example.dam_ph55508.model.Sach;

import java.util.ArrayList;



public class Top10Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10, container, false);
        // Inflate the layout for this fragment
        RecyclerView rcvTop10 = view.findViewById(R.id.rcvTop10);

        Top10DAO top10DAO = new Top10DAO(getContext());
        ArrayList<Sach> lst = top10DAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), lst);
        rcvTop10.setAdapter(adapter);

        return view;
    }
}