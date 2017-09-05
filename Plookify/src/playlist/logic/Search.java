/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.logic;

import common.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ec14373
 */
public class Search {

    private ObservableList<Track> trackData;
    private Database db = Database.getInstance();

    /*
     * Search constructor
     */    
    public Search() {
        db = Database.getInstance();

    }

    /*
     *  search database by artist if no results are returned search by track name
     * returned results are added to list
     */
    public ObservableList<Track> searchByArtist(String artist) {

        trackData = FXCollections.observableArrayList();

        if (!artist.equals("")) {
            try {
                // search by artist
                String query = "SELECT TrackID, name, genre FROM Track WHERE artist LIKE ?";
                PreparedStatement psm = db.getConnection().prepareStatement(query);
                psm.setString(1, artist + "%");
                ResultSet rs = psm.executeQuery();

                // checks to see if any artists are returned if not search by track instead
                if (!rs.isBeforeFirst()) {

                    String trackQuery = "SELECT TrackID, name, genre FROM Track WHERE name LIKE ?";
                    PreparedStatement tracksm = db.getConnection().prepareStatement(trackQuery);
                    tracksm.setString(1, artist + "%");
                    rs = tracksm.executeQuery();
                }

                while (rs.next()) {
                    trackData.add(new Track(rs.getInt("TrackID"), rs.getString("name"), rs.getString("genre")));
                }
            } catch (Exception e) {
                System.out.println("SQL statement error: " + e.getMessage());
            }
        } else if (!trackData.isEmpty()) {
            trackData.clear();
        }

        return trackData;

    }

}
