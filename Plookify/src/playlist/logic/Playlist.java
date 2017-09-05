package playlist.logic;

import common.Database;
import common.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ec14373
 */
public class Playlist {

    private final int playlistID;
    private final String name;
    private ObservableList<Track> playlistData;
    private final Database db;
    player.MainGUIController mgc = new player.MainGUIController();

    /*
     * Playlist constructor
     */    
    public Playlist(int playlistID, String name) {
        db = Database.getInstance();
        this.name = name;
        this.playlistID = playlistID;
    }

    /*
     * getters
     */    
    public String getName() {
        return name;
    }

    public int getID() {
        return playlistID;
    }

    public int getCreatedBy() {
        return playlistID;
    }

    /*
     * get permissions of playlist
     */    
    public String getPermissions() {
        if (this.playlistID == -1) {
            return "";
        }
        String permissions = "";

        try {
            String query = "SELECT playlistType FROM Playlist WHERE PlaylistID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, playlistID);
            ResultSet rs = psm.executeQuery();
            // getting playlist permissions
            if (rs.next()) {
                permissions = rs.getString("playlistType");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permissions;
    }

    /*
     * get the list of tracks for the playlist
     */    
    public ObservableList<Track> getPlaylistData() {
        buildPlaylistData();
        return playlistData;
    }

    /*
     *  get tracks related to the current playlist from database
     */
    private void buildPlaylistData() {
        if (this.playlistID == -1) {
            return;
        }
        try {
            playlistData = FXCollections.observableArrayList();
            String query = "SELECT t.TrackID, tp.dateAdded, t.name, t.artist FROM Playlist p "
                    + "INNER JOIN TrackPlaylist tp ON p.PlaylistID = tp.playlistID AND p.PlaylistID = ? "
                    + " INNER JOIN Track t ON tp.TrackID = t.TrackID";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, playlistID);
            ResultSet rs = psm.executeQuery();

            // getting data
            while (rs.next()) {
                playlistData.add(new Track(rs.getInt("TrackID"), rs.getString("name"), rs.getString("artist"), rs.getString("dateAdded")));
            }
        } catch (Exception e) {
            System.out.println("SQL statement error: " + e.getMessage());
        }
    }

    /*
     *  gets the creators name of the current playlist from database
     *  returns no creator if no creator is found
     */
    public String getCreatorName() {
        if (this.playlistID == -1) {
            return this.name;
        }
        try {
            String query = "SELECT c.name FROM Playlist p INNER JOIN Customer c ON p.CustomerID = c.CustomerID AND p.PlaylistID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, playlistID);
            ResultSet rs = psm.executeQuery();

            // getting playlist creators name
            if (rs.next()) {
                return rs.getString("name");
            }

        } catch (Exception e) {
            System.out.println("SQL statement error: " + e.getMessage());
        }
        return "No Creator";
    }

    /*
     *  update permissions of playlist in database
     */
    public void changePermissions(String permission) {

        try {
            String query = "UPDATE Playlist SET playlistType= ? WHERE PlaylistID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setString(1, permission);
            psm.setInt(2, playlistID);
            psm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     *  change name of playlist in database 
     */
    public void updateName(String name) {

        try {
            String query = "UPDATE Playlist SET name = ? WHERE PlaylistID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setString(1, name);
            psm.setInt(2, playlistID);
            psm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     *  add track to playlist if not already in playlist
     */
    public void addTrack(int trackID) {

        // search to make sure track is not already added to playlist
        for (Track track : playlistData) {
            if (track.getId() == trackID) {
                return;
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        String currentDate = dateFormat.format(date);

        try {
            String query = "INSERT INTO TrackPlaylist (TrackID, playlistID, dateAdded) VALUES (?, ?, ?)";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, trackID);
            psm.setInt(2, playlistID);
            psm.setString(3, currentDate);
            psm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     *  delete track from playlist
     */
    public void removeTrack(int trackID) {
        try {
            String query = "DELETE FROM TrackPlaylist WHERE TrackID = ? AND playlistID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, trackID);
            psm.setInt(2, playlistID);
            psm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     *  add current playlist to now playing
     */
    public void addToNowPlaying() {
         mgc.stop = true; // stop the currently playing track
        // delete all tracks already in now playling
        try {
            String query = "DELETE FROM NowPlaying WHERE CustomerID = ?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, Customer.getCurrentCustomer().getId());
            psm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        // add playlist to now playing for every track in playlist
        mgc.removeTracks(); // deletes track from arraylist
        for (int i = 0; i < playlistData.size(); i++) {
            try {
                String query = "INSERT INTO NowPlaying (CustomerID, TrackID) VALUES (?, ?)";
                PreparedStatement psm = db.getConnection().prepareStatement(query);
                psm.setInt(1, Customer.getCurrentCustomer().getId());
                psm.setInt(2, playlistData.get(i).getId());
                psm.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, e);
            }
        }
       // notifys main playlist that there has been an update to now playing tracks
       mgc.updateNeeded = true;
       mgc.changedall = true;
    }
}
