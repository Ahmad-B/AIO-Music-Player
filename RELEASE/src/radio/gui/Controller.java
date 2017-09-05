/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import java.net.URL;

import java.util.*;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import radio.logic.*;
import radio.logic.objects.*;

/**
 *
 * @author ec14071
 */
public class Controller implements Initializable {

    /**
     * declaring FXML variables of button type genButton - purpose is to
     * generate playlist to a view. saveButton - purpose is to save the
     * generated playlist to a subscribed customers playlists.
     */
    public Button genButton;
    public Button saveButton;

    /**
     * declaring anchor pane variable of FXML type results
     */
    public AnchorPane results;
    /**
     * declaring FXML variables of TextField type artist - to allow user input
     * via text for a artist name from user. playlistname - to allow user input
     * for the name they wish to save a generated playlist for.
     */
    public TextField artist;
    public TextField playlistname;

    /**
     * declaring FXML variables of TableView type results
     */
    @FXML
    public TableView<radio.logic.objects.Artist> searchResults;
    @FXML
    public TableView<radio.logic.objects.Row> playlist;

    @FXML
    public TableColumn sname;
    @FXML
    public TableColumn aname;
    @FXML
    public TableColumn resultColumn;

    /**
     * declaring warnings
     */
    public Label warning;
    public Label warning2;
    public Label warning3;
    public Label warning4;
    public Label warning5;
    public Pane err;
    public Label saved;
    public MenuItem menuItem;
    public MenuButton menuButton;

    private boolean isPrivate = true;

    private final ObservableList<radio.logic.objects.Row> table = FXCollections.observableArrayList();
    private ObservableList<Artist> suggestions = FXCollections.observableArrayList();

    /**
     * declaring objects
     */
    private RadioManager manager = new RadioManager();
    private RadioStation station;

    public void handleGenerate() {
        //RESET VIEW

        sname.setCellValueFactory(new PropertyValueFactory("songName"));
        aname.setCellValueFactory(new PropertyValueFactory("artistName"));

        table.clear();

        if (err.isVisible()) {
            err.setVisible(false);
        }

        //CHECK FOR EMPTY INPUT
        if (warning.isVisible()) {
            warning.setVisible(false);
        }
        if (artist.getText().equals("")) {
            warning.setVisible(true);
            playlist.setPlaceholder(warning);
        }
        if (warning2.isVisible()) {
            warning2.setVisible(false);
        }
        if (warning3.isVisible()) {
            warning3.setVisible(false);
        }
        if (warning4.isVisible()) {
            warning4.setVisible(false);
        }

        if (saved.isVisible()) {
            saved.setVisible(false);
        }

        //REQUEST PROCESS
        if (!artist.getText().equals("")) {

            //REQUEST RADIO STATION
            boolean check = manager.createStation(artist.getText().toLowerCase());
            station = manager.getStation();

            //UPDATE THE VIEW
            if (check) {

                HashMap<String, ArrayList<Track>> map = station.browseTracks();

                for (String key : map.keySet()) {
                    //ADD ARTIST TO VIEW
                    ArrayList<Track> tracks = map.get(key);

                    for (int i = 0; i < tracks.size(); i++) {

                        radio.logic.objects.Row row = new radio.logic.objects.Row("artistName", "songName");
                        //ADD TRACKS STORED IN STATION BY THE CURRENT ARTIST INTO VIEW

                        row.artistName.set(key);
                        row.songName.set(tracks.get(i).Name);

                        table.add(row);
                    }
                }
                playlist.setItems(table);
            } else {
                if (!suggestions.isEmpty()) {
                    playlist.setPlaceholder(new Label("artist not found try searching " + suggestions.get(0).Name + " instead?"));
                    suggestions.clear();
                    return;
                }
                artist.setText("");
                warning3.setVisible(true);
                playlist.setPlaceholder(warning3);
            }

        }

        suggestions.clear();
    }

    public void handleSave() {
        suggestions.clear();          
        if (err.isVisible()) {
            err.setVisible(false);
        }

        if (warning.isVisible()) {
            warning.setVisible(false);
        }

        if (warning3.isVisible()) {
            warning3.setVisible(false);
        }
        if (warning2.isVisible()) {
            warning2.setVisible(false);
        }
        if (warning4.isVisible()) {
            warning4.setVisible(false);
        }

        if (saved.isVisible()) {
            saved.setVisible(false);
        }

        if (table.isEmpty()) {
            warning4.setVisible(true);
            playlist.setPlaceholder(warning4);
            return;
        } else if (playlistname.getText().trim().equals("")) {
            warning2.setVisible(true);
            return;
        }

        if (station.saveAsPlaylist(playlistname.getText(), isPrivate)) {
            table.clear();
            artist.setText("");
            playlistname.setText("");
            saved.setVisible(true);
            playlist.setPlaceholder(saved);

            common.gui.FrontController.updateNeeded = true;
        } else {
            table.clear();
            err.setVisible(true);
            playlist.setPlaceholder(err);
        }
    }

    public void playlistType() {
        if (menuButton.getText().equals("Private")) {
            menuButton.setText("Friend");
            menuItem.setText("Private");
            isPrivate = false;
        } else {
            menuButton.setText("Private");
            menuItem.setText("Friend");
            isPrivate = true;
        }
    }

    public void onTyped() {
        suggestions = manager.search(artist.getText());
        if (artist.getText().equals("")) {
            suggestions.clear();
        }
        searchResults.setItems(suggestions);
    }

    public void onSelectionClick() {
        try {
            if (suggestions.isEmpty()) {
                return;
            }
            artist.setText(searchResults.getSelectionModel().getSelectedItem().Name);
        } catch (Exception e) {/*if empty cell exists*/}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("Name"));

        if (!common.Customer.getCurrentCustomer().isSubscribed()) {
            warning5.setVisible(true);
            playlist.setPlaceholder(warning5);
            genButton.setDisable(true);
            saveButton.setDisable(true);
        } else {
            playlist.setPlaceholder(new Label("Welcome to radio! Start by searching an \n artist to generate your random playlist!"));
        }

        searchResults.setPlaceholder(new Label(""));

    }

}
