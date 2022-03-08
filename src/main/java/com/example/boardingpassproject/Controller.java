package com.example.boardingpassproject;

import javafx.fxml.FXML;
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
        String current = phoneNumberField.getText();
        phoneNumber = createPhoneNumber(current);
        phoneNumberField.setText(phoneNumber);
        phoneNumberField.positionCaret(phoneNumber.length());
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

    private String createPhoneNumber(String value) {
        if (value.length() == 3) {
            if (phoneNumber.charAt(phoneNumber.length() - 1) != '-') {
                return value + "-";
            }
        } else if (value.length() == 7) {
            if (phoneNumber.charAt(phoneNumber.length() - 1) != '-') {
                return value + "-";
            }
        }
        return value;
    }

}