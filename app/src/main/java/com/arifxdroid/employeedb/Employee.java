package com.arifxdroid.employeedb;

/**
 * Created by arif on 5/13/15.
 */
public class Employee {

    String name;
    String email;
    String phone;
    double salary;
    int id;

    public Employee(String name, String email, String phone, double salary, int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.id = id;
    }

    public Employee(String name, String email, String phone, double salary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    public Employee(){


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){

        return "Name: "+name+"."+"  "+"Phone No: "+phone+"\n"+"Email: "+email+". "+"Salary: "+salary+"\n";
    }
}
