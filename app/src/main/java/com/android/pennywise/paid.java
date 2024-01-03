package com.android.pennywise;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class paid extends Fragment {
    RecyclerView paid_cont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_paid, container, false);

        // Find the RecyclerView by its ID in the fragment layout
        paid_cont = rootView.findViewById(R.id.rv_paidFragment);
        return rootView;
    }
}
