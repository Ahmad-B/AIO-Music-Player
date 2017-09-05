/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;

import account.accountManager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import common.Database;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 *
 * @author Surya
 */
public class accountSettings implements Initializable {
    @FXML
    private Pane accountSettings;
    @FXML
    private Label device1;
    @FXML
    private Label device2;
    @FXML
    private Label device3;
    @FXML
    private Label device4;
    @FXML
    private Label device5;
    @FXML
    private Button close;
    @FXML
    private Button upgrade;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Label date;
    
    @FXML
    private TextField update1;
    @FXML
    private TextField update2;
    @FXML
    private TextField update3;
    @FXML
    private TextField update4;
    @FXML
    private TextField update5;
    
    @FXML
    private Button confirm1;
    @FXML
    private Button confirm2;
    @FXML
    private Button confirm3;
    @FXML
    private Button confirm4;
    @FXML
    private Button confirm5;
    
    @FXML
    private Button logout;
    
    private final Database db = Database.getInstance();
    private Statement sm;
    private PreparedStatement ps;
    Connection con = db.getConnection();
    private String[] deviceList = {"","","","",""};
    private final int customerId = common.Customer.getCurrentCustomer().getId();
    private final int baseId = customerId*100;
    //private final long month = MILLISECONDS.convert(1, MONTHS);
    private final long month = 1;
    private final long seconds = month * 31556952L / 12;
    private final long milliseconds = seconds * 1000;
    private long lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
    
    /*delete from Owns where CustomerID = 1
delete from Device where DeviceID>100 and DeviceID<106*/
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sm = db.getConnection().createStatement();
            System.out.println(common.Customer.getCurrentCustomer().getId());
            ps = con.prepareStatement("SELECT d.name, d.DeviceID, o.CustomerID "
                    + "from Device d, Owns o "
                    + "where d.DeviceID = o.DeviceID and CustomerID = ?");
            ps.setInt(1, common.Customer.getCurrentCustomer().getId());
            ResultSet rs = ps.executeQuery();
            /*ResultSet rs = sm.executeQuery("SELECT d.name, d.DeviceID, o.CustomerID "
                    + "from Device d, Owns o "
                    + "where d.DeviceID = o.DeviceID and CustomerID = "+
                    common.Customer.getCurrentCustomer().getId());*/
            for(int i = 0; i<5 && rs.next(); i++){
                deviceList[i] = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
        
        //if(deviceList[0].equals("")) button1.setVisible(false);
        device1.setText(deviceList[0]);
        
        //if (deviceList[1].equals("")) button2.setVisible(false);
        device2.setText(deviceList[1]);
        
        //if (deviceList[2].equals("")) button3.setVisible(false);
        device3.setText(deviceList[2]);
        
        //if (deviceList[3].equals("")) button4.setVisible(false);
        device4.setText(deviceList[3]);
        
        //if (deviceList[4].equals("")) button5.setVisible(false);
        device5.setText(deviceList[4]);
    }
    
    @FXML
    private void addDevice1(){
        button1.setVisible(false);
        update1.setPromptText((device1.getText().equals(""))?"Add Device":"Replace Device");
        update1.setVisible(true);
        confirm1.setVisible(true);
        }
    
    @FXML
    private void addDevice2(){
        button2.setVisible(false);
        update2.setPromptText((device2.getText().equals(""))?"Add Device":"Replace Device");
        update2.setVisible(true);
        confirm2.setVisible(true);
    }
    @FXML
    private void addDevice3(){
        button3.setVisible(false);
        update3.setPromptText((device3.getText().equals(""))?"Add Device":"Replace Device");
        update3.setVisible(true);
        confirm3.setVisible(true);
    }
    @FXML
    private void addDevice4(){
        button4.setVisible(false);
        update4.setPromptText((device4.getText().equals(""))?"Add Device":"Replace Device");
        update4.setVisible(true);
        confirm4.setVisible(true);
    }
    @FXML
    private void addDevice5(){
        button5.setVisible(false);
        update5.setPromptText((device5.getText().equals(""))?"Add Device":"Replace Device");
        update5.setVisible(true);
        confirm5.setVisible(true);
    }
    
