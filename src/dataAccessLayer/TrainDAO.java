package dataAccessLayer;

import model.Train;

import java.sql.*;

public class TrainDAO {
    Connection con;
    Statement stmt;

    // Constructor sets up connection with the database
    public TrainDAO() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
        con.createStatement();
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
}
