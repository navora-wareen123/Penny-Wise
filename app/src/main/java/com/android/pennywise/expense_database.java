package com.android.pennywise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class expense_database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "expense.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "expense_table";
    private static final String COLUMN_ID = "expense_id";
    private static final String COLUMN_NAME = "expense_name";
    private static final String COLUMN_AMOUNT = "expense_amount";
    private static final String COLUMN_DATE = "expense_date";
    private static final String COLUMN_TIME = "expense_time";
    private static final String COLUMN_STATUS = "expense_status";
    private static final String COLUMN_CATEGORY = "expense_category";


    public expense_database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_CATEGORY + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addExpense(String name, String amount, String date, String time, String status, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_CATEGORY, category);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //this cursor will contain all the data in our database table will we return in our readAllData method and it will be called in to where it will gonna be displayed the data

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}

//note::::: yung data pala for time, kasi diba pag sa unpaid di natin kukunin yung time, ang kukunin lng is yung date, paano kaya ang gagawin kung paano i sosolve yun?// solved!!!!!
//note::::: if yung paid ay umabot na sa 250 ang size, i dedelete na yung pinaka luma or yung pinaka unang expense na nailagay for memory management
