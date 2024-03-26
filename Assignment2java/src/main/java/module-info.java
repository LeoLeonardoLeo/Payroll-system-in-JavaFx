module com.example.assignment2java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment2java to javafx.fxml;
    exports com.example.assignment2java;
}