
package social.gui;
import common.Database;
import java.sql.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import social.logic.Customer;
import social.logic.friendManager;

/**
 *
 * @author home
 */
public class AddFriendController {
    
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Customer> searchTable;
    @FXML
    private TableColumn<Customer, String> friendNameColumn;
    
    private ObservableList<Customer> displayNames = FXCollections.observableArrayList();
    private friendManager manager;
    private Database db;
   
   
    public AddFriendController()
    {
                db = Database.getInstance();
                manager = new friendManager();

    }

     @FXML
    private void initialize() {
        // Initialize the  table
    friendNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
    searchTable.setPlaceholder(new Label("Find some friends!"));
      
    }
    
    /**
     * when the user clicks search.
     */
    @FXML
    private void handleSearch() {
      String name = searchField.getText();
      search(name);
      
    }
  
     // search by given a name using search method in friendmanager
    public void search(String name)
    {
        displayNames.removeAll(displayNames);
        displayNames = manager.searchCustomer(name);
        searchTable.setItems(displayNames);
           if(displayNames.size()==0)
                  searchTable.setPlaceholder(new Label("No friend found!"));
                  
    }
    
 
    
  /**
     * Called when the user clicks add.
     */
    @FXML
    private void handleAdd() {
      
         int selectedIndex = searchTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
                       int customerID = displayNames.get(selectedIndex).getId();
                       String name = displayNames.get(selectedIndex).getName();
                       
                       if(manager.checkFriend(customerID)){
                            boolean isOKClicked = sendMessage(customerID,name);
                            if(isOKClicked)
                                manager.addFriend(customerID);
                          
                       } else{
                          // selected friend already been added
			 Alert alert = new Alert(AlertType.WARNING);
                         alert.setTitle("Friend");
                         alert.setHeaderText("Friend has already been added ");
                         alert.showAndWait();
                       
                       }
                       
		} 
               
    }
  
    // Sending message must be done in order to add friend 
    private boolean sendMessage(int id,String name)
    {
        TextInputDialog dialog = new TextInputDialog("Friend Request Message");
        dialog.setTitle("Request Friend");
        dialog.setHeaderText("Message request");
        dialog.setContentText("Enter a message to " +name);
        
         //set css 
           //  DialogPane dialogPane = dialog.getDialogPane();
           //  dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {  
          
            manager.friendRequest(id,result.get());
            return true; //ok clicked
        }
        
      return false; // cancel clicked   
    
    }
   

}

 
