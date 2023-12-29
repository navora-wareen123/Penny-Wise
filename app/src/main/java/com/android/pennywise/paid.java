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
    expense_database myDb;
    CustomAdapter customAdapter;
    ArrayList<String> expenseId, expenseName, expenseAmount, expenseTime, expenseDate, expenseCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_paid, container, false);

        // Find the RecyclerView by its ID in the fragment layout
        paid_cont = rootView.findViewById(R.id.rv_paidFragment);

        // Initialize your arrays
        expenseId = new ArrayList<>();
        expenseName = new ArrayList<>();
        expenseAmount = new ArrayList<>();
        expenseTime = new ArrayList<>();
        expenseDate = new ArrayList<>();
        expenseCategory = new ArrayList<>();

        // Initialize the database
        myDb = new expense_database(requireActivity());

        // Retrieve data from the database (replace with your actual data retrieval logic)
        readData();

        // Initialize and set the adapter for the RecyclerView
        customAdapter = new CustomAdapter(requireActivity(), expenseId, expenseName, expenseAmount, expenseDate, expenseTime, expenseCategory);
        paid_cont.setAdapter(customAdapter);
        paid_cont.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return rootView;
    }

    // Replace this method with your actual data retrieval logic
    void readData() {
        Cursor cursor = myDb.readAllPaidData();
        if (cursor.getCount() == 0) {
            // Handle case when there is no data
        } else {
            while (cursor.moveToNext()) {
                expenseId.add(cursor.getString(0));
                expenseName.add(cursor.getString(1));
                expenseAmount.add(cursor.getString(2));
                expenseTime.add(cursor.getString(4));
                expenseDate.add(cursor.getString(3));
                expenseCategory.add(cursor.getString(6));
            }
        }
    }
}
