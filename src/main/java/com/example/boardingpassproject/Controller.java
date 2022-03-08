package com.example.boardingpassproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public TextField genderField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField destinationField;
    @FXML
    public TextField departureField;

    public String errorMessage;

    public String name;

    public String email;

    public String phoneNumber;

    public String genders;

    public String age;

    public String departureDate;

    public String destinationName;

    public String departureTime;





    //onChange event handlers
    @FXML
    public void changeName() {
        name = nameField.getText();
    }

    @FXML 
    public void changeEmail() {
        email = emailField.getText();
    }

    @FXML
    public void changePhoneNum() {
        phoneNumber = phoneNumberField.getText();
    }

    @FXML
    public void changeGender() {
        genders = genderField.getText();
    }

    @FXML
    public void changeAge() {
        age = ageField.getText();
    }

    @FXML
    public void changeDestination() {
        destinationName = destinationField.getText();
    }

    @FXML
    public void changeDepartureTime() {
        departureTime = departureField.getText();
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