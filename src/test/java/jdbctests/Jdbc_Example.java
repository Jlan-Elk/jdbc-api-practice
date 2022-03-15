package jdbctests;

import java.sql.*;

public class Jdbc_Example {


    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@34.227.112.9:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultTest object
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //how to find many rows we hcave for the query
        //go last row and get the row count
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        //we need to move before the first row to get the full list since we are at the last row right now
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

//+++++++++++++++++++++++++++++++++++++++++++++++
        resultSet = statement.executeQuery("Select * from regions");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
