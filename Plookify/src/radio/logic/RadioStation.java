/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.logic;

import common.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import radio.logic.objects.*;
import java.util.*;

/**
 *
 * @author ec14071
 */
public class RadioStation {

    private String genre;
    private HashMap<String, ArrayList<Track>> playlist;

    public RadioStation(String genre) {
        this.genre = genre;
        playlist = new HashMap<>();
    }

    public void addTrack(Track track, Artist artist) {

        if (playlist.containsKey(artist.Name)) {
            for(Track current:playlist.get(artist.Name)){
                if(current.ID==track.ID){
                    return;
                }
            }
            playlist.get(artist.Name).add(track);
        } else {
            ArrayList temp = new ArrayList<Track>();
            temp.add(track);
            playlist.put(artist.Name, temp);
        }
    }

    public HashMap<String, ArrayList<Track>> browseTracks() {
        return playlist;
    }

    public boolean saveAsPlaylist(String name, boolean type) {
        if(common.Customer.getCurrentCustomer().getId()==-1)return false;
        if(!common.Customer.getCurrentCustomer().isSubscribed())return false;
        String pType = (type) ? ("private") : ("friend");
        String query = "INSERT INTO playlist (name, playlistType, CustomerID) VALUES('" + name + "', '" + pType + "', '" + common.Customer.getCurrentCustomer().getId() + "')";

        try {
            int pid;
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            //while (keys.next()) {
                pid = keys.getInt(1); //get the first index
            //}

            
            
            Calendar c = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String date = format.format(c.getTime());

            for (ArrayList<Track> current : playlist.values()) {
                
                for (Track track : current) {
                    String str = "INSERT INTO TrackPlaylist (TrackID, playlistID, dateAdded) VALUES (?,?,?)";
                    //str = str + "('" + track.ID + "','" + pid + "','" + date+"')";
                    
                    PreparedStatement st = Database.getInstance().getConnection().prepareStatement(str);
                    st.setString(1, track.ID+"");
                    st.setString(2, pid+"");
                    st.setString(3, date);  
                    st.executeUpdate();
                }
            }
            common.gui.FrontController.updateNeeded=true;
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }
}
