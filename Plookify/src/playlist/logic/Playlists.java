package playlist.logic;

import common.Customer;
import common.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ec14373
 */
public class Playlists {

    private final ArrayList<Playlist> playlists;
    private final Database db;
    Customer currentCustomer = Customer.getCurrentCustomer();

    /*
     * Playlists constructor
     */    
    public Playlists() {
        db = Database.getInstance();
        this.playlists = new ArrayList<>();
        updatePlaylists();
    }

    /*
     * create playlist and save it to the database
     */
    public void addPlaylist(String name) {
        // make sure there is not a playlist with the same name
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                return;
            }
        }
        // add name to db
        try {
            String query = "INSERT INTO Playlist (name, playlistType, CustomerID) VALUES (?, 'friend', ?)";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setString(1, name);
            psm.setInt(2, currentCustomer.getId());
            psm.executeUpdate();
            updatePlaylists();
        } catch (Exception ex) {
            System.out.println("Query Error" + ex.getMessage());
        }

    }

    /*
     *  delete playlist from database
     */
    public void deletePlaylist(int playlistID) {

        try {
            // delete tracks associated with the particular playlist
            String trackQuery = "DELETE FROM TrackPlaylist WHERE playlistID = ?";
            PreparedStatement trackpsm = db.getConnection().prepareStatement(trackQuery);
            trackpsm.setInt(1, playlistID);
            trackpsm.executeUpdate();
            // then delete playlist
            String query = "DELETE FROM Playlist WHERE PlaylistID=?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, playlistID);
            psm.executeUpdate();            
            
        } catch (Exception ex) {
            System.out.println("Query Error" + ex.getMessage());
        }

    }

    /*
     *  get the playlists names from the database
     */
    private void updatePlaylists() {
        //get all playlist names from database
        String name;
        int id;

        try {
            String query = "SELECT PlaylistID, name FROM Playlist WHERE CustomerID=?";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            currentCustomer = Customer.getCurrentCustomer();
            psm.setInt(1, currentCustomer.getId());
            ResultSet rs = psm.executeQuery();

            while (rs.next()) {
                id = rs.getInt("PlaylistID");
                name = rs.getString("name");
                Playlist newPlaylist = new Playlist(id, name);
                playlists.add(newPlaylist);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error. " + e.getMessage());
        }
    }

    /*
     * return list of playlists if no playlists return null
     */    
    public ArrayList<Playlist> getPlaylists() {
        if (playlists.isEmpty()) {
            return null;
        }
        return playlists;
    }

    /*
     * get list of friends playlists
     */    
    public ArrayList<Playlist> getFriendPlaylists() {
        ArrayList<Playlist> friendPlaylists = new ArrayList<>();
        //get all friend playlists which are public from database
        String name;
        int id;

        try {
            String query = "SELECT p.PlaylistID, p.name "
                    + "FROM Playlist p "
                    + "JOIN Customer c on c.CustomerID = c.CustomerID "
                    + "JOIN Friend f on c.CustomerID = f.CustomerID "
                    + "WHERE p.playlistType = 'friend' AND (p.CustomerID = f.CustomerIDFriend AND f.CustomerID = ?)"
                    + " AND f.hasAccepted = 1";
            PreparedStatement psm = db.getConnection().prepareStatement(query);
            psm.setInt(1, currentCustomer.getId());
            ResultSet rs = psm.executeQuery();

            while (rs.next()) {
                id = rs.getInt("PlaylistID");
                name = rs.getString("name");

                Playlist newPlaylist = new Playlist(id, name);
                friendPlaylists.add(newPlaylist);
            }

        } catch (Exception e) {

            System.out.println("Error. " + e.getMessage());
        }

        return friendPlaylists;
    }

}
