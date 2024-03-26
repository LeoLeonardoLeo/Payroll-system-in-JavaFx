package com.example.assignment2java;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    private EmployeeManager employeeManager;
    private Stage stage;

    @Override
    public void start(Stage firstStage) throws IOException {
        employeeManager = new EmployeeManager(); //initialize new employee

        stage = firstStage;
        stage.setTitle("HR Management");

        Scene menuScene = createMenuScene();

        stage.setScene(createMenuScene());
        stage.show();
    }
    //create employees
    private Scene createEmployeeScene() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(250)); //size of page

        Label title = new Label("HR Management form");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        // style
        title.setStyle("-fx-text-fill: #1f3a5c;");

        Label firstName = new Label("Enter first name: ");
        firstName.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label lastName = new Label("Enter last name: ");
        lastName.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label hourlyPay = new Label("Enter employee's hourly wage: ");
        hourlyPay.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label hoursWorked = new Label("Enter how many hours worked: ");
        hoursWorked.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label bonuses = new Label("Enter bonuses: ");
        bonuses.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label deductions = new Label("Enter deductions: ");
        deductions.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        // css label
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #1f3a5c;";

        firstName.setStyle(labelStyle);
        lastName.setStyle(labelStyle);
        hourlyPay.setStyle(labelStyle);
        hoursWorked.setStyle(labelStyle);
        bonuses.setStyle(labelStyle);
        deductions.setStyle(labelStyle);

        TextField fname = new TextField();
        TextField lname = new TextField();
        TextField wage = new TextField();
        TextField hrWorked = new TextField();
        TextField bonuse = new TextField();
        TextField deduction = new TextField();

        // style for the boxes
        String inputStyle = "-fx-background-color: #ffffff; -fx-border-color: #3f3a7a; -fx-font-size: 16px;";
        fname.setStyle(inputStyle);
        lname.setStyle(inputStyle);
        wage.setStyle(inputStyle);
        hrWorked.setStyle(inputStyle);
        bonuse.setStyle(inputStyle);
        deduction.setStyle(inputStyle);

        //layout
        //title
        gridPane.add(title, 0, 0, 2, 1);
        //first name
        gridPane.add(firstName, 0, 1);
        gridPane.add(fname, 1, 1);
        //last name
        gridPane.add(lastName, 0, 2);
        gridPane.add(lname, 1, 2);
        //wage
        gridPane.add(hourlyPay, 0, 3);
        gridPane.add(wage, 1, 3);
        //Hours worked
        gridPane.add(hoursWorked, 0, 4);
        gridPane.add(hrWorked, 1, 4);
        //Bonus
        gridPane.add(bonuses, 0, 5);
        gridPane.add(bonuse, 1, 5);
        //deductions
        gridPane.add(deductions, 0, 6);
        gridPane.add(deduction, 1, 6);




        //add employee button
        Button btn = new Button();
        btn.setText("Add Employee");
        btn.setAlignment(Pos.CENTER);
        btn.setStyle("-fx-background-color: #1f3a5c; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        btn.setPadding(new Insets(5));
        gridPane.add(btn, 1, 7);

        //back to menu button
        Button backToMenuBtn = new Button("Back to Menu");
        backToMenuBtn.setAlignment(Pos.CENTER);
        backToMenuBtn.setPadding(new Insets(5));

        // button css
        backToMenuBtn.setStyle("-fx-background-color: #1f3a77; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        btn.setStyle("-fx-background-color: #1f3a5c; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        gridPane.add(backToMenuBtn, 0, 7);


        Label success = new Label(); // showing that code worked
        gridPane.add(success, 1, 8);


        btn.setOnAction(e -> {
            String FirstName = fname.getText().trim();
            String LastName = lname.getText().trim();
            double HourlyPay = Double.parseDouble(wage.getText().trim());
            double HoursWorked = Double.parseDouble(hrWorked.getText().trim());
            double Bonus = Double.parseDouble(bonuse.getText().trim());
            double Deduction = Double.parseDouble(deduction.getText().trim());

            if (FirstName.isEmpty()){ //if first name is empty
                Alert a = new Alert(Alert.AlertType.WARNING, "First name cannot be empty");
                a.showAndWait();
            }
            else if (LastName.isEmpty()){ //if last name is empty
                Alert a = new Alert(Alert.AlertType.WARNING, "Last name cannot be empty");
                a.showAndWait();
            }
            else { //if all user input is added this will run (default)

                // Create an instance of Employee
                Employee newEmployee = new Employee(FirstName, LastName, HourlyPay, HoursWorked, Bonus, Deduction);


                employeeManager.addEmployee(newEmployee); //add employee into array
                //after employee is added, clears the employee from the text to allow
                //easy use to add more
                fname.clear();
                lname.clear();
                wage.clear();
                hrWorked.clear();
                bonuse.clear();
                deduction.clear();

                success.setText("Employee added");

            }
        });
        backToMenuBtn.setOnAction(e -> stage.setScene(createMenuScene())); //if back to menu is clicked, returns to menu
        return new Scene(gridPane);
    }

    //view employees
    private Scene createViewEmpScene(){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0)); //set padding to zero


        Text empList = new Text("Employee List");
        empList.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(empList);

        ListView<String> viewAllEmployees = new ListView<>(); //array of employee into

        viewAllEmployees.setStyle("-fx-border-color: transparent;");


        viewAllEmployees.setItems(FXCollections.observableArrayList(
                employeeManager.getEmployeeList().stream()
                        .map(employee -> employee.getFirstName() + " " + employee.getLastName() +
                                " Hourly Pay: " + employee.getHourlyPay() +
                                " Hours Worked: " + employee.getHoursWorked() +
                                " Salary: " + employee.getSalary()).collect(Collectors.toList())));

        //^^ in this code, to view all employees it gets all info from the array
        // list (observableArrayList), getEmployeeList() code is used and all other
        // needed getters to be outputted is used


        //size of box
        viewAllEmployees.setPrefWidth(500); //left to right size
        viewAllEmployees.setPrefHeight(500); //up and down size
        vbox.getChildren().add(viewAllEmployees);


        Button backToMenuBtn = new Button("Back to Menu");
        backToMenuBtn.setAlignment(Pos.CENTER);
        backToMenuBtn.setPadding(new Insets(5));
        backToMenuBtn.setStyle("-fx-background-color: #1f3a77; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        vbox.getChildren().add(backToMenuBtn);

       //back to menu button if clicked return to main menu
        backToMenuBtn.setOnAction(e -> stage.setScene(createMenuScene()));

        return new Scene(vbox);
    }

    //all payroll stuff
    private Scene payrollProcessing() {
        VBox payrollLayout = new VBox();
        payrollLayout.setAlignment(Pos.TOP_CENTER);
        payrollLayout.setSpacing(10);
        payrollLayout.setPadding(new Insets(20));

        Text payrollTitle = new Text("Payroll Processing");
        payrollTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        payrollLayout.getChildren().add(payrollTitle);

        //gets employee list
        ArrayList<Employee> employees = employeeManager.getEmployeeList();

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i); //gets each employee

            // set bonus and overtime
            double bonus = employee.getBonus();
            double overtime = employee.getOvertime();

            // calculate total salary including bonus and overtime
            double totalSalary = employee.getSalary() + bonus + overtime;

            // deduct taxes
            double tax = 0.1; // random tax rate
            double deductions = employee.getDeductions(); // gets deductions from employee

            // the net pay after taxes and deductions
            double netPay = totalSalary - (totalSalary * tax) - deductions;

            // display employee information and payroll details
            Label employeeInfo = new Label(employee.toString());
            Label payrollDetails = new Label(
                    "Total Salary: " + totalSalary +
                            "\nOvertime: " + overtime +
                            "\n-------------" +
                            "\nTax: " + (totalSalary * tax) +
                            "\nDeductions: " + deductions +
                            "\nNet Pay: " + netPay);

            VBox payrollInfo = new VBox(employeeInfo, payrollDetails);
            payrollInfo.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-padding: 10px;");
            payrollLayout.getChildren().add(payrollInfo);
        }

        Button backToMenuBtn = new Button("Back to Menu");
        backToMenuBtn.setAlignment(Pos.CENTER);
        backToMenuBtn.setPadding(new Insets(5));
        backToMenuBtn.setStyle("-fx-background-color: #1f3a77; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        payrollLayout.getChildren().add(backToMenuBtn);

        //main menu button
        backToMenuBtn.setOnAction(e -> stage.setScene(createMenuScene()));

        return new Scene(payrollLayout);
    }
    //delete employee
    private Scene deleteEmp(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(250));

        Label title = new Label("HR Management form");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label index = new Label("Enter the index of employee you'd like to delete: ");
        index.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        TextField indexof = new TextField();

        gridPane.add(index, 0, 1);
        gridPane.add(indexof, 1, 1);

        Button btn = new Button();
        btn.setText("Delete Employee");
        btn.setStyle("-fx-background-color: #1f3a77; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        btn.setAlignment(Pos.CENTER);
        btn.setPadding(new Insets(5));
        gridPane.add(btn, 1, 10);

        //back to menu button
        Button backToMenuBtn = new Button("Back to Menu");
        backToMenuBtn.setAlignment(Pos.CENTER);
        backToMenuBtn.setPadding(new Insets(5));
        backToMenuBtn.setStyle("-fx-background-color: #1f3a77; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");

        gridPane.add(backToMenuBtn, 0, 10);

        Label success = new Label(); // label to display the status message
        gridPane.add(success, 1, 8);

        //if button clicked
        btn.setOnAction(e -> {
            String indexText = indexof.getText().trim();

            try {
                int numIndex = Integer.parseInt(indexText);

                indexof.clear();

                success.setText("Employee Deleted");

            } catch (NumberFormatException p) {
                // handling the case where the entered value is not a valid integer
                Alert alert = new Alert(Alert.AlertType.WARNING, "Index must be a valid integer.");
                alert.showAndWait();
            }
        });
        backToMenuBtn.setOnAction(e -> stage.setScene(createMenuScene()));
        return new Scene(gridPane);
    }

    //main menu scene
    private Scene createMenuScene() {
        //menu stuff
        VBox menuLayout = new VBox();
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setSpacing(20);

        Text menuTitle = new Text("Menu");
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuLayout.getChildren().add(menuTitle);

        //add menu items HERE

        //to go into add customer
        Button addEmpBtn = new Button("Add Employee");
        addEmpBtn.setOnAction(e -> stage.setScene(createEmployeeScene()));
        menuLayout.getChildren().add(addEmpBtn);
        addEmpBtn.setStyle("-fx-background-color: #1f3777; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");

        //Delete employees
        Button deleteEmployee = new Button("Delete Employee");
        deleteEmployee.setOnAction(e -> stage.setScene(deleteEmp()));
        menuLayout.getChildren().add(deleteEmployee);
        deleteEmployee.setStyle("-fx-background-color: #1f3777; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");

        //view employees
        Button viewEmpsBtn = new Button("View Employees");
        viewEmpsBtn.setOnAction(e -> stage.setScene(createViewEmpScene()));
        menuLayout.getChildren().add(viewEmpsBtn);
        viewEmpsBtn.setStyle("-fx-background-color: #1f3777; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");

        //payroll processing
        Button payrollProcess = new Button("Payroll Processing");
        payrollProcess.setOnAction(e -> stage.setScene(payrollProcessing()));
        menuLayout.getChildren().add(payrollProcess);
        payrollProcess.setStyle("-fx-background-color: #1f3777; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");

        //scene size
        return new Scene(menuLayout, 800, 600);
    }

    public static void main(String[] args) {
        launch();
    }
}