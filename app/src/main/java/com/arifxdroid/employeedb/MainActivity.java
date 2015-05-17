package com.arifxdroid.employeedb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView lstView;
    EmployeeDB database;
    ArrayList<Employee> arrayListEmployee;
    ArrayList<String> allEmployee;


    //TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new EmployeeDB(this);

        allEmployee = new ArrayList<String>();

        arrayListEmployee = database.getAllEmployee();

        for (int i = 0; i> arrayListEmployee.size(); i++){

            allEmployee.add(arrayListEmployee.get(i).toString());
        }

        lstView = (ListView)findViewById(R.id.listView);
        //myAdapter = new ArrayAdapter<String>(this,R.layout.custom_list,R.id.textView,allEmployee);
        final CustomAdapter customAdapter = new CustomAdapter(this,arrayListEmployee);
        lstView.setAdapter(customAdapter);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int item_id = arrayListEmployee.get(position).getId();
                String name = arrayListEmployee.get(position).getName();
                String email = arrayListEmployee.get(position).getEmail();
                String number = arrayListEmployee.get(position).getPhone();
                double salary = arrayListEmployee.get(position).getSalary();




                Intent i = new Intent(MainActivity.this,EmployeeView.class);
                i.putExtra("idKey", item_id);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("number", number);
                i.putExtra("salary", salary);
                startActivity(i);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                finish();
            }
        });


//        txtShow = (TextView)findViewById(R.id.txtShow);
//
//        for (int i = 0; i>allEmployee.size(); i++){
//
//            txtShow.setText(allEmployee.get(i));
//        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent i = new Intent(this, EmployeeAdd.class);
            startActivity(i);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            finish();
            return true;
        }
        else if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
