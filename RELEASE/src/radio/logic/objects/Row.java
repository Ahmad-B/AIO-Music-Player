/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.logic.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sfk30
 */
public class Row {

    public final SimpleStringProperty artistName;
    public final SimpleStringProperty songName;

    public Row(String artistName, String songName) {
        this.artistName = new SimpleStringProperty(artistName);
        this.songName = new SimpleStringProperty(songName);
    }

    public void setArtistName(String artistName) {
        this.artistName.set(artistName);
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }
    
    public String getArtistName(){
        return artistName.get();
    }
    
    public String getSongName(){
        return songName.get();
    }

}
