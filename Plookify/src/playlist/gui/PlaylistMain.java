package playlist.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author brads
 */
public class PlaylistMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PlaylistGUI.fxml"));
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
