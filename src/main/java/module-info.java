module com.example.boardingpassproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boardingpassproject to javafx.fxml;
    exports com.example.boardingpassproject;
}