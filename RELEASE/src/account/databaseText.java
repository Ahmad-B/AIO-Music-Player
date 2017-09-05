/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import common.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Surya
 */
public class databaseText {
    public static void main(String[] args){
        System.out.println(new Date().getTime());
        Database db = Database.getInstance();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT PaymentID FROM PaymentType WHERE CustomerID = ?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("Success: "+rs.getInt("PaymentID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseText.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
