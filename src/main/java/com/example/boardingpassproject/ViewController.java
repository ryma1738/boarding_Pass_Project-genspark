package com.example.boardingpassproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ViewController {
    public static Stage prevStage;

    public static void setPrevStage(Stage stage) {
        prevStage = stage;
    }


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
    public ComboBox<String> genderBox;
    @FXML
    public DatePicker departDate;
    @FXML
    public ComboBox<String> originBox;
    @FXML
    public ComboBox<String> destinationBox;
    @FXML
    public ComboBox<String> timeBox;
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
    public String origin;
    public String destination;
    public String departureTime;
    private  static String ticketData;


    // storing cities we fly to
    ArrayList<String> cities = new ArrayList<>();

    // Use HashSet as our DataStructure to store all tickets generated because no duplicate values.
    public static HashSet<String> allTicketsGenerated = new HashSet<>();

    public void initialize() {
        backgroundImg.setImage(new Image("sky.jpg"));
        populateCities();
        addCitiesToDrop(destinationBox);
        addCitiesToDrop(originBox);
        genderBox.getItems().addAll("Male", "Female", "Other");
        timeBox.getItems().addAll("06:00 am", "10:30 am", "03:00 pm", "07:30 pm", "12:00 am");

    }

    // Create scene two

    public void switchToScene2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view-2.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ticket Generator");
        stage.getIcons().add(new Image("plane.png"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        prevStage.close();
        stage.show();
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
        phoneNumber = Utils.createPhoneNumber(current, phoneNumber);
        Utils.phoneChecker(phoneNumber);
        phoneNumberField.setText(phoneNumber);
        phoneNumberField.positionCaret(phoneNumber.length());
    }

    @FXML
    public void changeGender() {
        gender = (String) genderBox.getValue();

    }

    @FXML
    public void changeAge() {
        age = ageField.getText();
    }

    @FXML
    public void changeOrigin() {
        origin = (String) originBox.getValue();

    }

    @FXML
    public void changeDestination() {
        destination = (String) destinationBox.getValue();

    }

    @FXML
    public void changeDepartureTime() {
        departureTime = (String) timeBox.getValue();

    }

    @FXML
    public void changeDate() {
        LocalDate date = departDate.getValue();
        departureDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }

    //End of onChange Event handlers

    @FXML
    public void checkFormContents() throws IOException {
        errorMessage = "";
        // if error occurs set error msg visibility
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
        } else if (!Utils.phoneChecker(phoneNumber)) {
            errorMessage = "You must enter a valid phone number";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (age == null) {
            errorMessage = "You must choose an age";
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
        } else if (departureDate == null) {
            errorMessage = "You must choose a departure date";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (origin == null) {
            errorMessage = "You must choose an origin";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (destination == null) {
            errorMessage = "You must choose a destination";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        } else if (departureTime == null) {
            errorMessage = "You must choose a departure time";
            errorLabel.setVisible(true);
            errorLabel.setText(errorMessage);
            errorLabel.setManaged(true);
            return;
        }
        submitForm();
    }

    public void submitForm() throws IOException {
        // compile contents into files and generate ticket
        Utils.createTicket();
        Utils.createFileForStoringAllTickets();
        writeOverTicket();
        switchToScene2();
        errorLabel.setVisible(false);
    }

    private void writeOverTicket() {
        try {
            FileWriter writer = new FileWriter("Your_Boarding_Ticket.txt");
            ticketData = "\n\t*******************" +
                    "\n\t| Boarding Ticket |" +
                    "\n\t*******************" +
                    "\n\tName: " + name +
                    ",\n\tEmail: " + email +
                    ",\n\tPhone Number: " + phoneNumber +
                    ",\n\tGender: " + gender +
                    ",\n\tAge: " + age +
                    ",\n\tOrigin: " + origin +
                    ",\n\tDestination: " + destination +
                    ",\n\tDeparture Time: " + departureTime +
                    ",\n\tDeparture Date: " + departureDate +
                    ",\n\tBoarding Pass ID: " + Utils.generateTicketNum() +
                    ",\n\tTicket Price: $" + getFinalPrice() +
                    ",\n\tEstimated Time of Arrival: " + Utils.findTravelTime(origin, destination) + " minutes\n";
            writer.write(ticketData);
            writer.close();
            allTicketsGenerated.add(ticketData);
            storeAllTicketsGenerated();

        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    private static void storeAllTicketsGenerated() {
        try {
            Files.write(Paths.get("ALL_TICKETS_GENERATED.txt"), ticketData.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateCities() {
        cities.add("New York, NY");
        cities.add("Los Angeles, CA");
        cities.add("Chicago, IL");
        cities.add("Houston, TX");
        cities.add("Phoenix, AZ");
        cities.add("Philadelphia, PA");
        cities.add("San Antonio, TX");
        cities.add("San Diego, CA");
        cities.add("Dallas, TX");
        cities.add("San Jose, CA");
    }

    private void addCitiesToDrop(ComboBox drop) {
        Collections.sort(cities);
        for(String i : cities) {
            drop.getItems().add(i);
        }
    }

    public float applyTimeDiscount() {
        float timeDiscount = 0;
        switch((String) timeBox.getValue()) {
            case "06:00 am" -> timeDiscount += .10;
            case "10:30 am" -> timeDiscount += .08;
            case "03:00 pm" -> timeDiscount += .06;
            case "07:30 pm" -> timeDiscount += .04;
            case "12:00 am" -> timeDiscount += 1;
        }
        return timeDiscount;
    }

    // Switch Statement
    public int getSubTotal() {
        int eta = Utils.findTravelTime(origin, destination);
        float x = applyTimeDiscount();
        if(eta > 350) {
            return 225 + (int) (225 * x);
        } else if(eta > 300) {
            return 200 + (int) (200 * x);
        } else if (eta > 250) {
            return 175 + (int) (175 * x);
        } else if (eta > 200) {
            return 150 + (int) (150 * x);
        } else if (eta > 150) {
            return 125 + (int) (125 * x);
        } else if (eta > 100) {
            return 100 + (int) (100 * x);
        }
        return 75+ (int) (75 * x);
    }

    public int getFinalPrice() {
        if (Integer.parseInt(age) <= 12) return (int) (getSubTotal() * .5f);
        if (Integer.parseInt(age) >= 60) return (int) (getSubTotal() * .6f);
        if(gender.equals("female")) return (int) (getSubTotal() * .25f);
        return getSubTotal();
    }

}