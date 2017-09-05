/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.Customer;
import common.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Duration;
import player.MainGUIController;

/**
 *
 * @author Ahmad
 */
public class Logic {
    private Database db;
    Random rand = new Random();
    static ArrayList<Integer> playlist = new ArrayList<>();
    Customer customer = Customer.getCurrentCustomer();
    int customerID = customer.getId();
    
    //Removes all the songs in the arraylist
    public void removeAll() {
        playlist.clear();
    }
    
    //Remove specific song from arraylist
    public void removeOne(int id) {
        for(int i=0; i<playlist.size(); i++) {
            if(playlist.get(i).equals(id))
                playlist.remove(i);
        }
    }
    
    //Adds tracks to the arraylist
    public void addTracks() {
        db=Database.getInstance();
        PreparedStatement sm = null;
        String st = "select TrackID from NowPlaying where CustomerID = ?";
        try{
            sm = db.getConnection().prepareStatement(st);
            sm.setInt(1, customerID);
            ResultSet rs = sm.executeQuery();
            while(rs.next()){
                if (!playlist.contains(rs.getInt("TrackID"))) {
                    playlist.add(rs.getInt("TrackID"));
                }
            }
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    //Adds the tracks filepath to the arraylist so it can be played in main
    public void addFilePath() {
        db=Database.getInstance();
        PreparedStatement sm = null;
        String st = "select trackPath from Track where TrackID = ?";
        try{
            sm = db.getConnection().prepareStatement(st);
            for(int i=0; i<playlist.size(); i++) {
                sm.setInt(1, playlist.get(i));
                ResultSet rs = sm.executeQuery();
                if(!MainGUIController.getAL().contains(rs.getString("trackPath"))) {
                    MainGUIController.getAL().add(rs.getString("trackPath"));
                }
            }         
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    //Finds the name and artist information of a track
    public String findInfo(String path) {
        db=Database.getInstance();
        PreparedStatement sm = null;
        String st = "select name, artist from Track where trackPath=?";
        try{
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, path);
            ResultSet rs = sm.executeQuery();
            return rs.getString("name")+ " - " + rs.getString("artist");
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    //Removes a track from the database
    public void removeDBTrack(String path) {
        db=Database.getInstance();
        PreparedStatement sm = null;
        String st = "DELETE FROM NowPlaying WHERE CustomerID = ? AND TrackID = ?";
        try{
            int remove = findIDDB(path);
            sm = db.getConnection().prepareStatement(st);
            sm.setInt(1, customerID);
            sm.setInt(2, remove);
            sm.executeUpdate();
            
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    //Finds the ID of the song
    public int findIDDB(String path) {
        PreparedStatement sm = null;
        String st = "select TrackID from Track where trackPath = ?";
        try {
            sm = db.getConnection().prepareStatement(st);
            sm.setString(1, path);
            ResultSet rs = sm.executeQuery();
            return rs.getInt("TrackID");
        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rand.nextInt(200 - 4 + 1) + 4;
    }
    
        /* Method from: http://docs.oracle.com/javafx/2/media/overview.htm 
            Formats the time that is displayed on the player */
        public static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
        
}
