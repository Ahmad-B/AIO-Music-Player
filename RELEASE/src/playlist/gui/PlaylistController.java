package playlist.gui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import playlist.logic.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

/**
 * FXML PlaylistController class
 *
 * @author ec14373
 */
public class PlaylistController implements Initializable {

    @FXML
    private TextField playlistName;
    @FXML
    private Label createdBy;
    @FXML
    private Label createdByText;
    @FXML
    private TableView playlistView;
    @FXML
    private TableColumn song;
    @FXML
    private TableColumn artist;
    @FXML
    private TableColumn added;
    @FXML
    private MenuButton permissions;
    @FXML
    private MenuItem friendPermissions;
    @FXML
    private TableView trackView;
    @FXML
    private TableColumn add;
    @FXML
    private TableColumn Asong;
    @FXML
    private TableColumn Agenre;
    @FXML
    private Button play;
    @FXML
    private Separator separator;
    @FXML
    private TextField searchArtist;
    private Search search;
    private Playlist currentPlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // on load 
        Playlists playlists = new Playlists();
        // check to see if user has a playlist if they do show all controls on gui
        try {
            currentPlaylist = playlists.getPlaylists().get(0);
            // show controls on gui
            playlistName.setEditable(true);
            createdByText.setVisible(true);
            createdBy.setVisible(true);
            play.setVisible(true);
            permissions.setVisible(true);
            searchArtist.setVisible(true);
            trackView.setVisible(true);
            playlistView.setVisible(true);
            separator.setVisible(true);
            // set controls to selected playlist details
            playlistName.setText(currentPlaylist.getName());
            createdBy.setText(currentPlaylist.getCreatorName());
            // set permissions
            setPermissions();
            setPlaylistView();
            setTrackView();
            playlistView.setItems(currentPlaylist.getPlaylistData());

            // adding context menu to playlist view
            final ContextMenu contextMenu = new ContextMenu();
            MenuItem del = new MenuItem("Delete");
            contextMenu.getItems().addAll(del);
            del.setOnAction(new EventHandler<ActionEvent>() {
                // remove selected track from playlist
                @Override
                public void handle(ActionEvent event) {
                    int selection = playlistView.getSelectionModel().getSelectedIndex();
                    if (selection != -1) {
                        Track selectedTrack = currentPlaylist.getPlaylistData().get(selection);
                        currentPlaylist.removeTrack(selectedTrack.getId()); // remove track from current playlist
                        // update playlist view
                        playlistView.setItems(currentPlaylist.getPlaylistData());
                    }
                }
            });
            // set context menu
            playlistView.setContextMenu(contextMenu);
        } catch (NullPointerException ex) {
            // hide controls on gui
            playlistName.setEditable(false);
            createdByText.setVisible(false);
            createdBy.setVisible(false);
            play.setVisible(false);
            permissions.setVisible(false);
            searchArtist.setVisible(false);
            trackView.setVisible(false);
            playlistView.setVisible(false);
            separator.setVisible(false);
            currentPlaylist = new Playlist(-1, "No Playlists");
        }
    }

    /*
     * loads the controller class with selected playlist
     * if its a friend playlist do not show edit options to user
     */
    public void initPlaylist(Playlist playlist, boolean isFriendPlaylist) {
        currentPlaylist = playlist;
        // check to see if playlist selected is a friends playlist
        if (isFriendPlaylist && currentPlaylist != null) {
            // hide controls that user can't do on a friends playlists
            playlistName.setEditable(false);
            permissions.setVisible(false);
            searchArtist.setVisible(false);
            separator.setVisible(false);
            trackView.setVisible(false);
            playlistName.setVisible(true);
            createdByText.setVisible(true);
            createdBy.setVisible(true);
            play.setVisible(true);
            playlistView.setVisible(true);
            playlistView.setPrefWidth(550);
            playlistView.setLayoutX(50);
            song.setPrefWidth(180);
            artist.setPrefWidth(174);
            added.setPrefWidth(170);
            playlistName.setText(currentPlaylist.getName()); // sets playlist name from db
            playlistName.positionCaret(currentPlaylist.getName().length()); // sets pointer to end of string
            createdBy.setText(currentPlaylist.getCreatorName());
            setPlaylistView();
            playlistView.setItems(currentPlaylist.getPlaylistData());
        } else if (currentPlaylist != null) {
            // show controls that user can do on their own playlists
            playlistView.setPrefWidth(290);
            playlistView.setLayoutX(302);
            song.setPrefWidth(96);
            artist.setPrefWidth(91);
            added.setPrefWidth(90);
            // show controls on gui
            playlistName.setEditable(true);
            createdByText.setVisible(true);
            createdBy.setVisible(true);
            play.setVisible(true);
            permissions.setVisible(true);
            searchArtist.setVisible(true);
            trackView.setVisible(true);
            playlistView.setVisible(true);
            separator.setVisible(true);
            playlistName.setText(currentPlaylist.getName()); // sets playlist name from db
            playlistName.positionCaret(currentPlaylist.getName().length()); // sets pointer to end of string
            createdBy.setText(currentPlaylist.getCreatorName());
            // set permissions
            setPermissions();
            setPlaylistView();
            setTrackView();
            playlistView.setItems(currentPlaylist.getPlaylistData());

            // adding context menu to playlist view
            final ContextMenu contextMenu = new ContextMenu();
            MenuItem del = new MenuItem("Delete");
            contextMenu.getItems().addAll(del);
            del.setOnAction(new EventHandler<ActionEvent>() {
                // remove selected track from playlist
                @Override
                public void handle(ActionEvent event) {
                    int selection = playlistView.getSelectionModel().getSelectedIndex();
                    if (selection != -1) {
                        Track selectedTrack = currentPlaylist.getPlaylistData().get(selection);
                        currentPlaylist.removeTrack(selectedTrack.getId()); // remove track from current playlist
                        // update playlist view
                        playlistView.setItems(currentPlaylist.getPlaylistData());
                    }
                }
            });
            // set context menu
            playlistView.setContextMenu(contextMenu);
        } else {
            // hide controls on gui
            playlistName.setEditable(false);
            playlistName.setText("No Playlists");
            createdByText.setVisible(false);
            createdBy.setVisible(false);
            play.setVisible(false);
            permissions.setVisible(false);
            searchArtist.setVisible(false);
            trackView.setVisible(false);
            playlistView.setVisible(false);
            separator.setVisible(false);
        }
    }

    /*
     * sets the playlist tableview to values in database
     */
    private void setPlaylistView() {
        // sets playlistView columns to database columns
        song.setCellValueFactory(new PropertyValueFactory<>("name"));
        artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        added.setCellValueFactory(new PropertyValueFactory<>("added"));
    }

    /*
     * sets the track tableview to values in database and adds button
     */    
    private void setTrackView() {
        // add button
        add.setCellFactory(
                new Callback<TableColumn<Playlist, Boolean>, TableCell<Playlist, Boolean>>() {

                    @Override
                    public TableCell<Playlist, Boolean> call(TableColumn<Playlist, Boolean> p) {
                        return new AddButton();
                    }

                });

        // sets trackView columns to database columns
        Asong.setCellValueFactory(new PropertyValueFactory<>("name"));
        Agenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    /*
     * defines the button to be added
     */
    private class AddButton extends TableCell<Playlist, Boolean> {

        final Button cellButton = new Button("+");

        AddButton() {
            // set sizing
            cellButton.setMaxSize(20.0, 20.0);
            cellButton.setMinSize(20.0, 20.0);
            // set id to reference in css
            cellButton.setId("addButton");
            // on click add selected track to current playlist
            cellButton.setOnAction((ActionEvent t) -> {
                // add track to playlist
                int selectedIndex = getTableRow().getIndex();
                Track selectedTrack = (Track) trackView.getItems().get(selectedIndex);
                currentPlaylist.addTrack(selectedTrack.getId());
                playlistView.setItems(currentPlaylist.getPlaylistData());
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    /*  
     *  set permissions of current playlist
     */
    public void setPermissions() {
        // get current permissions
        String currentPermissions = currentPlaylist.getPermissions();
        // set menu accordingly 
        if (currentPermissions.equals("Private") || currentPermissions.equals("private")) {
            permissions.setText("Private");
            friendPermissions.setText("Friend");
        } else {
            permissions.setText("Friend");
            friendPermissions.setText("Private");
        }

    }

    /*  
     *  change permissions of current playlist
     */
    public void changePermissions() {
        // checks to see what the permissions are already set as
        if (permissions.getText().equals("Private")) {
            // if private permissions
            currentPlaylist.changePermissions("Friend");
            permissions.setText("Friend");
            friendPermissions.setText("Private");
        } else {
            // if friend permissions
            currentPlaylist.changePermissions("Private");
            permissions.setText("Private");
            friendPermissions.setText("Friend");
        }
    }

    /*
     *  search track database by artist
     */
    public void searchArtist() {
        search = new Search();
        setTrackView();
        trackView.setItems(search.searchByArtist(searchArtist.getText()));
    }

    /*
     * add current playlist to now playing 
     */
    public void play() {
        currentPlaylist.addToNowPlaying();
    }

    /*
     * update the playlist name in database
     */
    public void updatePlaylistName() {
        currentPlaylist.updateName(playlistName.getText());
        // boolean used to notify main thread that update is needed
        common.gui.FrontController.updateNeeded = true;
    }

}
