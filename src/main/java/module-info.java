module com.example.boardingpassproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boardingpassproject to view.fxml;
    exports com.example.boardingpassproject;
}