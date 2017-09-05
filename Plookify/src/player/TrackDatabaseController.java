/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import common.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import common.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TrackDatabaseController implements Initializable {

    static player.logic.Logic logic = new player.logic.Logic();
    MainGUIController mgc = new MainGUIController();
    static Stage thisStage;
    private Database db;
    @FXML ChoiceBox choice;
    @FXML TextField query;
    @FXML ListView listTracks;
    @FXML ListView listPlaylist;
    ArrayList<String> artists = new ArrayList<String>();
    ArrayList<String> genres = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList <Integer> ids = new ArrayList<Integer>();
    Random rand = new Random();
    ObservableList <String> choices = FXCollections.observableArrayList("Artist", "Genre", "Name");
    ObservableList<String> display = FXCollections.observableArrayList();
    ObservableList<String> temp = FXCollections.observableArrayList();
    public ObservableList<String> playlist = FXCollections.observableArrayList();
    int namecount = 0, artistcount = 0, genrecount = 0;
    Customer customer = Customer.getCurrentCustomer();
    int customerID = customer.getId();
     
    //Searches the databse for songs by artist
    public void searchbyArtist(String query) {
        display.clear();
        listTracks.setItems(display);
        
        PreparedStatement sm = null;
        String st = "select TrackID, name, artist, trackPath, genre from Track where artist=?";
        try{
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, query);
            ResultSet rs = sm.executeQuery();
            
            while(rs.next()){
                artists.add(rs.getString("name") + " - " + rs.getString("artist"));
                display.add(artists.get(artistcount));
                artistcount++;
            }
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
      listTracks.setItems(display);
    }
    
    //Searches the databse for songs by genre
    public void searchbyGenre(String query1) {
        display.clear();
        listTracks.setItems(display);
        
        PreparedStatement sm = null;
        String st = "select TrackID, name, artist, trackPath, genre from Track where genre=?";
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, query1);
            ResultSet rs = sm.executeQuery();
            
            while(rs.next()){
                genres.add(rs.getString("name") + " - " + rs.getString("artist"));
                display.add(genres.get(genrecount));
                genrecount++;
            }
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        listTracks.setItems(display);
    } 
    
    //Searches the databse for songs by name
    public void searchbyName(String query2) {
        display.clear();
        listTracks.setItems(display);
        
        PreparedStatement sm = null;
        String st = "select TrackID, name, artist, trackPath, genre from Track where name=?";
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, query2);
            ResultSet rs = sm.executeQuery();
            
            while(rs.next()){
                names.add(rs.getString("name") + " - " + rs.getString("artist"));
                display.add(names.get(namecount));
                namecount++;
            }
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        listTracks.setItems(display);
     } 
    
    //Determines what to search songs by
    public void selected() {
        String select = choice.getValue().toString();
        if(select.equals("Artist")) {
            searchbyArtist(query.getText());
        }
        else if(select.equals("Genre")) {
            searchbyGenre(query.getText());
        }
        else if(select.equals("Name")) {
            searchbyName(query.getText());
        }
    }
    
    //Adds the selected track to playlist
    public void addSelected() {
        try {
        String query = listTracks.getSelectionModel().getSelectedItem().toString();
        if(existPlaylist(query) == false) {
            playlist.add(query);
        }
        addToIDS(query);
        
        for(int i=0; i<display.size(); i++) {
            if(query.equals(display.get(i))) {
                display.remove(i);
            }
        }
        listTracks.setItems(display);
        listPlaylist.setItems(playlist);
        mgc.empty = false;
        } catch(Exception e) {
            
        }
       
    }
    
    //Adds all the songs to the playlist
    public void addAll() {
        for(int i=0; i<display.size(); i++) {
            if(existPlaylist(display.get(i)) == false) {
                playlist.add(display.get(i));
                addToIDS(display.get(i));
            }
        }
        listPlaylist.setItems(playlist);
        display.clear();
        listTracks.setItems(null);
        mgc.empty = false;
    }
    
    //Adds the Ids of the songs to the arraylist
    public void addToIDS(String selected) {
        String[] parts = selected.split(" - ");
        ids.add(findIDDB(parts));
        
    }
    
    //Checks to see if the playlist exists
    public boolean existPlaylist(String query){
        for(int i=0; i<playlist.size(); i++) {
            if(query.equals(playlist.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    //Saves the playlist to the database
    public void savePlaylist() {
        
    PreparedStatement sm = null;
    String st = "DELETE FROM NowPlaying WHERE TrackID=? AND CustomerID=?";
    try {
        sm = db.getConnection().prepareStatement(st);
        for(int j=0; j<ids.size(); j++) {
                sm.setInt(1, ids.get(j));
                sm.setInt(2, customerID);
                sm.executeUpdate();
        }
        String st2 = "insert into NowPlaying(CustomerID, TrackID) values (?, ?)";
        for(int i=0; i<playlist.size(); i++) {
            String selected = playlist.get(i);
            String[] parts = selected.split(" - ");
            int trackID = findIDDB(parts);
            
            sm = db.getConnection().prepareStatement(st2);
            sm.setInt(1, customerID);
            sm.setInt(2, trackID);
            sm.executeUpdate();
        }
        mgc.updateNeeded = true;
    }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    //Removes a track from the arraylist and the database
    public void removeTrack() {
        PreparedStatement sm = null;
        String st = "DELETE FROM NowPlaying WHERE CustomerID = ? AND trackID = ?";
        
        try {
        String selected = listPlaylist.getSelectionModel().getSelectedItem().toString();
        String[] parts = selected.split(" - ");
        int trackID = findIDDB(parts);
           
        sm = db.getConnection().prepareStatement(st);
        for(int i=0; i<ids.size(); i++) {
            if(findIDDB(parts) == ids.get(i)) {
                sm.setInt(1, customerID);
                sm.setInt(2, ids.get(i));
                sm.executeUpdate();
                
                String st2 = "select trackPath from Track where TrackID=?";
                sm = db.getConnection().prepareStatement(st2);
                sm.setInt(1, ids.get(i));
                ResultSet rs = sm.executeQuery();
                logic.addTracks();
                logic.addFilePath();
                MainGUIController.removeTrack(rs.getString("trackPath"));
                ids.remove(i);
            }
        }
        String st3 = "DELETE FROM NowPlaying WHERE CustomerID = ? AND trackID = ?";
        sm = db.getConnection().prepareStatement(st3);
        sm.setInt(1, customerID);
        sm.setInt(2, trackID);
        sm.executeUpdate();
       
        playlist.remove(selected);
        listPlaylist.setItems(null);
        listPlaylist.setItems(playlist);
        logic.removeOne(trackID);
        mgc.updateNeeded = true;
        }catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }catch(NullPointerException e) {
            
        }
    }
    
    //Removes all the tracks off arraylist and database
    public void removeAll() {
        try {
            Statement sm = db.getConnection().createStatement();
            sm.executeUpdate("DELETE FROM NowPlaying WHERE CustomerID='"+customerID+"'");
            ids.clear();
            playlist.clear();
            listPlaylist.setItems(null);
            listPlaylist.setItems(playlist);
            
            mgc.removeTracks();
            mgc.changedall = true;
            mgc.empty = true;
            
        }catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //Find the ID of the song
    public int findIDDB(String [] parts) {
        PreparedStatement sm = null;
        String st = "select TrackID from Track where name = ? AND artist = ?";
        
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, parts[0]);
            sm.setString(2, parts[1]);
            ResultSet rs = sm.executeQuery();
            
            return rs.getInt("TrackID");
        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return rand.nextInt(200 - 4 + 1) + 4;
    }
    
    //Updates the playlist so the songs are displayed
    public void updatePlaylist() {
        PreparedStatement sm = null;
        String st = "select TrackID from NowPlaying where CustomerID = ?";
        
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setInt(1, customerID);
            ResultSet rs = sm.executeQuery();
            
            while(rs.next()){
                ids.add(rs.getInt("TrackID"));
                playlist.add(findSong(rs.getInt("TrackID")));      
            }
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        listPlaylist.setItems(playlist);
    }
    
    //Find a song on the database
    public String findSong(int ID) {
        PreparedStatement sm = null;
        String st = "select name, artist from Track where TrackID = ?";
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setInt(1, ID);
            ResultSet rs = sm.executeQuery();
            
            return rs.getString("name") + " - " + rs.getString("artist");
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db=Database.getInstance();
        choice.setValue("Artist");
        choice.setItems(choices);
        updatePlaylist();
    }    
   
}
