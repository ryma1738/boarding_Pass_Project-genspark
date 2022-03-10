package com.example.boardingpassproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ViewTwoController {

    ViewController controller = new ViewController();

    @FXML
    Label ticket;
    @FXML
    ImageView ticketImage;
    

    public List<String> getTicketFile() throws IOException { 
    return Files.readAllLines(Paths.get("Your_Boarding_Ticket.txt"));
    }
    
    public String formatList() throws IOException {
        List<String> newList = getTicketFile();
        String str = "";
        for(String i : newList) {
            str += i + "\n";
        }
        return str;
    }

    public void initialize() throws IOException {
        ticketImage.setImage(new Image("earthPlane.png"));
        ticket.setText(formatList());
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("styleSheet.css");
        stage.setTitle("Ticket Generator");
        stage.getIcons().add(new Image("plane.png"));
        stage.setScene(scene);
        stage.show();
    }
    
}
