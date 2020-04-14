/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mike
 */
public class Database {

    private static final String connectionUrl = "jdbc:sqlserver://localhost:1433";
    private static final String userName = "SA";
    private static final String password = "Amuitatparola1";
    private static final String databaseName = "MusicAlbums";
    private static Connection connection = null;

    private static final Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }

        return connection;
    }

    private static void createConnection() {
        try {
            String url = connectionUrl + ";databaseName=" + databaseName + ";user=" + userName + ";password="
                    + password;
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.err.println(e);
            rollback();
        }
    }

    public static void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
