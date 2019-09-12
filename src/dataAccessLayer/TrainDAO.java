package dataAccessLayer;

import model.Train;

import java.sql.*;

public class TrainDAO {
    private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final String USERNAME = "hr";
    private final String PASSWORD = "hr";
    Connection con;
    Statement stmt;

    // Constructor sets up connection with the database
    public TrainDAO() {
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // method returns a train filled with data from the Trains table where Train_No = trainNo
    public Train findTrain(int trainNo){
        // Prepare query
        String query = "SELECT * FROM trains WHERE train_no = " + trainNo;
        try {
            ResultSet rs = stmt.executeQuery(query);
            rs.next();

            // get properties from query
            int no = rs.getInt(1);
            String name = rs.getString(2);
            String source = rs.getString(3);
            String dest = rs.getString(4);
            double price = rs.getDouble(5);

            return new Train(no, name, source, dest, price);
        } catch (SQLException e) {
            System.out.println("That train does not exist.");
            e.printStackTrace();
            return null;
        }
    }

    public void displayTrainSchedule(){
        String query = "SELECT * FROM trains";
        System.out.println("Train No\tName\tSource\tDestination\tTicket Price");
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                System.out.println(
                        rs.getInt(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" +
                        rs.getString(4) + "\t" +
                        rs.getDouble(5)
                );
            }
        } catch (SQLException e) {
            System.out.println("Unable to find schedule.");
            e.printStackTrace();
        }
    }
}
