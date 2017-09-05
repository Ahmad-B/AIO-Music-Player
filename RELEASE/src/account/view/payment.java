/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;

import common.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Surya
 */
public class payment implements Initializable {
    private final int customerId = common.Customer.getCurrentCustomer().getId();
    private final Database db = Database.getInstance();
    private final Connection con = db.getConnection();
    PreparedStatement ps;
    
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button paypal;
    
    @FXML
    private Button remove;
    @FXML
    private Button topup;
    @FXML
    private Label status;
    @FXML
    private Button back;
    
    @FXML
    private TextField number;
    @FXML
    private TextField name;
    @FXML
    private TextField security;
    @FXML
    private TextField expiry;
    @FXML
    private Button card;
    
    @FXML
    private Label month;
    
    @FXML
    private Label type;
    
    private final long mon = 1;
    private final long seconds = mon * 31556952L / 12;
    private final long milliseconds = seconds * 1000;
    private long Date;
    
    private int payID = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("isSubscribed?: "+common.Customer.getCurrentCustomer().isSubscribed());
        System.out.println("This is the global variable at initialisation: " + common.gui.FrontController.socialUpdateNeeded);
        status.setText(common.Customer.getCurrentCustomer().isSubscribed()?"Subscribed":"Free User");
        month.setText("Unsubscribed");
        
        try{
            ps = con.prepareStatement("SELECT PaymentID, isCreditCard, payBy FROM PaymentType WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int PaymentID = rs.getInt("PaymentID");
                payID = PaymentID;
                int isCreditCard = rs.getInt("isCreditCard");
                //long date = rs.getLong("payBy");
                ps = con.prepareStatement("SELECT deadLine FROM Customer WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                rs = ps.executeQuery();
                if (rs.next()) month.setText(new Date(rs.getLong("deadLine")+milliseconds).toString());
                if (isCreditCard==0){
                    type.setText("Payment: Paypal");
                    ps = con.prepareStatement("SELECT * FROM CreditCard WHERE CreditCardID = ?");
                    ps.setInt(1, PaymentID);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        number.setPromptText(rs.getString("cardNumber"));
                        name.setPromptText(rs.getString("name"));
                        security.setPromptText(rs.getString("securityCode"));
                        expiry.setPromptText(rs.getString("expDate"));
                    }
                    
                } else {
                    type.setText("Payment: Card");
                    ps = con.prepareStatement("SELECT * FROM Paypal WHERE PaypalID = ?");
                    ps.setInt(1, PaymentID);
                    rs = ps.executeQuery();
                    if (rs.next()){
                        email.setPromptText(rs.getString("email"));
                        password.setPromptText("Password Saved");
                    }
                }
            } else {
                toggleButtons(false);
            }
            System.out.println(payID);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (common.Customer.reverted) status.setText("Payment Outstanding");
    }
    
