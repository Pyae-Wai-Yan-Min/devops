package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) {
        // Default to localhost:33060 if running locally in IntelliJ
        String dbHost = "localhost:33060";

        // If running inside a Docker container, pass the host via args or check args[0]
        if (args.length > 0) {
            dbHost = args[0];
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        Connection con = null;
        int retries = 30;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(3000);
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + dbHost + "/employees?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example"
                );
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted.");
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}