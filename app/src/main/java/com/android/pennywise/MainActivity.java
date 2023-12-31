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
    TabItem ti_paid,ti_unpaid;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding the recycler view inside of the main method class where is the id located

        //finding tabItems
        ti_paid = findViewById(R.id.paid_expense_tab);
        ti_unpaid = findViewById(R.id.unpaid_expense_tab);

        //initializing the tab layout and view pager
        tabLayout = findViewById(R.id.expense_nav);
        viewPager = findViewById(R.id.expenses_container);

        //setting up the tablayout class into the view pager class
        tabLayout.setupWithViewPager(viewPager);

        //initializing the vpadapter class and has 2 argument
        VPadapter vPadapter = new VPadapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        //adding fragment to the viewpager and giving each a title
        vPadapter.addFragment(new paid(), "Paid");
        vPadapter.addFragment(new unpaid(), "Unpaid");

        //initializing and setting up the Custom adapter to the viewPager
        viewPager.setAdapter(vPadapter);

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
    }
}