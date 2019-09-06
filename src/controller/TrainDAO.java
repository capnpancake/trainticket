package controller;

import java.sql.*;

public class TrainDAO {
    Connection con;
    Statement stmt;

    public TrainDAO() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
        stmt = con.createStatement();
    }
    
    public Train select(int id){
        return new Train();
    }
}
