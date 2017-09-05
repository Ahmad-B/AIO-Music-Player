/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist;

import static javafx.application.Application.launch;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ec14373
 */


// TODO: need to run application from this file
public class ComponentLoader extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playlist/gui/PlaylistGUI.fxml"));
        primaryStage.setTitle("Playlist");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false); 
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
