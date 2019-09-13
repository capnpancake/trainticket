package model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Ticket {

    private static int counter = 100;
    private String pnr;
    private Date travelDate;
    private Train train;
    private TreeMap<Passenger, Double> passengers = new TreeMap<>();

    public Ticket(Date travelDate, Train train) {
        this.travelDate = travelDate;
        this.train = train;
        pnr = generatePNR();
        counter += 1;
    }

    private String generatePNR(){
        // return a string formatted as SD_YYYYMMDD_100
        // where S = train.source, D = train.destination, and 100 is the counter
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return Character.toString(train.getSource().charAt(0)) +
                Character.toString(train.getDestination().charAt(0)) + "_" +
                df.format(travelDate) + "_" +
                counter;
    }


    private double calcPassengerFare(Passenger passenger){
        // calculate and return fare for passenger
        double fare = getTrain().getTicketPrice();

        // determine discount based on age
        if (passenger.getAge() <= 12){
            fare *= 0.5;
        } else if (passenger.getAge() >= 60){
            fare *= 0.6;
        }
        // determine discount for gender
        if (passenger.getGender() == 'F'){
            fare *= 0.75;
        }

        return fare;
    }

    public void addPassenger(String name, int age, char gender){
        Passenger pssngr = new Passenger(name, age, Character.toUpperCase(gender));
        passengers.put(pssngr, calcPassengerFare(pssngr));
    }

    private double calculateTotalTicketPrice(){
        double total = 0;
        for (Map.Entry<Passenger, Double> entry : passengers.entrySet()){
            total += entry.getValue();
        }
        return total;
    }

    private StringBuilder generateTicket(){
        // set up template and fill in upper portion
        StringBuilder td = new StringBuilder()
                .append("PNR          : ").append(pnr).append("\r\n")
                .append("Tran No.     : ").append(train.getTrainNo()).append("\r\n")
                .append("Train Name   : ").append(train.getTrainName()).append("\r\n")
                .append("From         : ").append(train.getSource()).append("\r\n")
                .append("To           : ").append(train.getDestination()).append("\r\n")
                .append("Travel Date  : ").append(travelDate).append("\r\n\r\n")
                .append("Passengers:" + "\r\n")
                .append("Name\t\tAge\tGender\tFare\r\n");

        // use a foreach to iterate through the treemap and print all passengers
        for (Map.Entry<Passenger, Double> entry : passengers.entrySet()){
            String name = entry.getKey().getName();
            int age = entry.getKey().getAge();
            char gender = entry.getKey().getGender();
            td.append(name).append("\t\t").append(age).append("\t").append(gender).append("\t").append(entry.getValue()).append("\r\n");
        }

        // finish by showing total price
        td.append("Total Price : " + "\t\t\t").append(calculateTotalTicketPrice());

        // return fleshed out stringbuilder
        return td;
    }

    public void writeTicket() throws IOException {
        String file = "E:\\tickets\\" + getPnr() + ".txt";
        Path path = Paths.get(file);
        if (!Files.exists(path)){ Files.createFile(path); }
        FileWriter fw = new FileWriter(file);
        fw.write(generateTicket().toString());
        fw.close();
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TreeMap<Passenger, Double> getPassengers() {
        return passengers;
    }

    public void setPassengers(TreeMap<Passenger, Double> passengers) {
        this.passengers = passengers;
    }
}


