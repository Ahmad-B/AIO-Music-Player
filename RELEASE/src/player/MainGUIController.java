package player;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainGUIController implements Initializable {

    @FXML public Label cname;
    @FXML Slider timeSlider;
    @FXML Slider volumeSlider;
    @FXML Label time;
    @FXML Button play;
    @FXML Button trackDB;
    @FXML ImageView soundIcon;
    @FXML TextField jumpTo;
   
    
    static player.logic.Logic logic = new player.logic.Logic();
    public static ArrayList<String> fl1 = new ArrayList<>();
    
    private int count = 0;
    public static boolean updateNeeded = false;
    public static boolean empty = false;
    public static boolean changedall = false;
    public static boolean stop = false;
   
    MediaPlayer music;
    Media media;
    Duration duration;
    Duration length;

    //Method when play button is clicked
    @FXML
    public void playClicked() {
        if (fl1.isEmpty()) {
            cname.setText("Please add some tracks!");
        } else {
            if (changedall) {
            logic.addTracks();
            logic.addFilePath();
            startPlay(fl1.get(0), 0);
            changedall = false;
            playClicked();
        } else {
            if ("▌▌".equals(play.getText())) {
                music.pause();
                play.setText("►");
            } else {
                music.play();
                play.setText("▌▌");
            }
            cname.setText("Now playing: " + logic.findInfo(fl1.get(count)));
        }
    }
    }
    
    //if the song viewer is open on gui, stop the music
    public void dbOpen() {
        if ("▌▌".equals(play.getText())) 
            music.stop();
    }
    
    //Method for the stop button for music
    public void stopClicked() {
        if ("▌▌".equals(play.getText())) {
        music.stop();
        play.setText("►");
        cname.setText("Now playing: ");
        }
    }
    
    //Method for the restart button for music
    public void restartClicked() { 
        if ("▌▌".equals(play.getText())) 
        music.seek(music.getStartTime());
    }
    
    //Method called when user wants to jump to/start at a time in the song
    public void jumpToTime() {
        if (!fl1.isEmpty()) {
            try {
               double time = Double.parseDouble(jumpTo.getText());
               String timemin = jumpTo.getText();
                if("▌▌".equals(play.getText())) {
                    jumpTo.setText("Jumped to: " + timemin); }
                else {
                    jumpTo.setText("Starting at: " + timemin); }
               if(timemin.contains(".") || timemin.contains(":")) {
                   jumpTo.setText("Seconds only!");
               }
               else if(time > duration.toSeconds()) jumpTo.setText("Invalid input!");
               else music.seek(duration.seconds(time));
            }
            catch(Exception e) {jumpTo.setText("Invalid input!");}
         }
    }

    //Opens the song viewer/now playing playlist if not run on combined gui
    @FXML
    public void trackDBClick() throws Exception {
         if ("▌▌".equals(play.getText())) {
        stopClicked();
         }
        Parent root = FXMLLoader.load(getClass().getResource("trackDatabase.fxml"));
        Stage trackDB = new Stage();
        trackDB.setTitle("Track Database");
        trackDB.setScene(new Scene(root));
        trackDB.show();
        trackDB.setResizable(false);
    }
    
    //Controls the music when the seeking music slider is moved
    public void sliderMoved() {
         music.seek(duration.multiply(timeSlider.getValue() / 100.0));
    }
    
    //Controls the volume level when the volume slider is moved
    public void volumeMoved() {
        music.setVolume(volumeSlider.getValue() / 100);
        setSoundIcon();
    }
    
    //Method to initialise all the music and play
    public void startPlay(String fl, int auto) {
        if(empty)
            return;
        if(fl1.isEmpty()){
            timeSlider.setDisable(true);
        }
        else {
        String fileLocation = System.getProperty("url", fl);
        media = new Media(fileLocation);
        music = new MediaPlayer(media);
        duration = music.getMedia().getDuration();
        length = music.getMedia().getDuration();
        
        if(auto == 1) playClicked();
            
        music.currentTimeProperty().addListener((Observable ov) -> {
            updateValues();
        });
        
        music.setOnReady(() -> {
            duration = music.getMedia().getDuration();
            updateValues();
        });

        music.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValuel, Duration duration, Duration current){
                timeSlider.setValue(current.toSeconds());
            }
        });
        music.setOnEndOfMedia(new Runnable() { //plays next song when current is finished
        @Override public void run() {
            jumpTo.clear();
            play.setText("►");
            if(empty) return;
            if(count+1 >= MainGUIController.getAL().size()) {
                cname.setText("Now playing: ");
                begin();
                return;
            }
            logic.removeDBTrack(MainGUIController.getAL().get(count));
            startPlay(MainGUIController.getAL().get(++count), 1);
        }
      });
    }
    }

    //get method for the arraylist of trackpaths
    public static ArrayList<String> getAL(){
        return fl1;
    }
    
    //remove specific track from list of trackpaths
    public static void removeTrack(String path) {
       for(int i=0; i<fl1.size(); i++) {
           if(fl1.get(i).equals(path)) {
               fl1.remove(i);
           }
       }
    }
    
    //Deletes all the tracks from the arraylists
    public static void removeTracks() {
        fl1.clear();
        logic.removeAll();
    }
    
    //Sets the now playing text to defauly
    public void settext() {
        cname.setText("Now playing: ");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trackDB.setVisible(false); //invisible on main gui, set true otherwise
        begin();
        volumeSlider.setValue(100);
        soundIcon.setImage(new Image ("player/soundmax.png"));
        
                // create thread
        Thread t = new Thread(){
            public void run(){
                while(true) { //thread will be on endless loop to check variable changes
                    // thread sleeps
                    try {
                        sleep(10);
                    } catch (InterruptedException ex) {
                        
                    }
                    //When the song viewer/now playing is open
                    if(stop) {
                        dbOpen();
                        stop = false;
                    }
                    //checks if list is empty
                    if(empty) {
                        timeSlider.setDisable(true);
                        empty = false;
                    }
                    // updates player
                    if(updateNeeded){
                        begin();
                        updateNeeded=false;
                    }

                }
            }
        };
        t.setDaemon(true);
        t.start();
        
    }
    
    //Adds all the tracks and begins the player code
    public void begin() {
        logic.addTracks();
        logic.addFilePath();

        
        if(fl1.isEmpty()) {
            timeSlider.setDisable(true);
        }
        else {
            startPlay(fl1.get(count), 0);
        }
    }
    
    //Determines the sound icon depending on where the volume slider is
    public void setSoundIcon() {
        if(volumeSlider.getValue() == 0.0) {
            soundIcon.setImage(new Image ("player/soundmute.png"));
        }
        else {
            soundIcon.setImage(new Image ("player/soundmax.png"));
        }
    }
    
    
    /* Method from: http://docs.oracle.com/javafx/2/media/overview.htm 
            Updates the length of the song and time position of song */
    protected void updateValues() {
        if (time != null && timeSlider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = music.getCurrentTime();
                    time.setText(logic.formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                }
            });
        }
    }
    
}
