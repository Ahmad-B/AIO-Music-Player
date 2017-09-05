package common.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import playlist.gui.PlaylistController;
import playlist.logic.Playlist;
import playlist.logic.Playlists;
import social.Mainpage;
import social.gui.GUIController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FrontController implements Initializable {

    public static boolean updateNeeded = false;
    public static boolean socialUpdateNeeded = false;
    private boolean trackdbopen = false;
    private boolean isFirst = false;
    public Pane current;
    public Pane social;
    public Pane nowPlaying;
    public Pane player;
    public Pane accountBar;
    @FXML
    private TableView playlistNames;
    @FXML
    private TableColumn playlistName;
    @FXML
    private TableView friendsPlaylists;
    @FXML
    private TableColumn friendsPNames;
    @FXML
    private Button create;
    @FXML
    private TextField createPlaylist;
    @FXML
    private Button username;
    private FXMLLoader playlistLoader;
    private Pane page;
    private Playlists playlists;
    private int playlistID;
    private String createPName;
    
    @FXML
    ImageView logo;
    Pane page2 = null;
    player.MainGUIController playerControl = new player.MainGUIController();

    @FXML
    public void changeName(String name) {
        username.setText(name);
    }

    private void setPlaylistNames() {
        playlists = new Playlists();
        // get arraylist of all playlist names
        ArrayList<Playlist> list = playlists.getPlaylists();
        playlistName.setCellValueFactory(new PropertyValueFactory<>("name"));

        if (list != null) {
            // convert arraylist to observablelist
            ObservableList<Playlist> playlistsNames = FXCollections.observableArrayList(list);
            // sets playlist names
            playlistNames.setItems(playlistsNames);
            deletePlaylist();
            if (isFirst) {
                isFirst = false;
                playlistNames.getSelectionModel().select(0); // have first row in table selected on load
            } else {
                // select current playlist
                int currentPlaylistID = 0;
                for(int i=0; i<list.size(); i++) {
                    if(list.get(i).getName().equals(createPName)) {
                        currentPlaylistID = i;
                    }
                }
                playlistNames.getSelectionModel().select(currentPlaylistID); // have first row in table selected on load
            }
            // change main pane to first playlist in table
            Playlist playlist = (Playlist) playlistNames.getSelectionModel().getSelectedItem();
            PlaylistController controller = playlistLoader.<PlaylistController>getController();
            controller.initPlaylist(playlist, false);
            current.getChildren().clear();
            current.getChildren().add(page);
        } else {
            // no playlists in tableview
            // set tableview to null
            playlistNames.setItems(null);
            // set main view
            PlaylistController controller = playlistLoader.<PlaylistController>getController();
            controller.initPlaylist(null, false);
            current.getChildren().clear();
            current.getChildren().add(page);
        }
        // set friend playlists
        ArrayList<Playlist> friendList = playlists.getFriendPlaylists();
        if (friendList != null) {
            // convert arraylist to observablelist
            ObservableList<Playlist> playlistsNames = FXCollections.observableArrayList(friendList);
            // sets PlayistNames columns to database columns
            friendsPNames.setCellValueFactory(new PropertyValueFactory<>("name"));
            friendsPlaylists.setItems(playlistsNames);
        }
    }

    public void onClick() throws IOException {
        page2.setVisible(true);
        trackdbopen = false;
        // loads new playlist information to main pane
        friendsPlaylists.getSelectionModel().clearSelection(); // remove selection
        Playlist playlist = (Playlist) playlistNames.getSelectionModel().getSelectedItem();
        PlaylistController controller = playlistLoader.<PlaylistController>getController();
        controller.initPlaylist(playlist, false);
        current.getChildren().clear();
        current.getChildren().add(page);
    }

    public void onFriendClick() throws IOException {
        // loads friends playlist information to main pane
        playlistNames.getSelectionModel().clearSelection(); // remove selection
        Playlist playlist = (Playlist) friendsPlaylists.getSelectionModel().getSelectedItem();
        PlaylistController controller = playlistLoader.<PlaylistController>getController();
        controller.initPlaylist(playlist, true);
        current.getChildren().clear();
        current.getChildren().add(page);
    }

    public void createPlaylistClick() {
        page2.setVisible(true);
        trackdbopen = false;
        // add playlist to database
        if (!createPlaylist.getText().equals("")) {
            createPName = createPlaylist.getText();
            playlists.addPlaylist(createPName);
            createPlaylist.setText("");
            setPlaylistNames();
        }
    }

    public void deletePlaylist() {
        // adding context menu to playlist view
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem del = new MenuItem("Delete");
        contextMenu.getItems().addAll(del);
        del.setOnAction(new EventHandler<ActionEvent>() {
            // remove selected track from playlist
            @Override
            public void handle(ActionEvent event) {
                Playlist selectedPlaylist = playlists.getPlaylists().get(playlistNames.getSelectionModel().getSelectedIndex());
                playlists.deletePlaylist(selectedPlaylist.getID()); // remove track from current playlist     
                // update playlist view
                setPlaylistNames();

            }
        });
        // set context menu
        playlistNames.setContextMenu(contextMenu);
    }

    public void radioClick() throws IOException {
        page2.setVisible(true);
        trackdbopen = false;
        current.setMinSize(0, 0);
        Pane page = (Pane) FXMLLoader.load(getClass().getResource("/radio/gui/RadioGUI.fxml"));
        current.getChildren().clear();
        current.getChildren().add(page);
    }

    @FXML
    private void accountDetails() {
        current.setMinSize(0, 0);
        Pane page;
        try {
            page = (Pane) FXMLLoader.load(getClass().getResource("/account/view/accountSettings.fxml"));
            current.getChildren().clear();
            current.getChildren().add(page);
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void trackClick() throws IOException {
        playerControl.stop = true;
        page2.setVisible(false);
        current.setMinSize(0, 0);
        Pane page = (Pane) FXMLLoader.load(getClass().getResource("/player/trackDatabase.fxml"));
        current.getChildren().clear();
        current.getChildren().add(page);
        trackdbopen = true;
    }

    public void trackClose() throws IOException {
        if (trackdbopen) {
            playlistLoader = new FXMLLoader(getClass().getResource("/playlist/gui/PlaylistGUI.fxml"));
            page = (Pane) playlistLoader.load();
            current.getChildren().add(page);
            page2.setVisible(true);
            trackdbopen = false;
        }
    }
    
    public void setSocial() throws IOException
    {
           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/social/gui/GUI.fxml"));

            AnchorPane socPage = (AnchorPane) loader.load();

            social.getChildren().add(socPage);
            GUIController controller = loader.getController();
            Mainpage mpage = new Mainpage();
            controller.setMainpage(mpage);
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page = null;

        playlistLoader = new FXMLLoader(getClass().getResource("/playlist/gui/PlaylistGUI.fxml"));
        Image image = new Image("http://se38.x10.mx/music/test.png");
        logo.setImage(image);

        try {

            //load track module
            page2 = (Pane) FXMLLoader.load(getClass().getResource("/player/mainGUI.fxml"));

            //load social module
            setSocial();
            //end load
            //load playlist module
            playlistLoader = new FXMLLoader(getClass().getResource("/playlist/gui/PlaylistGUI.fxml"));
            page = (Pane) playlistLoader.load();
            //add loaded panes
            current.getChildren().add(page);
            player.getChildren().add(page2);

        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, e);
        }

        // create thread
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // thread sleeps
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("Exeception: " + ex);
                    }
                    // updates playlist view
                    if (updateNeeded) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                playlistID = playlistNames.getSelectionModel().getSelectedIndex();
                                setPlaylistNames();
                            }
                        });
                        updateNeeded = false;
                    }
                    
                     if (socialUpdateNeeded) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    setSocial();
                                } catch (IOException ex) {
                                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        socialUpdateNeeded = false;
                    }
              
                }
            }
        };
        t.setDaemon(true);
        t.start();
        // on first load
        isFirst = true;
        setPlaylistNames();

    }

}
