/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Surya
 */
public class errorWindow implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Label messageY;
    @FXML
    private Label messageZ;
    @FXML
    private Label message4;
    
    private Label[] labels = {message, messageY, messageZ};
    
    @FXML
    private Button closeWindow;
    
    private String[] messages = {"", "", "", ""};
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     *
     * @param text
     */
    @FXML
    public void setMessage(ArrayList<String> errors){
        for (int i = 0; i<errors.size(); i++){
            messages[i] = errors.get(i);
        }
        
        message.setText(messages[0]);
        messageY.setText(messages[1]);
        messageZ.setText(messages[2]);
        message4.setText(messages[3]);
    }
    
    /**
     *
     */
    @FXML
    public void closeWindow(){
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }
}
