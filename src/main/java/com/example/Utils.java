package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Utils {
    private final static ArrayList<String> locationCombos = (ArrayList<String>) Arrays.asList(new String[]{ 
        "New York, Ny : Los Angeles, CA", "New York, NY : Chicago, IL", "New York, NY : Houston, TX", "New York, NY : Phoenix, AZ",
        "New York, NY : Philadelphia, PA", "New York, NY : San Antonia, TX", "New York, NY : San Diego, CA", "New York, NY : Dallas, TX",
        "New York, NY : San Jose, CA", "Los Angeles, CA : Chicago, IL", "Los Angeles, CA : Houston, TX", "Los Angeles, CA : Phoenix, AZ",
        "Los Angeles, CA : Philadelphia, PA", "Los Angeles, CA : San Antonio, TX", "Los Angeles, CA : San Diego, CA",
        "Los Angeles, CA : Dallas, TX", "Los Angeles, CA : San Jose, CA", "Chicago, IL : Houston, TX", "Chicago, IL : Phoenix, AZ",
        "Chicago, IL : Philadelphia, PA", "Chicago, IL : San Antonio, TX", "Chicago, IL : San Diego, CA", "Chicago, IL : Dallas, TX",
        "Chicago, IL : San Jose, CA", "Houston, TX : Phoenix, AZ", "Houston, TX : Philadelphia, PA", "Houston, TX : San Antonio, TX",
        "Houston, TX : San Diego, CA", "Houston, TX : Dallas, TX", "Houston, TX : San Jose, CA", "Phoenix, AZ : Philadelphia, PA",
        "Phoenix, AZ : San Antonio, TX", "Phoenix, AZ : San Diego, CA", "Phoenix, AZ : Dallas, TX", "Phoenix, AZ : San Jose, CA",
        "Philadelphia, PA : San Antonio, TX", "Philadelphia, PA : San Diego, CA", "Philadelphia, PA : Dallas, TX",
        "Philadelphia, PA : San Jose, CA", "San Antonio, TX : San Diego, CA", "San Antonio, TX : Dallas, TX", 
        "San Antonio, TX : San Jose, CA", "San Diego, CA : Dallas, TX", "San Diego, CA : San Jose, CA", "Dallas, TX : San Jose, CA",}); // use contains
    private final static int[] travelTime = new int[]{380, 160, 250, 345, 60, 265, 375, 245, 410, 240, 190, 85, 300, 165, 60, 175, 75, 170, 230,
    114, 180, 265, 150, 280, 175, 200, 65, 215, 75, 260, 265, 180, 70, 135, 120, 319, 363, 220, 470, 185, 75, 308, 149, 85, 240};
    
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

    public static int findTravelTime(String depart, String arrival) {
        int index = locationCombos.indexOf(depart + " : " + arrival);
        if (index != -1) {
            return travelTime[index];
        }
        index = locationCombos.indexOf(arrival + " : " + depart);
        if (index != -1) {
            return travelTime[index];
        } else return -1;
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
