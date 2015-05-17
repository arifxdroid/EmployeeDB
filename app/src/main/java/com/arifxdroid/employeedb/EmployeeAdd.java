package com.arifxdroid.employeedb;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EmployeeAdd extends ActionBarActivity {

    EditText etName, etPhone, etEmail, etSalary;
    Button btnSave, btnUpdateValue;

    EmployeeDB database;
    public Employee em;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add);

        database = new EmployeeDB(this);

        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etSalary = (EditText)findViewById(R.id.etSalary);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnUpdateValue = (Button)findViewById(R.id.btnUpdateValue);


        Intent i = getIntent();
        int Id = i.getIntExtra("givenId", 0);
        if (Id > 0){

            em = database.getAnEmployee(Id);
            etName.setText(em.getName().toString());
            etPhone.setText(em.getPhone().toString());
            etEmail.setText(em.getEmail().toString());
            etSalary.setText(Double.toString(em.getSalary()));
        }

        btnUpdateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String number = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                double salary = Double.parseDouble(etSalary.getText().toString());

                Intent i = getIntent();
                int Id = i.getIntExtra("givenId", 0);
                em = new Employee(name,email,number,salary);
                database.updateEmployee(em,Id);
                Toast.makeText(getApplicationContext(),"Update success!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EmployeeAdd.this,MainActivity.class);
                startActivity(intent);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String number = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                double salary = Double.parseDouble(etSalary.getText().toString());

                em = new Employee(name,email,number,salary);
                long inserted = database.addEmployee(em);
                if (inserted == -1){
                    Toast.makeText(getApplicationContext(), "Data can't Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                }


                Intent i = new Intent(EmployeeAdd.this,MainActivity.class);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
