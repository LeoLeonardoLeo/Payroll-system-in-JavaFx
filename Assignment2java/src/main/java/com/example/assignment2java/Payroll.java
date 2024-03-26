package com.example.assignment2java;
import java.io.*;

public class Payroll {

    private double salary;
    private double deductions;
    private double totalPay;
    public Payroll(double salary, double deductions) {
        this.salary = salary;
        this.deductions = deductions;
        this.totalPay = calculateNetPay();
    }

    private double calculateNetPay() {
        return salary - deductions; //employees net pay
    }
    public double getSalary() {
        return salary;
    }

    // Serialize payroll data to a file
    public void serializePayroll(Payroll payroll) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("payrollData.ser"))) {
            out.writeObject(payroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize payroll data from the file
    public static Payroll deserializePayroll() {
        Payroll payroll = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("payrollData.ser"))) {
            payroll = (Payroll) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return payroll;
    }


    @Override
    public String toString() {
        return "Payroll{" +
                "salary=" + salary +
                ", deductions=" + deductions +
                ", pay=" + totalPay +
                '}';
    }
}


