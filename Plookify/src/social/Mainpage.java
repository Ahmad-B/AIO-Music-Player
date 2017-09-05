
package social;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import social.gui.GUIController;


public class Mainpage extends Application {
     private Stage primaryStage;
     private BorderPane root;
     
public Mainpage(){

}
     
 public void start(Stage primaryStage) {
       this.primaryStage= primaryStage;
       this.primaryStage.setTitle("Social");
        initRoot();
        mainGUI();

 }
 
  /**
     * Initializes the root layout.
     */
    public void initRoot() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainpage.class.getResource("gui/Root.fxml"));
            root = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 //return the main stage
 public Stage getPrimaryStage() {
        return primaryStage;
    }
   
public void mainGUI()
{
try{
  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui/GUI.fxml"));
         AnchorPane page = (AnchorPane) loader.load();
      
        root.setCenter(page);
        GUIController controller = loader.getController();
        controller.setMainpage(this);
        
}catch (IOException e) {
        e.printStackTrace();
        
    }
}
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
