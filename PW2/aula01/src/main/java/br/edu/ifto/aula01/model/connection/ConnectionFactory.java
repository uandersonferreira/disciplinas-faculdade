package br.edu.ifto.aula01.model.connection;


import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ConnectionFactory {
//    public static void main(String[] args) {
//
//        //testar conexão
//        System.out.println("Connection successful: "+ConnectionFactory.getConnection());
//
//    }//main

    public static Connection getConnection()  {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_teste";
            String userName = "postgres";
            String password = "root";

            return DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
        }

        return null;

    }//method

}//class
