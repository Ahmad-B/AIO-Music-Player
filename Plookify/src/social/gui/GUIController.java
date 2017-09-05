
package social.gui;

import common.Database;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import social.Mainpage;
import social.logic.Customer;
import social.logic.Request;
import social.logic.friendManager;

/**
 *
 * @author home
 */
public class GUIController {
    @FXML
    private TableView<Customer> friendTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private MenuButton changePrivate;
    @FXML
    private MenuItem changePublic;
    @FXML
    private Button inboxBut;
     @FXML
    private Button seePlaylistBut;
     @FXML
    private Button deleteBut;
     @FXML
    private Button addBut;
      @FXML
    private Button friendlistBut;
     @FXML
    private AnchorPane friendlistPane;  // friendtable belong to this class
     @FXML
    private AnchorPane currentPane;  // current table 
     
    // Reference to the main application.
    private Mainpage main;
    // Reference to the friendManager .
    private friendManager manager ;
    private Database db;
    private ObservableList<Customer> Names = FXCollections.observableArrayList(); // Friends name
   
   
    public GUIController()
    {
      manager = new friendManager() ;
      db = Database.getInstance();
      Names = manager.getNames();
    }
    
    @FXML
    private void initialize() {
        
	// Initialize the  table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
 
        // check customer is a paid subscription
         if (!common.Customer.getCurrentCustomer().isSubscribed()) {
             friendTable.setPlaceholder(new Label("    To use this service \nyou must be subscribed!"));
             // set all button to invisible
             inboxBut.setVisible(false);
             seePlaylistBut.setVisible(false);
             deleteBut.setVisible(false);
             addBut.setVisible(false);
             friendlistBut.setVisible(false);
             
         }
         
         else{
             
            //Add names to the table
            Names = manager.getNames();
            if(Names.size()==0)
                friendTable.setPlaceholder(new Label("Add some friends"));
            
            friendTable.setItems(Names);
            
            changePrivate.setText(manager.getStatus());
            if(changePrivate.getText().equals("Private"))
                 changePublic.setText("Public");
            else
                changePublic.setText("Private");
            
         }
	}
       
        // // when the user clicks on the menubutton to change status. 
    @FXML
    private void statusType() {
        if (changePrivate.getText().equals("Private")) {
            
            changePrivate.setText("Public");
            changePublic.setText("Private");
            
        } else {
            changePrivate.setText("Private");
            changePublic.setText("Public");
        }
         manager.changeStatus(changePrivate.getText());	//update to database change isDiscoverable 
    }
   
	// when the user clicks on the delete button.
	 
    @FXML
    private void handleDeleteFriend() {
        if(friendlistPane.isVisible())
        {
            int selectedIndex = friendTable.getSelectionModel().getSelectedIndex();
                
		if (selectedIndex >= 0) {
                         Alert alert = new Alert(AlertType.CONFIRMATION);
                         alert.setTitle("Delete friend");
                         alert.setHeaderText("Confirmation to delete friend");
                         
                         //alert css
                          DialogPane dialogPane = alert.getDialogPane();
                          dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
                          
                         
                         Optional<ButtonType> result = alert.showAndWait();
                         if (result.get() == ButtonType.OK){
                             
                             // get friendID
                                 int id = friendTable.getItems().get(selectedIndex).getId();
                                 // remove friend from database
                                 manager.removeFriend(id);
                                 // remove friend from table
                                 friendTable.getItems().remove(selectedIndex);
                            
                         } 
                             // ... user chose CANCEL or closed the dialog                  
                    
		} else {
			// Nothing selected
			 Alert alert = new Alert(AlertType.WARNING);
                         alert.setTitle("No Selection");
                         alert.setHeaderText("No Friend Selected");
                         alert.setContentText("Please select a friend in friendlist table.");
                         alert.showAndWait();
		}
	}else{
        // not on friendlist page
			 Alert alert = new Alert(AlertType.WARNING);
                         alert.setTitle("No Selection");
                         alert.setHeaderText("No Friend Selected");
                         alert.setContentText("Please select a friend in friendlist table.");
                         alert.showAndWait();
        
        }
    }
       
 //  when the user clicks the add button.
 
    @FXML
    private void handleAddFriend() {
       
        // To get AddFriendpage;
     
      friendlistPane.setVisible(false);
      try {
            // get adding friend page to display
            Pane addPage = (Pane) FXMLLoader.load(getClass().getResource("AddFriend.fxml"));
            currentPane.setMinSize(0, 0);
            currentPane.getChildren().clear();
            currentPane.getChildren().add(addPage);
           
         
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
}
    
    //  when the user clicks the inbox button.
 
    @FXML
    private void handleInbox() {
       
        // To get Inbox page;
        friendlistPane.setVisible(false);
      try {
            AnchorPane inboxPage = (AnchorPane) FXMLLoader.load(getClass().getResource("Inbox.fxml"));
            currentPane.getChildren().clear();
            currentPane.getChildren().add(inboxPage);
         
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
}
      //  when the user clicks the friendlist button.
    @FXML
    private void handleFriendlist() {
  
       Names = manager.getNames();
        friendTable.setItems(Names);
     friendlistPane.setVisible(true);
     
}
      //  when the user clicks the playlist button.
     @FXML
    private void handleSeePlaylist(){
      friendlistPane.setVisible(false);
    
        try {
           AnchorPane playlistPage = (AnchorPane) FXMLLoader.load(getClass().getResource("FriendPlaylist.fxml"));
            currentPane.getChildren().clear();
            currentPane.getChildren().add(playlistPage);
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public ObservableList getNames()
    {
        return Names;
    }
     // give reference to the mainpage
    public void setMainpage(Mainpage main)
    {
        this.main = main;
    }
   
}
