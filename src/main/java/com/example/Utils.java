package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Utils {
    
    public static String generateTicketNum() {
        // generate random ticket number that does not match another ticket num
        return UUID.randomUUID().toString();
    }

    public static Boolean phoneChecker(String phoneNumber) {
        if (phoneNumber.matches("(?:\\d{3}-){2}\\d{4}")) {
            return true;
        } else {
            return false;
        }
    }

    public static String createPhoneNumber(String value, String phoneNumber) {
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

    public static void createTicket() {
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

    public static void createFileForStoringAllTickets() {
        try {
            File storeAllTickets = new File("ALL_TICKETS_GENERATED.txt");
            if (storeAllTickets.createNewFile()) {
                System.out.println("File created: " + storeAllTickets.getName());
            } else {
                System.out.println("File already exists: " + storeAllTickets.getName());
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }
    
}
