package com.android.pennywise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class statistics extends AppCompatActivity {
    Button btn_finance, btn_statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //going to the finance section
        btn_finance = findViewById(R.id.btn_finance);
        btn_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(statistics.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //going to the statistics section
        btn_statistics = findViewById(R.id.btn_statistics);
        btn_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(statistics.this, statistics.class);
                startActivity(intent);
            }
        });
    }
}