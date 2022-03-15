package jdbctests;

import java.sql.*;

public class MetaData_Example {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@34.227.112.9:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultTest object
        ResultSet resultSet = statement.executeQuery("Select * from employees");


        //get the database info related data inside the dbMataData object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        
        System.out.println("User = " + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Diver Name = " + dbMetadata.getDriverName());
        System.out.println("Diver Version = " + dbMetadata.getDriverVersion());

        //get the Resultset Metadata object info
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        
        //how many columns we have 
        int colCount = resultSetMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);
        
        //get the cloumns names
        String colNames1 = resultSetMetaData.getColumnName(1);
        System.out.println("colNames1 = " + colNames1);
        String colNames2 = resultSetMetaData.getColumnName(2);
        System.out.println("colNames2 = " + colNames2);

//        System.out.println(resultSetMetaData.getColumnName(1));
//        System.out.println(resultSetMetaData.getColumnName(2));

        //print all the column names dynamically

        for (int i=1; i<=colCount; i++) {

            System.out.println(resultSetMetaData.getColumnName(i));

        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}

