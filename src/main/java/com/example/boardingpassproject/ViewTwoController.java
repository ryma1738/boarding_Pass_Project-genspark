package com.example.boardingpassproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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

    
}
