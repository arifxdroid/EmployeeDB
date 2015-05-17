package com.arifxdroid.employeedb;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EmployeeView extends ActionBarActivity {
    TextView txtNameView;
    TextView txtNumberView;
    TextView txtEmailView;
    TextView txtSalaryView;
    Button btnUpDate;
    Button btnDelete;
    Employee em;
    EmployeeDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        db = new EmployeeDB(this);
        txtNameView= (TextView) findViewById(R.id.txtNameView);
        txtNumberView= (TextView) findViewById(R.id.txtNumberView);
        txtEmailView= (TextView) findViewById(R.id.txtEmailView);
        txtSalaryView= (TextView) findViewById(R.id.txtSalaryView);
        btnUpDate= (Button) findViewById(R.id.btnUpdate);
        btnDelete= (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        int Id = i.getIntExtra("idKey", 0);
        String givenName =i.getExtras().getString("name", "");
        String givenNumber = i.getExtras().getString("number", "");
        String givenEmail = i.getExtras().getString("email", "");
        double givenSalary = i.getDoubleExtra("salary", 0);
        Toast.makeText(getApplicationContext(),"id:"+Id, Toast.LENGTH_LONG).show();

        txtNameView.setText(givenName);
        txtNumberView.setText(givenNumber);
        txtEmailView.setText(givenEmail);
        txtSalaryView.setText(Double.toString(givenSalary));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                int Id = i.getIntExtra("idKey", 0);

                int deleted = db.deleteEmployee(Id);
                if (deleted >0){
                    Toast.makeText(getApplicationContext(), "Successfully Delete!",Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Delete not success!!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EmployeeView.this, MainActivity.class);
                startActivity(intent);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
        btnUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                int Id = i.getIntExtra("idKey", 0);
                i= new Intent(EmployeeView.this,EmployeeAdd.class);
                i.putExtra("givenId",Id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_upDate) {
            return true;
        }
        else if (id == R.id.action_Delete){

            Intent i = getIntent();
            int Id = i.getIntExtra("idKey", 0);

            int deleted = db.deleteEmployee(Id);
            if (deleted >0){
                Toast.makeText(getApplicationContext(), "Successfully Delete!",Toast.LENGTH_SHORT ).show();
            }else {
                Toast.makeText(getApplicationContext(), "Delete not success!!", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(EmployeeView.this, MainActivity.class);
            startActivity(intent);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
