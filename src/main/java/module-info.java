module com.example.supplychain22dec {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychain22dec to javafx.fxml;
    exports com.example.supplychain22dec;
}