package com.example.boardingpassproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    @FXML
    public Label errorLabel;
    @FXML
    public Button ticketButton;
    @FXML
    public Image backgroundImg;



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
        //backgroundImg.setImage(new Image("sky.jpg"));
    }

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

        String current = phoneNumberField.getText();
        phoneNumber = createPhoneNumber(current);
        phoneChecker(phoneNumber);
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
        errorMessage = "";
        // if error occurs set error msg visibility
        Boolean error = false;
        if (name == null) {
            errorMessage = "You must enter a name";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (email == null || !App.validate(email) || email.length() < 8) {
            errorMessage = "You must enter a valid email address";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (!phoneChecker(phoneNumber)) {
            errorMessage = "You must enter a valid phone number";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        }
        submitForm();
    }

    public void submitForm() {
        // compile contents into files and generate ticket
        createTicket();
        writeOverTicket();
        errorLabel.setVisible(false);
    }

    private void createTicket() {
        try {
            File ticket = new File("Your_Boarding_Ticket.txt");
            if (ticket.createNewFile()) {
                System.out.println("File created: " + ticket.getName());
            } else {
                System.out.println("File already exists: " + ticket.getName());
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    private void writeOverTicket() {
        try {
            FileWriter writer = new FileWriter("Your_Boarding_Ticket.txt");
            writer.write("\t*******************" +
                    "\n\t| Boarding Ticket |" +
                    "\n\t*******************" +
                    "\n\tName: " + name +
                    ",\n\tEmail: " + email +
                    ",\n\tPhone Number: " + phoneNumber +
                    ",\n\tGender: " + genders +
                    ",\n\tAge: " + age +
                    ",\n\tDestination: " + destinationName +
                    ",\n\tDeparture Time: " + departureTime);
            writer.close();
            System.out.println("Ticket Successfully Generated");
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
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
        } else {
            return false;
        }
    }


}