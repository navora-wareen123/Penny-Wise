package com.android.pennywise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    private ArrayList<String> expenseId, expenseName, expenseAmount, expenseDate, expenseTime, expenseCategory;



    CustomAdapter(Context context,
                  ArrayList<String> expenseId,
                  ArrayList<String> expenseName,
                  ArrayList<String> expenseAmount,
                  ArrayList<String> expenseDate,
                  ArrayList<String> expenseTime,
                  ArrayList<String> expenseCategory){

        this.context = context;
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseTime = expenseTime;
        this.expenseCategory = expenseCategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_expense_paid_card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.expenseId.setText(String.valueOf(expenseId.get(position)));
        holder.expenseName.setText(String.valueOf(expenseName.get(position)));
        holder.expenseAmount.setText(String.valueOf(expenseAmount.get(position)));
        holder.expenseDate.setText(String.valueOf(expenseDate.get(position)));
        holder.expenseTime.setText(String.valueOf(expenseTime.get(position)));
        holder.expenseCategory.setText(String.valueOf(expenseCategory.get(position)));
    }

    @Override
    public int getItemCount() {
        return expenseId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expenseId, expenseName, expenseAmount, expenseDate, expenseTime, expenseCategory;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            expenseId = itemView.findViewById(R.id.view_expenseId);
            expenseName = itemView.findViewById(R.id.view_expenseName);
            expenseAmount = itemView.findViewById(R.id.view_expense_paid_amount);
            expenseDate = itemView.findViewById(R.id.view_expenseDate);
            expenseTime = itemView.findViewById(R.id.view_expenseTime);
            expenseCategory = itemView.findViewById(R.id.view_category);

        }
    }
}
