package com.android.pennywise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;

import java.util.Calendar;

public class expense_adder extends AppCompatActivity {

    //declaring objects and variables
    TextView viewDate;
    DatePicker datePicker;
    ImageButton btnDatePicker;
    RadioButton rb_status, paid, unpaid;
    RadioGroup rg_status;
    Spinner sp_category;
    LinearLayout dateCont;
    Button btn_close, btn_ok;
    EditText name, amount;
    String s_name,s_amount, status, category, s_time, s_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_adder);

        //finding the objects in layout
        viewDate = findViewById(R.id.lbl_date);
        datePicker = new DatePicker(this);
        btnDatePicker = findViewById(R.id.btn_datePicker);
        paid = findViewById(R.id.rb_paid);
        unpaid = findViewById(R.id.rb_unpaid);
        rg_status = findViewById(R.id.rg_expenseStatus);
        sp_category = findViewById(R.id.sp_category);
        dateCont = findViewById(R.id.due_dateContainer);
        btn_ok = findViewById(R.id.btn_expenseOk);
        btn_close = findViewById(R.id.btn_expenseClose);
        name = findViewById(R.id.et_expenseName);
        amount = findViewById(R.id.et_expenseAmount);


        //set default values
        status = "Paid";
        category = "Food and drinks";

        // Set event on the button Pick a Date
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //set event when clicking the paid radio button
        paid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //when the paid radio button was chosen, the Selected date and image date button will disappear
                dateCont.setVisibility(View.VISIBLE);
            }
        });

        //set event when clicking the unpaid radio button
        unpaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //when the unpaid radio button was chosen, the Selected date and image date button will appear
                dateCont.setVisibility(View.INVISIBLE);
            }
        });

        //set event when clicking the close button
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigating to the expense section where we can see the paid expenses and unpaid expenses
                Intent intent = new Intent(expense_adder.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //set event when clicking the ok button
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GETTING THE VALUES THAT FILLED UP BY THE USER

                //get the value/ name of the radio button who is checked
                int id =rg_status.getCheckedRadioButtonId();
                rb_status = findViewById(id);
                status = rb_status.getText().toString();

                //get the value/ name of the spinner who is selected
                ArrayAdapter<CharSequence> sp_adapter;
                sp_adapter = ArrayAdapter.createFromResource(expense_adder.this, R.array.expense_categories, android.R.layout.simple_spinner_item);
                sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_category.setAdapter(sp_adapter);
                sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        category = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //get the value/ name of the editText expense name
                //expense name is the hint in the layout
                //storing to the s_name String variable
                s_name = name.getText().toString();

                //checking if the length of the expense name is exceeded in 24 then trow a feedback
                if(s_name.length() > 24){
                    Toast.makeText(expense_adder.this, "Expense Name is too long", Toast.LENGTH_SHORT).show();
                }else{

                    //get the value/ amount of the editText expense amount
                    //expense amount is the hint in the layout
                    s_amount = amount.getText().toString();

                    if(status.equalsIgnoreCase("paid")){

                        //GETTING CURRENT DATE
                        //instantiating the Date class
                        //as well as getting the current date and time
                        Date date = new Date();
                        //formatting the date as MM/dd/yy ex. 12/31/99
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
                        //storing to the string variable
                        s_date = dateFormat.format(date);

                        //GETTING CURRENT TIME
                        Date currTime = new Date();
                        //formatting
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:ss");
                        //storing to the string variable
                        s_time = timeFormat.format(currTime);

                        //STORING THE DATA IN DATABASE

                        //instantiating the database class
                        expense_database myDb = new expense_database(expense_adder.this);

                        //using the object of expense_database, we will now inserting the data in database
                        myDb.addExpense(s_name,s_amount,s_date,s_time,status,category);
                        Toast.makeText(expense_adder.this, "Expense Add Successfully!", Toast.LENGTH_SHORT).show();

                        //after making an query, go back to the activity_main layout
                        Intent intent = new Intent(expense_adder.this, MainActivity.class);
                        startActivity(intent);



                    }else if(status.equalsIgnoreCase("unpaid")){

                        //get the user picked date from date picker dialog
                        //storing them in string variable s_date
                        s_date = viewDate.getText().toString();

                        //GETTING CURRENT TIME
                        Date currTime = new Date();
                        //formatting
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:ss");
                        //storing to the string variable
                        s_time = timeFormat.format(currTime);

                        //instantiating the class name and creating an object named myDb
                        expense_database myDb = new expense_database(expense_adder.this);

                        //using the object of expense_database, we will now inserting the data in database
                        myDb.addExpense(s_name,s_amount,s_date,s_time,status,category);
                        Toast.makeText(expense_adder.this, "Expense Add Successfully!", Toast.LENGTH_SHORT).show();

                        //after making an query, it will go back to the activity_main layout
                        Intent intent = new Intent(expense_adder.this, MainActivity.class);
                        startActivity(intent);
                        //note:::::the time will then be updated if the status is changed in paid once the user check on checkbox
                    }
                }
            }
        });
    }

    //creating the calendar date picker
    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();

        // Initialize DatePicker with the current date
        DatePicker datePicker = new DatePicker(this);
        datePicker.init(
                c.get(Calendar.YEAR),//get current year
                c.get(Calendar.MONTH),//get current month
                c.get(Calendar.DAY_OF_MONTH),//get current date
                null
        );

        // Create AlertDialog with the DatePicker as its view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(datePicker);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the selected date from DatePicker
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                // Set the selected date in the TextView
                viewDate.setText(day + "/" + (month + 1) + "/" + year);
            }
        });
        builder.setNegativeButton("Cancel", null);

        // Show the AlertDialog
        builder.show();
    }
}