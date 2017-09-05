package playlist.logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ec14373
 */
public class Track {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty artist;
    private final SimpleStringProperty added;
    private final SimpleStringProperty genre;
    
    /*
     *  Constructor for users playlists
     */
    Track(int id, String name, String artist, String added) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.artist = new SimpleStringProperty(artist);
        this.added = new SimpleStringProperty(added);
        this.genre = new SimpleStringProperty("");
    }
    
    /*
     *  Constructor for users friends playlists
     */
    Track(int id, String name, String genre) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.genre = new SimpleStringProperty(genre);
        this.artist = new SimpleStringProperty("");
        this.added = new SimpleStringProperty("");
    }
    
    
    /*
     *  getters and setters
     */    
    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }    
    
    public String getName() {
        return name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getArtist() {
        return artist.get();
    }
    
    public void setArtist(String artist) {
        this.artist.set(artist);
    }
    
    public String getAdded() {
        return added.get();
    }
    
    public void setAdded(String added) {
        this.added.set(added);
    }
    
    public String getGenre() {
        return genre.get();
    }
    
    public void setGenre(String genre) {
        this.genre.set(genre);
    }
    
    
}
