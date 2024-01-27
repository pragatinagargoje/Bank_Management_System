package bank.management.system;

import java.sql.*;

public class Con {



    Connection connection;
    Statement statement; // Fix the variable name here

    public Con() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "root");
            statement = connection.createStatement(); // Fix the variable name here
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
