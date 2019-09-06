package dataAccessLayer;

import model.Train;

import java.sql.*;

public class TrainDAO {
    Connection con;
    Statement stmt;

    public TrainDAO() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
        con.createStatement();
    }

    public Train findTrain(int trainNo){
        String query = "SELECT * FROM trains WHERE train_no = " + trainNo;
        try {
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
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
