
package social.gui;

import common.Database;
import java.net.URL;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import social.logic.Customer;
import social.logic.Request;
import social.logic.friendManager;

/**
 * FXML Controller class
 *
 * @author home
 */
public class InboxController {

     @FXML
    private TableView<Request> messageTable;
    @FXML
    private TableColumn<Request, String> friendNameColumn;
    @FXML
    private TableColumn<Request, String> dateColumn;
    
    ObservableList<Request> NamesInbox = FXCollections.observableArrayList();
    private Database db;
    private Stage dialogStage;
    private friendManager manager;
    
    public InboxController()
    {
       db = Database.getInstance();
       manager = new friendManager() ;
      
    }
    
    
    public void initialize(){
        //initialize table
        friendNameColumn.setCellValueFactory(new PropertyValueFactory<Request, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Request, String>("date"));
        
        NamesInbox= manager.updateInbox();
        messageTable.setItems(NamesInbox);
        if(NamesInbox.size()==0)
            messageTable.setPlaceholder(new Label("No friend request message"));
        
        
    }    
    
    // when the user select friend name the table.
	 
	@FXML
	private void handleView() {
		int selectedIndex = messageTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
                    Request request = messageTable.getItems().get(selectedIndex);
                    String name = request.getName();
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Message from: "+ name);
                    alert.setContentText(request.getMessage()+"\nPlease make your decision");
                    
                    //set css 
                     DialogPane dialogPane = alert.getDialogPane();
                     dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());

                    ButtonType buttonTypeOne = new ButtonType("Accept");
                    ButtonType buttonTypeTwo = new ButtonType("Decline");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        // ... user accept friend request
                        manager.makeDecision(1,request);
                        messageTable.getItems().remove(selectedIndex);
                        common.gui.FrontController.updateNeeded = true;
                    } else if (result.get() == buttonTypeTwo) {
                        // ... user decline friend request
                        manager.makeDecision(0,request);
                        messageTable.getItems().remove(selectedIndex);
                    } else{
                    
                    }
                  
                    
		} else {
			// Nothing selected
			 Alert alert = new Alert(Alert.AlertType.WARNING);
                         alert.setTitle("No Selection");
                         alert.setHeaderText("No Name Selected");
                         alert.setContentText("Please select a name to view message.");
                        
                         alert.showAndWait();
		}
	}
   
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
