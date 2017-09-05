
package social.logic;

import common.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import playlist.logic.Playlist;


/**
 *
 * @author nm313
 */
public class Friendplaylist {
    
    private final int friendID;
    //private final String friendName;
    
    private final ArrayList<Playlist> friendPlaylists;
    private final Database db;
    
    public Friendplaylist(int ID) {
        db = Database.getInstance();
        friendID = ID;
        friendPlaylists = new ArrayList<>();
        updateFriendPlaylists();
    }
    private void updateFriendPlaylists(){
        //get all friend playlists which are public from database
        String name;
        int id;
        
        try{
            Statement sm = db.getConnection().createStatement();
            ResultSet rs = sm.executeQuery("SELECT PlaylistID, name FROM Playlist Where playlistType = 'friend' and customerID = "+ friendID);
            
            while(rs.next()){
                id = rs.getInt("PlaylistID");
                name = rs.getString("name");
                
                Playlist newPlaylist = new Playlist(id, name);
                friendPlaylists.add(newPlaylist);
            }
                                                
        }catch(Exception e){          
            
            System.out.println( "Error. " + e.getMessage());   
        }        
    }
  
    public int getID() {
        return friendID;
    }
    
  public ArrayList<Playlist> getPlaylists() {
     
        return friendPlaylists;
    }
    
}
