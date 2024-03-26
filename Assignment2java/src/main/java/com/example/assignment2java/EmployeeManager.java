package com.example.assignment2java;

import java.util.ArrayList;

public class EmployeeManager {

    private ArrayList<Employee> employeeList;

    public EmployeeManager(){ // constructor
        this.employeeList = new ArrayList<>();
    }

    void addEmployee(Employee newEmployee){
        employeeList.add(newEmployee); // add employee to array
    }
    void viewEmployees() { //views all
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName() +
                    " Salary: " + employee.getSalary() +
                    " Hourly Rate: " + employee.getHourlyPay());
        }
    }

    ArrayList<Employee> getEmployeeList(){
        return new ArrayList<>(employeeList);
    }
/*
    void updateEmployee(int employeeIndex, Employee newEmployee){
        if(employeeIndex >= 0 && employeeIndex < employeeList.size()){ //if the array has no employees then cannot work
            // and if less than the size then able to update
            employeeList.set(employeeIndex, newEmployee); //getting specific employee you want to update
            System.out.println("Employee updated");
        }
        else{
            System.out.println("Could not update employee");
        }
    }


 */
    public void deleteEmployee(int index) {
        if (index >= 0 && index < employeeList.size()) { //if index is greater than 0(employee exists)
            employeeList.remove(index); //removes employee from the list using that index
            System.out.println("Employee deleted");
        } else {
            System.out.println("Employee does not exist");
        }
    }

}

