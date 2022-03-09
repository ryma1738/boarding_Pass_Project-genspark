package com.example.boardingpassproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Controller {

    @FXML
    public ImageView backgroundImg;
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public TextField ageField;
    @FXML
    public ComboBox genderBox;
    @FXML
    public DatePicker departDate;
    @FXML
    public ComboBox originBox;
    @FXML
    public ComboBox destinationBox;
    @FXML
    public ComboBox timeBox;
    @FXML
    public Label errorLabel;
    @FXML
    public Button ticketButton;
    

    
    
    

    public String errorMessage;
    public String name;
    public String email;
    public String phoneNumber;
    public String gender;
    public String age;
    public String departureDate;
    public String destinationName;
    public String departureTime;

    // storing cities we fly to
    ArrayList<String> cities = new ArrayList<>();
        
    // Use HashSet as our DataStructure to store all tickets generated because no duplicate values.
    public HashSet<String> allTicketsGenerated = new HashSet<>();

    
    public void initialize() {
        backgroundImg.setImage(new Image("sky.jpg"));
        populateCities();
        addCitiesToDrop(destinationBox);
        addCitiesToDrop(originBox);
        genderBox.getItems().addAll(
            "                     Male", "                    Female", "                    Other");
        
        //genderChoice.setItems(FXCollections.observableArrayList(
           // "                   Gender", "                     Male", "                    Female", "                    Other"));
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
        String current = phoneNumberField.getText();
        phoneNumber = createPhoneNumber(current);
        phoneChecker(phoneNumber);
        phoneNumberField.setText(phoneNumber);
        phoneNumberField.positionCaret(phoneNumber.length());

    }

    @FXML
    public void changeGender() {
        // Gets value from genderBox and type casts to string
        // Then removes all white space from combo box placeholder text
        gender = (String) genderBox.getValue();
        gender = gender.replaceAll("\\s", "");

    }

    @FXML
    public void changeAge() {
        age = ageField.getText();
    }

    @FXML
    public void changeDestination() {
        //destinationName = destinationField.getText();
    }

    @FXML
    public void changeDepartureTime() {
        //departureTime = departureField.getText();
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
        } else if (gender == null) {
            errorMessage = "You must choose a gender";
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
            String ticketData = "\n\t*******************" +
                    "\n\t| Boarding Ticket |" +
                    "\n\t*******************" +
                    "\n\tName: " + name +
                    ",\n\tEmail: " + email +
                    ",\n\tPhone Number: " + phoneNumber +
                    ",\n\tGender: " + gender +
                    ",\n\tAge: " + age +
                    ",\n\tDestination: " + destinationName +
                    ",\n\tDeparture Time: " + departureTime +
                    ",\n\tBoarding Pass ID: " + generateTicketNum();
            writer.write(ticketData);
            writer.close();
            allTicketsGenerated.add(ticketData);
            System.out.println("Ticket Successfully Generated");
            System.out.println(allTicketsGenerated);
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    private String generateTicketNum() {
        //generate random ticket number that does not match another ticket num
        return UUID.randomUUID().toString();
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

    public void populateCities() {
        cities.add("          New York, NY");
        cities.add("         Los Angeles, CA");
        cities.add("           Chicago, IL");
        cities.add("           Houston, TX");
        cities.add("           Phoenix, AZ");
        cities.add("         Philadelphia, PA");
        cities.add("         San Antonio, TX");
        cities.add("          San Diego, CA");
        cities.add("             Dallas, TX");
        cities.add("           San Jose, CA");
    }

    public void addCitiesToDrop(ComboBox drop) {
        for(String i : cities) {
            drop.getItems().add(i);
        }
    }




}