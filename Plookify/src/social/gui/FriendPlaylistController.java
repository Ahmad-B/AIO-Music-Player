
package social.gui;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import playlist.logic.Playlist;
import playlist.logic.Track;
import social.logic.Customer;
import social.logic.Friendplaylist;
import social.logic.friendManager;
/**
 * FXML Controller class
 *
 * @author home
 */
public class FriendPlaylistController {

    private ObservableList<Playlist> playlistsName;
    private ObservableList<Track> tracksName;
    private Playlist selectedPlaylist;
    private friendManager manager;
   
    @FXML
    private TextField searchName;
    @FXML
    private TableView<Customer> friendName;
    @FXML
    private TableView<Playlist> playlists;
    @FXML
    private TableView<Track> tracks;
    @FXML
    private TableColumn showPlaylists;
    @FXML
    private TableColumn showFriendName;
    @FXML
    private TableColumn showTracksName;
   

    public FriendPlaylistController(){
      
        manager = new friendManager();
   
    }
     /**
     * Initializes the controller class.
     */
    public void initialize() {
       
      showPlaylists.setCellValueFactory(new PropertyValueFactory<Playlist,String>("name"));
      showTracksName.setCellValueFactory(new PropertyValueFactory<Track,String>("name"));
      showFriendName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
      
      friendName.setPlaceholder(new Label("Search friend to view playlist!"));
      playlists.setPlaceholder(new Label("No selected \n   friend"));
      tracks.setPlaceholder(new Label("No selected\n  playlist"));
          
    
    } 
    //Search friend in friendlist
    @FXML 
    private void search()
    {
     ObservableList<Customer> friendNameList = manager.searchFriend(searchName.getText());
        if(friendNameList.size()==0)
        {
            friendName.setPlaceholder(new Label("Not on your friendlist"));
        }
      friendName.setItems(manager.searchFriend(searchName.getText()));
    }
    // User clicks playlist in playlist table
    @FXML
	private void handleSelectplaylist() {
		int selectedIndex = playlists.getSelectionModel().getSelectedIndex();
                
		if (selectedIndex >= 0) {
                        // get playlist
                        selectedPlaylist = playlists.getItems().get(selectedIndex);
                       
                       // view tracks in that playlist
                        ObservableList<Track> tracksName = selectedPlaylist.getPlaylistData();
                        if(tracksName.size()==0)
                        {
                           tracks.setPlaceholder(new Label(" No public\n   track\n available!"));
                        }
			tracks.setItems(tracksName);
                }
	}
        
         // User clicks name in name table
      @FXML
	private void handleSelectfriend() {
		int selectedIndex = friendName.getSelectionModel().getSelectedIndex();
                int id ;
		if (selectedIndex >= 0) {
                        // get playlist
                        id = friendName.getItems().get(selectedIndex).getId();
                        
                        setupPlaylist(id);
                       
		}
	}
        
          //display playlists from selected friend name
        private void setupPlaylist(int id)
        {
                               
             ArrayList<Playlist> list = (new Friendplaylist(id)).getPlaylists();
             playlistsName = FXCollections.observableArrayList(list);
             
             if(list.size()==0)
            { 
                tracksName = FXCollections.observableArrayList();
                playlists.setPlaceholder(new Label(" No public\n   playlist\n available!"));
                tracks.setPlaceholder(new Label(" No public\n   track\n available!"));
                tracks.setItems(tracksName);
                playlists.setItems(playlistsName);
            }
            else{
              
            // show all friendlist
            playlists.setItems(playlistsName);
           // initially show tracks of the first playlist
            tracksName = playlistsName.get(0).getPlaylistData();
            tracks.setItems(tracksName);

            }
      
       }
    
}
