package com.arifxdroid.employeedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by arif on 5/13/15.
 */
public class EmployeeDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "employeeInfo";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "employeeTable";
    public static final String ID_FIELD = "_ID";
    public static final String NAME_FIELD = "name";
    public static final String NUMBER_FIELD = "number";
    public static final String EMAIL_FIELD = "email";
    public static final String SALARY_FIELD = "salary";

    public static final String TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME_FIELD+" TEXT NOT NULL, "+NUMBER_FIELD+" TEXT, "+EMAIL_FIELD+" TEXT, "+SALARY_FIELD+" DOUBLE)";

    public EmployeeDB(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addEmployee(Employee employee){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_FIELD,employee.getName());
        values.put(NUMBER_FIELD,employee.getPhone());
        values.put(EMAIL_FIELD,employee.getEmail());
        values.put(SALARY_FIELD, employee.getSalary());
        long inserted =db.insert(TABLE_NAME, "" ,values);
        db.close();
        return inserted;
    }

    public ArrayList<Employee> getAllEmployee(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null,null, null,null,null);
        ArrayList<Employee> all = new ArrayList<Employee>();

        if (cursor!=null){

            if (cursor.getCount()>0){

                cursor.moveToFirst();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                    String name = cursor.getString(cursor.getColumnIndex(NAME_FIELD));
                    String number = cursor.getString(cursor.getColumnIndex(NUMBER_FIELD));
                    String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
                    double salary = cursor.getDouble(cursor.getColumnIndex(SALARY_FIELD));

                    Employee em = new Employee(name,email,number,salary,id);
                    all.add(em);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return all;

    }

    public Employee getAnEmployee(int id){

        Employee em = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where "+ID_FIELD+"='" + id + "'",null);

        if (cursor!=null){

            if (cursor.getCount()>0){

                cursor.moveToFirst();
                do {
                    int itemId = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                    String name = cursor.getString(cursor.getColumnIndex(NAME_FIELD));
                    String number = cursor.getString(cursor.getColumnIndex(NUMBER_FIELD));
                    String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
                    double salary = cursor.getDouble(cursor.getColumnIndex(SALARY_FIELD));

                    em = new Employee(name,email,number,salary,itemId);
                }while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return em;
    }



    public int deleteEmployee(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NAME, ID_FIELD+"=?", new String[]{""+id});
        db.close();
        return deleted;
    }

    public int updateEmployee(Employee emp, int id){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_FIELD, emp.getName());
        values.put(NUMBER_FIELD,emp.getPhone());
        values.put(EMAIL_FIELD,emp.getEmail());
        values.put(SALARY_FIELD, emp.getSalary());
        int updated = db.update(TABLE_NAME, values, ID_FIELD+"=?", new String[]{""+id});
        return  updated;
    }
}
