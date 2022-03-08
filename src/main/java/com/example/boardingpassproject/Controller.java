package com.example.boardingpassproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    public String errorMessage;


    //onChange event handlers
    @FXML
    public void nameChange() {

    }

    @FXML 
    public void changeEmail() {

    }

    @FXML
    public void changePhoneNum() {

    }

    @FXML
    public void changeGender() {

    }

    @FXML
    public void changeAge() {

    }

    @FXML
    public void changeDestination() {

    }

    @FXML
    public void changeDepartureTime() {

    }
    //End of onChange Event handlers
    @FXML
    public void checkFormContents() {

        //if an item is wrong change error message text else submitForm()
        submitForm();
    }

    private void submitForm() {
        // compile contents into files and generate ticket
    }

    private String generateTicketNum () {
        //generate random ticket number that does not match another ticket num
        return "";
    }
}