package view;


import java.util.Scanner;

public class TicketApplication {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter Your Train Number");
        int trainNo = input.nextInt();
        System.out.println("Enter Your Travel Date");
        String travelDate = input.nextLine();
        System.out.println("Enter Number of Passengers");
        String numOfPassengers = input.nextLine();
        System.out.println("Enter Passenger Name");
        String passengerName = input.nextLine();
        System.out.println("Enter Passenger Age");
        String passengerAge = input.nextLine();
        System.out.println("Enter Passenger Gender (M/F)");
        String passengerGender = input.nextLine();

    }


}





