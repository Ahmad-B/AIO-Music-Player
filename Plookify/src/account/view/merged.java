/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;


import common.Database;
import common.gui.FrontController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Surya
 */
public class merged implements Initializable {
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField loginUser;
    @FXML
    private PasswordField loginPass;
    @FXML
    private Button loginSubmit;
    @FXML
    private Button loginClear;
    @FXML
    private Button forgot;

    @FXML
    private AnchorPane createPane;
    @FXML
    private TextField createUser;
    @FXML
    private PasswordField createPass;
    @FXML
    private TextField first;
    @FXML
    private TextField sur;
    @FXML
    private PasswordField reenter;
    @FXML
    private TextField address;
    @FXML
    private TextField postcode;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField reenterEmail;
    
    private final Database db = Database.getInstance();;
    private Statement sm;
    private ArrayList<String> inputs;
    private PreparedStatement ps;
    private final Connection con = db.getConnection();
    
    @FXML
    private Button createSubmit;
    @FXML
    private Button createClear;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void clearLogin(){
        loginUser.setText("");
        loginPass.setText("");
    }
    
    @FXML
    private void login(){
        String username = loginUser.getText();
        String password = loginPass.getText();
        boolean found = false;
        ps = null;
        String st = "SELECT CustomerID, username, password FROM Customer WHERE username = ?";
        try {
            ps = con.prepareStatement(st);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            //sm = db.getConnection().createStatement();
            //ResultSet rs = sm.executeQuery("SELECT CustomerID, username, password, isSubscribed FROM Customer WHERE username='"+username+"'");
            while(rs.next()){
                if (username.equals(rs.getString("username"))){
                    if (password.equals(rs.getString("password"))){
                        common.Customer.getCurrentCustomer().login(rs.getInt("CustomerID"));
                        mainPage(username);
                        Stage stage = (Stage) loginSubmit.getScene().getWindow();
                        stage.close();
                        return;
                    } else {
                        loginUser.setText("Incorrect Password!");
                        loginPass.setText("");
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                loginUser.setText("User not found"); loginPass.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(merged.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
    private void clearCreate(){
        createUser.setText("");
        createPass.setText("");
        reenter.setText("");
        first.setText("");
        sur.setText("");
        address.setText("");
        postcode.setText("");
        number.setText("");
        email.setText("");
        reenterEmail.setText("");
    }
    
    @FXML
    private void createAccount(){
        boolean errorFree = true;
        ArrayList<String> errors = new ArrayList<>();
        
        String username = createUser.getText();
        String password = createPass.getText();
        String passwordR = reenter.getText();
        String firstName = first.getText();
        String surname = sur.getText();
        String add = address.getText();
        String post = postcode.getText();
        String num = number.getText();
        String mail = email.getText();
        String mailR = reenterEmail.getText();
        long date = new Date().getTime();
        
        if (createUser.getText().equals("")|createPass.getText().equals("")|reenter.getText().equals("")|address.getText().equals("")|
                postcode.getText().equals("")|number.getText().equals("")|email.getText().equals("")|reenterEmail.getText().equals("")|
                first.getText().equals("")|sur.getText().equals("")){
            errorFree = false;
            errors.add("All fields must be filled!");
        }

        try{
            sm = db.getConnection().createStatement();
            ResultSet rs = sm.executeQuery("SELECT username FROM Customer");
            while (rs.next()){
                if (createUser.getText().equals(rs.getString("username"))){
                    createUser.setText("");
                    errorFree = false;
                    errors.add("Username already exists!");
                    break;
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        if (!createPass.getText().equals(reenter.getText())){
            reenter.setText("");
            errorFree = false;
            errors.add("Passwords don't match!");
        } if (!email.getText().equals(reenterEmail.getText())){
            reenterEmail.setText("");
            errorFree = false;
            errors.add("Emails don't match!");
        }
        if (errorFree){
            
            try{
                ps = con.prepareStatement("INSERT INTO Customer (name, PaymentID, number, email, isSubscribed, isDiscoverable, lastReplaceDate, username, password, addressLine1, postCode, surname, deadLine) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, firstName);
                ps.setInt(2, -1);
                ps.setString(3, num);
                ps.setString(4, mail);
                ps.setInt(5, 0);
                ps.setInt(6,0);
                ps.setLong(7,date);
                ps.setString(8, username);
                ps.setString(9, password);
                ps.setString(10, add);
                ps.setString(11, post);
                ps.setString(12, surname);
                ps.setLong(13, 0);
                ps.executeUpdate();
                ps = con.prepareStatement("SELECT CustomerID FROM Customer WHERE username = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                //sm = db.getConnection().createStatement();
                //sm.executeUpdate("INSERT INTO Customer (name, PaymentID, number, email, isSubscribed, isDiscoverable, lastReplaceDate, username, password, addressLine1, postCode, surname)"
                        //+ " VALUES ('"+firstName+"', '-1','"+num+"','"+mail+"', '0.0',"+"'0.0','"+date+"','"+username+"','"+password+"','"+add+"','"+post+"','"+surname+"')"); 
                //ResultSet rs = sm.executeQuery("SELECT CustomerID FROM Customer WHERE username = '"+username+"'");
                common.Customer.getCurrentCustomer().login(rs.getInt("CustomerID")); //assumption: a user will always subscribe to Plookify AFTER creating an account
                mainPage(username);
                Stage stage = (Stage) createSubmit.getScene().getWindow();
                stage.close();
                return;
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            showError(errors);
        }
    }
    
    private void showError(ArrayList<String> errors){
        Stage error = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(errorWindow.class.getResource("errorWindow.fxml"));
        Pane screen = null;
        
        try {
            screen = (Pane) loader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Scene scene = new Scene(screen);
        error.setTitle("Warning!");
        error.setScene(scene);
        errorWindow ew = loader.<errorWindow>getController();
        
        try{
            ew.setMessage(errors);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        error.show();
    }

    private void mainPage(String username) {
        Stage main = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../common/gui/Front.fxml"));
        Pane page;
        try {
            page = (Pane) loader.load();
            Scene scene = new Scene(page);
            main.setTitle("Plookify");
            main.setScene(scene);
            FrontController f = loader.<FrontController>getController();
            try{
                f.changeName(username);
            } catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        main.show();
        } catch (IOException ex) {
            Logger.getLogger(merged.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