    @FXML
    private void submitP(){
        try {
            String mail = email.getText();
            String pass = password.getText();
            int PaymentID = 0;
            Date = new Date().getTime()+milliseconds;
            
            ps = con.prepareStatement("SELECT PaymentID, isCreditCard FROM PaymentType WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                PaymentID = rs.getInt("PaymentID");
                int isCreditCard = rs.getInt("isCreditCard");
                
                if(isCreditCard==0){
                    ps = con.prepareStatement("UPDATE PaymentType SET payBy = ? WHERE CustomerID = ?");
                    ps.setLong(1, Date);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("UPDATE Paypal SET email = ?, password = ? WHERE PaypalID = ?");
                    ps.setString(1, mail);
                    ps.setString(2, pass);
                    ps.setInt(3, PaymentID);
                    ps.executeUpdate();
                } else {
                    ps = con.prepareStatement("DELETE FROM CreditCard WHERE CreditCardID = ?");
                    ps.setInt(1, PaymentID);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("UPDATE PaymentType SET payBy = ?, isCreditCard = ? WHERE CustomerID = ?");
                    ps.setLong(1, Date);
                    ps.setInt(2, 0);
                    ps.setInt(3, customerId);
                    ps.executeUpdate();
                    
                    ps = db.getConnection().prepareStatement("INSERT INTO Paypal (email, password, PaypalID) "
                            + "VALUES (?, ?, ?)");
                    ps.setString(1, mail);
                    ps.setString(2, pass);
                    ps.setInt(3, PaymentID);
                    ps.executeUpdate();
                }
                
            }
            else{
                ps = con.prepareStatement("INSERT INTO PaymentType (isCreditCard, CustomerID, hasPaid, payBy) "
                        + "VALUES (?, ?, ?, ?)");
                ps.setInt(1, 0);
                ps.setInt(2, customerId);
                ps.setInt(3, 1);
                ps.setLong(4, Date);
                System.out.println("Success"+ ps.executeUpdate());

                ps = con.prepareStatement("SELECT PaymentID FROM PaymentType WHERE "
                        + "CustomerID = ?");
                ps.setInt(1, customerId);
                rs = ps.executeQuery();
                while(rs.next()){
                    PaymentID = rs.getInt("PaymentID");
                }

                ps = db.getConnection().prepareStatement("INSERT INTO Paypal (email, password, PaypalID) "
                        + "VALUES (?, ?, ?)");
                ps.setString(1, mail);
                ps.setString(2, pass);
                ps.setInt(3, PaymentID);
                ps.executeUpdate();
            }

                ps = con.prepareStatement("UPDATE Customer SET deadLine = ?, PaymentID = ? WHERE CustomerID = ?");
                ps.setLong(1, Date);
                ps.setInt(2, PaymentID);
                ps.setInt(3, customerId);
                ps.executeUpdate();
            
                PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Customer SET isSubscribed = 1 WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                ps.executeUpdate();
                
            type.setText("Payment: Paypal");
            common.Customer.getCurrentCustomer().nowSubscribed();
            status.setText(common.Customer.getCurrentCustomer().isSubscribed()?"Subscribed":"Free User");
            month.setText(new Date(Date+milliseconds).toString());
            toggleButtons(true);
            
            System.out.println("This is the variable before (Paypal): " + common.gui.FrontController.socialUpdateNeeded);
            common.gui.FrontController.socialUpdateNeeded = true;
            System.out.println("This is the variable after: " + common.gui.FrontController.socialUpdateNeeded);
            

            
        } catch (SQLException ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //common.gui.FrontController.socialUpdateNeeded = true;
    }
    
    @FXML
    private void toggleButtons(boolean t){
        if (t){
            remove.setText("Remove Payment Method");
            topup.setText("Top Up");  
        } else {
            remove.setText("No Payment Method");
            topup.setText("No Payment Method");
        }
    }
    
    @FXML
    private void submitC(){
        Date = new Date().getTime()+milliseconds;
        try {
            String cardNo = number.getText();
            String n = name.getText();
            String sec = security.getText();
            String exp = expiry.getText();
            int PaymentID = 0;
            ps = con.prepareStatement("SELECT PaymentID, isCreditCard FROM PaymentType WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                PaymentID = rs.getInt("PaymentID");
                int isCreditCard = rs.getInt("isCreditCard");
                
                if (isCreditCard == 1){
                    ps = con.prepareStatement("UPDATE PaymentType SET payBy = ? WHERE CustomerID = ?");
                    ps.setLong(1, Date);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("UPDATE CreditCard (cardNumber, securityCode, expDate, name) "
                            + "VALUES (?, ?, ?, ?)");
                    ps.setString(1, cardNo);
                    ps.setString(2, sec);
                    ps.setString(3, exp);
                    ps.setString(4, n);
                    ps.executeUpdate();
                } else {
                    ps = con.prepareStatement("DELETE FROM Paypal WHERE PaypalID = ?");
                    ps.setInt(1, PaymentID);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("UPDATE PaymentType SET payBy = ?, isCreditCard = ? WHERE CustomerID = ?");
                    ps.setLong(1, Date);
                    ps.setInt(2, 1);
                    ps.setInt(3, customerId);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("INSERT INTO CreditCard (CreditCardID, cardNumber, securityCode, expDate, name) "
                            + "VALUES (?, ?, ?, ?, ?)");
                    ps.setInt(1, PaymentID);
                    ps.setString(2, cardNo);
                    ps.setString(3, sec);
                    ps.setString(4, exp);
                    ps.setString(5, n);
                    ps.executeUpdate();
                }
            } else {
                ps = con.prepareStatement("INSERT INTO PaymentType (isCreditCard, CustomerID, hasPaid, payBy) "
                        + "VALUES (?, ?, ?, ?)");
                ps.setInt(1, 1);
                ps.setInt(2, customerId);
                ps.setInt(3, 1);
                ps.setLong(4, Date);
                System.out.println("Success"+ ps.executeUpdate());

                ps = con.prepareStatement("SELECT PaymentID FROM PaymentType WHERE "
                        + "CustomerID = ?");
                ps.setInt(1, customerId);
                rs = ps.executeQuery();
                while(rs.next()){
                    PaymentID = rs.getInt("PaymentID");
                }
                
                ps = con.prepareStatement("INSERT INTO CreditCard (CreditCardID, cardNumber, securityCode, expDate, name) "
                        + "VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, PaymentID);
                ps.setString(2, cardNo);
                ps.setString(3, sec);
                ps.setString(4, exp);
                ps.setString(5, n);
                ps.executeUpdate();
            }
                ps = con.prepareStatement("UPDATE Customer SET deadLine = ?, PaymentID = ? WHERE CustomerID = ?");
                ps.setLong(1, Date);
                ps.setInt(2, PaymentID);
                ps.setInt(3, customerId);
                ps.executeUpdate();
                
                PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Customer SET isSubscribed = 1 WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                ps.executeUpdate();
                
                type.setText("Payment: Card");
                common.Customer.getCurrentCustomer().nowSubscribed();
                status.setText(common.Customer.getCurrentCustomer().isSubscribed()?"Subscribed":"Free User");
                month.setText(new Date(Date+milliseconds).toString());
                toggleButtons(true);
                System.out.println("This is the variable before (Card): " + common.gui.FrontController.socialUpdateNeeded);
                common.gui.FrontController.socialUpdateNeeded = true;
                System.out.println("This is the variable after: " + common.gui.FrontController.socialUpdateNeeded);
                
                
        } catch (SQLException ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //common.gui.FrontController.socialUpdateNeeded = true;
        
    }
    
    @FXML
    private void removePay(){
        if (payID>0){
            try{
                ps = con.prepareStatement("SELECT isCreditCard FROM PaymentType WHERE PaymentID = ?");
                ps.setInt(1, payID);
                ResultSet rs = ps.executeQuery();
                int isCreditCard = -1;
                if(rs.next()){
                    isCreditCard = rs.getInt("isCreditCard");
                }
                
                ps = con.prepareStatement("DELETE FROM PaymentType WHERE PaymentID = ?");
                ps.setInt(1, payID);
                ps.executeUpdate();
                
                if (isCreditCard == 0){
                    ps = con.prepareStatement("DELETE FROM Paypal where PaypalID = ?");
                    ps.setInt(1, payID);
                    ps.executeUpdate();
                } else {
                    ps = con.prepareStatement("DELETE FROM CreditCard where CreditCardID = ?");
                    ps.setInt(1, payID);
                    ps.executeUpdate();
                }
                
                toggleButtons(false);
                type.setText("Payment: NA");
                
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    @FXML
    private void topUp(){
        if (payID>0){
            Date = new Date().getTime()+milliseconds;
            try {
                System.out.println("Started");
                ps = con.prepareStatement("UPDATE PaymentType SET payBy = ? WHERE CustomerID = ?");
                ps.setLong(1, Date);
                ps.setInt(2, customerId);
                ps.executeUpdate();
                
                ps = con.prepareStatement("UPDATE Customer SET deadLine = ? WHERE CustomerID = ?");
                ps.setLong(1, Date);
                ps.setInt(2, customerId);
                ps.executeUpdate();
                
                PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Customer SET isSubscribed = 1 WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                ps.executeUpdate();
                
                common.Customer.getCurrentCustomer().nowSubscribed();
                status.setText(common.Customer.getCurrentCustomer().isSubscribed()?"Subscribed":"Free User");
                month.setText(new Date(Date+milliseconds).toString());
                toggleButtons(true);
                System.out.println("Finished");
                System.out.println("This is the variable before (Top Up): " + common.gui.FrontController.socialUpdateNeeded);
                common.gui.FrontController.socialUpdateNeeded = true;
                System.out.println("This is the variable after: " + common.gui.FrontController.socialUpdateNeeded);
                
            } catch (SQLException ex) {
                Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void closeWindow(){
        Stage s = (Stage) back.getScene().getWindow();
        s.close();
    }
}
