/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package account.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sr322
 */
public class addDevice implements Initializable{
    @FXML
    private Button add;
    @FXML
    private TextField field;
    
    public Label toEdit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setLabel(Label l){
        toEdit = l;
    }
    @FXML
    public void text(){
        if (field.getText().equals(""))
            field.setText("Cannot be empty!");
        else{
            toEdit.setText(field.getText());
            Stage s = (Stage) add.getScene().getWindow();
            s.close();
        }   
    }
    
}
