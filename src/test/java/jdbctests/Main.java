package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@34.227.112.9:1521:xe";
        String dbUsername ="hr";
        String dbPassword = "hr";

       //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultTest object
        ResultSet resultSet = statement.executeQuery("Select * from departments");


        //how to retrieve any information from oracle
        //first of all u need to
        // move pointer first row---> resultSet.next();
//        resultSet.next();
//
//        //getting info with column name
//        System.out.println(resultSet.getString("region_name"));
//        //getting the info with column index(starts from 1)
//        System.out.println(resultSet.getString(2));
//
//        System.out.println(resultSet.getInt(1)+ " "+resultSet.getString("region_name"));
//
//        //move to second row
//        resultSet.next();
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getInt("region_id"));
//
//
//        resultSet.next();
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getString(2));
//
//        resultSet.next();
//        System.out.println(resultSet.getInt(1)+ " "+resultSet.getString("region_name"));
//
//

        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+ " - "+resultSet.getString(2)+ " - "+resultSet.getString(3)+ " - "+resultSet.getString(4));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();




    }
}
