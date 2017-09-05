/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.*;

public class Database {

    private Connection c = null;

    private static final Database INSTANCE = new Database();

    private Database() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:../SE38/Plookify/src/common/Plookify.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Database open");
    }

    public void insertCommand(String sql) {       
        try {
            c.createStatement().executeUpdate(sql); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public ResultSet selectCommand(String sql){
        try {
            return c.createStatement().executeQuery(sql);           
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return this.c;
    }
    
    public void closeConnection() throws SQLException {
        System.out.println("closing connection");
        this.c.close();
    }
}
