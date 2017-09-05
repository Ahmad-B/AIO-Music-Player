/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.logic;

/**
 *
 * @author ec14071
 */
//for database
import java.sql.*;
import common.Database;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import radio.logic.objects.*;

public class RadioManager {

    private final Database db;

    private String searchedArtist;
    private RadioStation rs;

    public RadioManager() {
        db = Database.getInstance();
    }

    public RadioStation getStation() {
        return rs;
    }

    public boolean createStation(String artist) {

        String genre = getGenre(artist);
        searchedArtist = artist;
        if (genre == null) {
            return false;
        }

        if (!checkTracksExistForGenre(genre)) {
            return false;
        }

        rs = new RadioStation(genre);
        boolean result = addRandomTracks(rs, genre);

        return result;
    }

    private boolean addRandomTracks(RadioStation radio, String genre) {
        boolean added = false;
        int rand = 5 + (int) (Math.random() * ((10 - 5) + 1));

        for (int i = 0; i < rand; i++) {
            Artist artist = getRandArtist(genre);
            Track track = null;

            if (artist != null) {
                track = getRandTrack(artist);
            }
            if (track != null) {
                radio.addTrack(track, artist);
                added = true;

            } else {
                i--;
            }

        }
        return added;
    }

    private Artist getRandArtist(String genre) {

        try {
            PreparedStatement sm = db.getConnection().prepareStatement("select ArtistID, name from Artist where genre='" + genre + "' ORDER BY RANDOM() LIMIT 1");
            ResultSet rs = sm.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist();

                artist.ID = rs.getInt("ArtistID");
                artist.Name = rs.getString("name");

                return artist;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private Track getRandTrack(Artist artist) {
        PreparedStatement sm = null;
        ResultSet rs = null;
        try {
            sm = db.getConnection().prepareStatement("select TrackID, name from Track where LOWER(artist)='" + artist.Name.toLowerCase() + "' order by random() LIMIT 1");

            rs = sm.executeQuery();

            while (rs.next()) {
                Track randTrack = new Track();

                randTrack.ID = rs.getInt("TrackID");
                randTrack.Name = rs.getString("name");
                randTrack.artist = artist;

                return randTrack;
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (sm != null) {
                try {
                    sm.close();
                } catch (SQLException ex) {/*do nothing*/}
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {/*do nothing*/}
            }

        }

        return null;
    }

    private boolean checkTracksExistForGenre(String genre) {
        try {
            PreparedStatement sm = db.getConnection().prepareStatement("select COUNT(*) as c from Track where genre='" + genre + "'");
            ResultSet rs = sm.executeQuery();
            if (rs.getInt("c") > 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }

    private String getGenre(String artist) {
        //get genre from database given an artist

        String genre = null;
        try {
            PreparedStatement sm = db.getConnection().prepareStatement("select genre from Artist where LOWER(name)='" + artist + "'");
            ResultSet rs = sm.executeQuery();

            while (rs.next()) {
                genre = rs.getString("genre");
            }
        } catch (Exception e) {
            return null;
        }
        return genre;
    }

    public ObservableList<Artist> search(String artist) {
        ObservableList<Artist> artistResults = FXCollections.observableArrayList();
        String str = "select name, ArtistID from Artist where LOWER(name) LIKE '" + artist.toLowerCase() + "%'";
        try {
            Statement sm = db.getConnection().createStatement();
            ResultSet rs = sm.executeQuery(str);
            while (rs.next()) {
                artistResults.add(new Artist(rs.getInt("ArtistID"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            return null;
        }
        return artistResults;
    }
}
