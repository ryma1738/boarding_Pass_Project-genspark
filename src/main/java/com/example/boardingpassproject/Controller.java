package com.example.boardingpassproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    public String errorMessage;
    @FXML
    private Label welcomeText;

    @FXML
    public void checkFormContents() {

        //if an item is wrong change error message text else submitForm()
        submitForm();
    }

    private void submitForm() {
        // compile contents into files and generate ticket
    }
}