    @FXML
    private void confirmed1(){
        String device = update1.getText();
        int deviceId = baseId+1;
        if (device.equals("")) return;
        if (device1.getText().equals("")){
            try {
                ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?)");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                ps.executeUpdate();
                ps = db.getConnection().prepareStatement("INSERT INTO Owns VALUES (?,?)");
                ps.setInt(1, customerId);
                ps.setInt(2, deviceId);
                ps.executeUpdate();
                /*
                sm = db.getConnection().createStatement();
                sm.executeUpdate("INSERT INTO Device (DeviceID, name) VALUES ('"+deviceId+"','"+device+"')");
                sm.executeUpdate("INSERT INTO Owns (CustomerID, DeviceID) VALUES ('"+customerId+"','"+deviceId+"')");
                */
                device1.setText(device);
            } catch (SQLException ex) {
                Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            long currentTime = new Date().getTime();
            if (currentTime-lastReplacedTime < milliseconds){
                update1.setText("");
                update1.setPromptText("Cannot replace now.");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ps = con.prepareStatement("UPDATE Device SET name = ? WHERE DeviceID = ?");
                    ps.setString(1, device);
                    ps.setInt(2, deviceId);
                    ps.executeUpdate();
                    //sm = db.getConnection().createStatement();
                    //sm.executeUpdate("UPDATE Device SET name = '"+device+"' WHERE DeviceID = '"+deviceId+"'");
                    device1.setText(device);
                    common.Customer.getCurrentCustomer().setReplacedDate();
                    date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
                    lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    //sm.executeUpdate("UPDATE Customer SET lastReplaceDate = '"+lastReplacedTime+"' WHERE CustomerID = '"+customerId+"'");
                } catch (SQLException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        button1.setVisible(true);
        update1.setVisible(false);
        confirm1.setVisible(false);
    }
    @FXML
    private void confirmed2(){
        String device = update2.getText();
        int deviceId = baseId+2;
        if (device.equals("")) return;
        if (device2.getText().equals("")){
            try {
                ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?)");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                ps.executeUpdate();
                ps = db.getConnection().prepareStatement("INSERT INTO Owns VALUES (?,?)");
                ps.setInt(1, customerId);
                ps.setInt(2, deviceId);
                ps.executeUpdate();
                /*
                sm = db.getConnection().createStatement();
                sm.executeUpdate("INSERT INTO Device (DeviceID, name) VALUES ('"+deviceId+"','"+device+"')");
                sm.executeUpdate("INSERT INTO Owns (CustomerID, DeviceID) VALUES ('"+customerId+"','"+deviceId+"')");
                */
                device2.setText(device);
            } catch (SQLException ex) {
                Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            long currentTime = new Date().getTime();
            if (currentTime-lastReplacedTime < milliseconds){
                update2.setText("");
                update2.setPromptText("Cannot replace now.");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ps = con.prepareStatement("UPDATE Device SET name = ? WHERE DeviceID = ?");
                    ps.setString(1, device);
                    ps.setInt(2, deviceId);
                    ps.executeUpdate();
                    device2.setText(device);
                    common.Customer.getCurrentCustomer().setReplacedDate();
                    date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
                    lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        button2.setVisible(true);
        update2.setVisible(false);
        confirm2.setVisible(false);
    }
    @FXML
    private void confirmed3(){
        String device = update3.getText();
        int deviceId = baseId+3;
        if (device.equals("")) return;
        if (device3.getText().equals("")){
            try {
                ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?)");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                ps.executeUpdate();
                ps = db.getConnection().prepareStatement("INSERT INTO Owns VALUES (?,?)");
                ps.setInt(1, customerId);
                ps.setInt(2, deviceId);
                ps.executeUpdate();
                /*
                sm = db.getConnection().createStatement();
                sm.executeUpdate("INSERT INTO Device (DeviceID, name) VALUES ('"+deviceId+"','"+device+"')");
                sm.executeUpdate("INSERT INTO Owns (CustomerID, DeviceID) VALUES ('"+customerId+"','"+deviceId+"')");
                */
                device3.setText(device);
            } catch (SQLException ex) {
                Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            long currentTime = new Date().getTime();
            if (currentTime-lastReplacedTime < milliseconds){
                update3.setText("");
                update3.setPromptText("Cannot replace now.");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    device3.setText(device);
                    common.Customer.getCurrentCustomer().setReplacedDate();
                    date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
                    lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        button3.setVisible(true);
        update3.setVisible(false);
        confirm3.setVisible(false);
    }
    
                /*ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                int executeUpdate = ps.executeUpdate();
                System.out.println("Hi! :"+executeUpdate);
                ps = db.getConnection().prepareCall("INSERT INTO Owns VALUES ('"+customerId+"','"+deviceId+"'");
                int executeUpdate1 = ps.executeUpdate();
                System.out.println(executeUpdate1);
                device3.setText(device);*/
    @FXML
    private void confirmed4(){
        String device = update4.getText();
        int deviceId = baseId+4;
        if (device.equals("")) return;
        if (device4.getText().equals("")){
            try {
                ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?)");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                ps.executeUpdate();
                ps = db.getConnection().prepareStatement("INSERT INTO Owns VALUES (?,?)");
                ps.setInt(1, customerId);
                ps.setInt(2, deviceId);
                ps.executeUpdate();
                /*
                sm = db.getConnection().createStatement();
                sm.executeUpdate("INSERT INTO Device (DeviceID, name) VALUES ('"+deviceId+"','"+device+"')");
                sm.executeUpdate("INSERT INTO Owns (CustomerID, DeviceID) VALUES ('"+customerId+"','"+deviceId+"')");
                */
                device4.setText(device);
            } catch (SQLException ex) {
                Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            long currentTime = new Date().getTime();
            if (currentTime-lastReplacedTime < milliseconds){
                update4.setText("");
                update4.setPromptText("Cannot replace now.");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    device4.setText(device);
                    common.Customer.getCurrentCustomer().setReplacedDate();
                    date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
                    lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        button4.setVisible(true);
        update4.setVisible(false);
        confirm4.setVisible(false);
    }
    @FXML
    private void confirmed5(){
        String device = update5.getText();
        int deviceId = baseId+5;
        if (device.equals("")) return;
        if (device5.getText().equals("")){
            try {
                
                ps = db.getConnection().prepareStatement("INSERT INTO Device VALUES (?, ?)");
                ps.setInt(1, deviceId);
                ps.setString(2, device);
                ps.executeUpdate();
                ps = db.getConnection().prepareStatement("INSERT INTO Owns VALUES (?,?)");
                ps.setInt(1, customerId);
                ps.setInt(2, deviceId);
                ps.executeUpdate();
                
                /*sm = db.getConnection().createStatement();
                sm.executeUpdate("INSERT INTO Device (DeviceID, name) VALUES ('"+deviceId+"','"+device+"')");
                sm.executeUpdate("INSERT INTO Owns (CustomerID, DeviceID) VALUES ('"+customerId+"','"+deviceId+"')");*/
                device5.setText(device);
            } catch (SQLException ex) {
                Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            long currentTime = new Date().getTime();
            if (currentTime-lastReplacedTime < milliseconds){
                update5.setText("");
                update5.setPromptText("Cannot replace now.");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    device5.setText(device);
                    common.Customer.getCurrentCustomer().setReplacedDate();
                    date.setText(common.Customer.getCurrentCustomer().getReplacedDate().toString());
                    lastReplacedTime = common.Customer.getCurrentCustomer().getReplacedTime();
                    ps = con.prepareStatement("UPDATE Customer SET lastReplaceDate = ? WHERE CustomerID = ?");
                    ps.setLong(1, lastReplacedTime);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        button5.setVisible(true);
        update5.setVisible(false);
        confirm5.setVisible(false);
    }
    
    @FXML
    private void change(){
        Stage s = new Stage();
        SplitPane screen = null;
        try {
            screen = (SplitPane) FXMLLoader.load(getClass().getResource("payment.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(screen);
        s.setTitle("Plookify: Payment");
        s.setScene(scene);
        s.show();
    
    }
    
    @FXML
    private void closeAccount(){
        System.out.println("Closing Account");
        try {
            int playlistID = -2;
            ps = con.prepareStatement("SELECT PlaylistID FROM Playlist WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs0 = ps.executeQuery();
            
            if (rs0.next()) playlistID = rs0.getInt("PlaylistID");
            
            String trackQuery = "DELETE FROM TrackPlaylist WHERE playlistID = ?";
            PreparedStatement trackpsm = db.getConnection().prepareStatement(trackQuery);
            trackpsm.setInt(1, playlistID);
            trackpsm.executeUpdate();
            // then delete playlist
            String query = "DELETE FROM Playlist WHERE PlaylistID=?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, playlistID);
            psm.executeUpdate();
            
            ps = con.prepareStatement("SELECT PaymentID FROM Customer WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            System.out.println("SELECT PaymentID FROM Customer WHERE CustomerID = ?");
            
            int PaymentID = 0;
            if (rs.next()) PaymentID = rs.getInt("PaymentID");
            System.out.println("PaymentID = "+PaymentID);
            
            ps = con.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ps.executeUpdate();
            System.out.println("DELETE FROM Customer WHERE CustomerID = ?");
            
            if (PaymentID>0){
                ps = con.prepareStatement("SELECT isCreditCard FROM PaymentType WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                rs = ps.executeQuery();
                System.out.println("SELECT isCreditCard FROM PaymentType WHERE CustomerID = ?");

                int isCreditCard = -1;
                if (rs.next()) isCreditCard = rs.getInt("isCreditCard");
                System.out.println("isCreditCard = "+isCreditCard);

                ps = con.prepareStatement("DELETE FROM PaymentType WHERE CustomerID = ?");
                ps.setInt(1, customerId);
                ps.executeUpdate();
                System.out.println("DELETE FROM PaymentType WHERE CustomerID = ?");
                
                if (isCreditCard == 0){
                    ps = con.prepareStatement("DELETE FROM Paypal WHERE PaypalID = ?");
                    ps.setInt(1, PaymentID);
                    ps.executeUpdate();
                    System.out.println("DELETE FROM Paypal WHERE PaypalID = ?");
                } else {
                    ps = con.prepareStatement("DELETE FROM CreditCard WHERE CreditCardID = ?");
                    ps.setInt(1, PaymentID);
                    ps.executeUpdate();
                    System.out.println("DELETE FROM CreditCard WHERE CreditCardID = ?");
                }
            }
            ps = con.prepareStatement("DELETE FROM Owns WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ps.executeUpdate();
            System.out.println("DELETE FROM Owns WHERE CustomerID = ?");

            ps = con.prepareStatement("DELETE FROM Device WHERE DeviceID > ? AND DeviceID < ?");
            ps.setInt(1, baseId);
            ps.setInt(2, baseId+6);
            ps.executeUpdate();
            System.out.println("DELETE FROM Device WHERE DeviceID > ? AND DeviceID < ?");
            
            signout();
        } catch (SQLException ex) {
            Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void signout(){
        Stage login = new Stage();
        SplitPane screen = null;
        try {
            screen = (SplitPane) FXMLLoader.load(getClass().getResource("merged.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(accountSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(screen);
        login.setTitle("Log in to Plookify");
        login.setScene(scene);
        common.Customer.getCurrentCustomer().logout();
        Stage s = (Stage) logout.getScene().getWindow();
        s.close();
        login.show();
    }
}
