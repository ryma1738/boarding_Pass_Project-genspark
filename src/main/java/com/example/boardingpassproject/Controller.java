package com.example.boardingpassproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneNumberField;
    //@FXML
    //public TextField genderField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField destinationField;
    @FXML
    public TextField departureField;
    @FXML
    public Label errorLabel;
    @FXML
    public Button ticketButton;
    @FXML
    public ImageView backgroundImg;
    @FXML
    public ComboBox genderBox;
    @FXML
    public ChoiceBox genderChoice;



    public String errorMessage;
    public String name;
    public String email;
    public String phoneNumber;
    public String genders;
    public String age;
    public String departureDate;
    public String destinationName;
    public String departureTime;


    public void initialize() {
        genderBox.getItems().addAll(
            "                     Male", "                    Female", "                    Other");
        backgroundImg.setImage(new Image("sky.jpg"));

        //genderChoice.setItems(FXCollections.observableArrayList(
           // "                   Gender", "                     Male", "                    Female", "                    Other"));
    }

    //onChange event handlers

    @FXML
    public void changeName() {
        name = nameField.getText();
        System.out.println(name);
    }

    @FXML 
    public void changeEmail() {
        email = emailField.getText();
    }

    @FXML
    public void changePhoneNum() {
        phoneNumber = phoneNumberField.getText();
        System.out.println(phoneNumber);

        String current = phoneNumberField.getText();
        phoneNumber = createPhoneNumber(current);
        phoneChecker(phoneNumber);
        phoneNumberField.setText(phoneNumber);
        phoneNumberField.positionCaret(phoneNumber.length());

    }

    @FXML
    public void changeGender() {
        //genders = genderField.getText();
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
        errorLabel.setText(errorMessage);
        errorMessage = "";
        // if error occurs set error msg visibility
        Boolean error = false;
        if (name.length() < 1) {
            errorMessage = "You must enter a name";
            errorLabel.setVisible(true);
            errorLabel.setManaged(true);
            return;
        } else if (!email.matches("/.+@.+\\..+/") || email.length() < 8) {
            errorMessage = "You must enter a valid email address";
            errorLabel.setVisible(true);
            errorLabel.setManaged(true);
            return;
        } else if (!phoneChecker(phoneNumber)) {
            errorMessage = "You must enter a valid phone number";
            errorLabel.setVisible(true);
            errorLabel.setManaged(true);
            return;
        }
        submitForm();
    }

    private void submitForm() {
        // compile contents into files and generate ticket
    }

    private String generateTicketNum() {
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

    private Boolean phoneChecker(String phoneNumber) {
        if (phoneNumberField.getText().matches("(?:\\d{3}-){2}\\d{4}")) {
            return true;
        }else {
            return false;
        }
    }

}