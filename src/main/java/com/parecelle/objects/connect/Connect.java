package com.parecelle.objects.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    // ./mvnw spring-boot:run
    public static Connection getConnection () throws Exception {

        Class.forName("org.postgresql.Driver");

		String url = "jdbc:postgresql://viaduct.proxy.rlwy.net:59020/railway";
        String user = "postgres";
        String password = "23d16-F6Fc35GAE*5*dE246gaCd2d3aE";

        Connection connection = null;

        try {
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection to the PostgreSQL server failed. Error: " + e.getMessage());
        }
		return connection;
    
    }
}
