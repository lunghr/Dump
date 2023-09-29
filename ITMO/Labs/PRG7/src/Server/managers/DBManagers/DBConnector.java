package Server.managers.DBManagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    // fields for driver
    private String URL;
    private String username;
    private String password;
    private Connection connection;

    //object of connector
    public DBConnector (){
        //local db
        this.URL = "jdbc:postgresql://localhost:5432/studs";
        this.username ="postgres";
        this.password = "Shaurma8982";
    }
    // method for initialize connection
    public void connect(){
        try{
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection established");
        }catch (SQLException e){
            System.out.println("Connection error");
        }

    }

    public Connection getConnection(){
        return connection;
    }

}
