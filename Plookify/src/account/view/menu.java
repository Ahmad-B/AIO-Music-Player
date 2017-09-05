/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Surya
 */
public class menu implements Initializable {

    @FXML
    private Pane menu;
    @FXML
    private Button username;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void settings(){
        Stage settings = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(menu.class.getResource("accountSettings.fxml"));
        Pane screen = null;
        
        try {
            screen = (Pane) loader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Scene scene = new Scene(screen);
        settings.setTitle("Options!");
        settings.setScene(scene);
        settings.show();
    }
}
