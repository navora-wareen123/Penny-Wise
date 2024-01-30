package com.android.pennywise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_finance, btn_statistics;
    RecyclerView expense_viewer;
    expense_database myDb;
    ArrayList<String> expense_id, expense_name, expense_amount, expense_category, expense_status, expense_date, expense_time;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding the recycler view inside of the main method class where is the id located


        myDb = new expense_database(MainActivity.this);
        expense_id = new ArrayList<>();
        expense_name = new ArrayList<>();
        expense_amount = new ArrayList<>();
        expense_category = new ArrayList<>();
        expense_status = new ArrayList<>();
        expense_date = new ArrayList<>();
        expense_time = new ArrayList<>();
        expense_viewer = findViewById(R.id.expense_container);

        //show the expense_adder
        //expense_adder is a layout where you can fill up a form to add new paid or unpaid expenses
        Button btn_expenseAdder = findViewById(R.id.btn_expense_adder);
        btn_expenseAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, expense_adder.class);
                startActivity(intent);
            }
        });

        //going to the finance section
        btn_finance = findViewById(R.id.btn_finance);
        btn_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //going to the statistics section
        btn_statistics = findViewById(R.id.btn_statistics);
        btn_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, statistics.class);
                startActivity(intent);
            }
        });
        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, expense_id, expense_name, expense_amount, expense_date, expense_time, expense_status, expense_category);
        expense_viewer.setAdapter(customAdapter);
        expense_viewer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                expense_id.add(cursor.getString(0));
                expense_name.add(cursor.getString(1));
                expense_amount.add(cursor.getString(2));
                expense_date.add(cursor.getString(3));
                expense_time.add(cursor.getString(4));
                expense_status.add(cursor.getString(5));
                expense_category.add(cursor.getString(6));
            }
        }
    }
}