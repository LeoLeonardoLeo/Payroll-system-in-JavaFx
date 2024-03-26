package com.example.assignment2java;
import java.io.*;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private double hourlyPay;
    private double hoursWorked;
    private double bonus;
    private double overtime;
    private double deductions;
    private static int ID = 1;
    private int empId; // unique ID for each employee
    private static int incrimentId = 1;

    static private Payroll salary; //gets salary from payroll class
    public Employee(String firstName, String lastName, double hourlyPay, double hoursWorked, double bonus, double deductions)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        this.bonus = bonus;
        this.overtime = overtime;
        this.deductions = deductions;
        this.empId = incrimentId;
        incrimentId++;
    }

    // Serialize employee data to a file
    public void serializeEmployee(Employee employee) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employeeData.ser"))) {
            out.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Deserialize employee data from the file
    public static Employee deserializeEmployee() {
        Employee employee = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employeeData.ser"))) {
            employee = (Employee) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public double getHourlyPay() {
        return hourlyPay;
    }
    public double getHoursWorked(){
        return hoursWorked;
    }
    public double getBonus() {
        return bonus;
    }
    public double getOvertime(){
        return overtime;
    }
    public double getDeductions(){
        return deductions;
    }
    //setter
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    /*  public double getSalary() {
          return hourlyPay * hoursWorked; //what the person gets paid (hours they worked times wage
      }
      */
    public double getSalary() {
        double fourtyHoursWorked = Math.min(hoursWorked, 40); // regular hours up to 40 per week
        double overtime = Math.max(hoursWorked - 40, 0); // overtime hours over 40 a week

        //
        double regularPay = fourtyHoursWorked * hourlyPay; //what the person gets paid (hours they worked times wage

        // pay for overtime (double hourly pay)
        double overtimePay = overtime * (hourlyPay * 2);

        // salary + regular and overtime pay
        return regularPay + overtimePay;
    }

    @Override
    public String toString() {
        return "Name: " +  firstName + " " + lastName +
                " \nHourly Pay: " + hourlyPay +
                " \nHours Worked: " + hoursWorked +
                " \nSalary: " + getSalary() +
                "\n-------------" +
                " \nBonus: " + bonus;
    }


}
