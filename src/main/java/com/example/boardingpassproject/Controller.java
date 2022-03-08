package com.example.boardingpassproject;

import javafx.fxml.FXML;

public class Controller {
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