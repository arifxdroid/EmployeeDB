package com.arifxdroid.employeedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arif on 5/3/15.
 */
public class CustomAdapter extends ArrayAdapter<String>{

    Context context;
    ArrayList<Employee> emp;
    public CustomAdapter(Context context, ArrayList emp) {
        super(context, R.layout.custom_view, emp);
        this.context = context;
        //this.value = value;
        this.emp = emp;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_view,parent,false);
        TextView txtView = (TextView) rowView.findViewById(R.id.txtView);
        txtView.setText(emp.get(position).getName().toString());
        return rowView;
    }
}
