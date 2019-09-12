package view;

import dataAccessLayer.TrainDAO;
import model.Ticket;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TicketApplication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TrainDAO dao = new TrainDAO();

        System.out.println("Enter the date (dd/MM/yyyy) you will be traveling on : ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date =  new Date();
        try {
            date = sdf.parse(scan.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.displayTrainSchedule();
        System.out.print("Please choose a train to purchase tickets for: ");
        Ticket ticket = new Ticket(date, dao.findTrain(Integer.parseInt(scan.nextLine())));

        System.out.print("How many passengers are you purchasing tickets for? ");
        int passengers = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < passengers; i++){
            System.out.print("Enter Passenger Name : ");
            String name = scan.nextLine();
            System.out.print("Passenger's Age : ");
            int age = Integer.parseInt(scan.nextLine());
            System.out.print("Passenger's Gender (M/F) : ");
            char gender = scan.nextLine().charAt(0);
            ticket.addPassenger(name, age, gender);
        }

        try {
            ticket.writeTicket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ticket booked with PNR: " + ticket.getPnr());
    }
}
