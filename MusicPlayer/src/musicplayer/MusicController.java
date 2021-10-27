package musicplayer;

import java.util.LinkedList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Java 3 AT 3 - Project Question 3 – Implement your solution - Must contain
 * dynamic data structures (e.g. doubly linked list or a binary tree) - Must
 * contain hashing techniques - Must contain sorting algorithm - Must contain
 * searching technique - Must contain 3rd party library - Must have a GUI - Must
 * adhere to coding standards - Must have help files
 *
 * @author Andrew Williamson / P113357
 */
public class MusicController {

    public static LinkedList<Song> songList = new LinkedList<>();
    
    private Song currentSong;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void add(String source) {
        songList.add(new Song(source));
    }
    
    public LinkedList<Song> songList() {
        return songList;
    }

    public void play() {

        if (songList.size() > 0) {

            if (mediaPlayer == null) {
                currentSong = songList.getFirst();
                media = new Media(currentSong.getPath());
                mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.setAutoPlay(true);
                mediaPlayer.play();
            } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        } else {
            // Create a message box to show an error.
            // alternatively make the play button only clickable 
            // When there is something to play.
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void next() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            int index = songList.indexOf(currentSong);

            if (index < songList.size() - 1) {
                index++;
                currentSong = songList.get(index);
            } else {
                currentSong = songList.getFirst();
            }
            media = new Media(currentSong.getPath());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

    public void previous() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            int index = songList.indexOf(currentSong);

            if (index > 0) {
                index--;
                currentSong = songList.get(index);

            } else {
                currentSong = songList.getLast();
            }
            media = new Media(currentSong.getPath());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
    
    public Song getCurrentSong() {
        return currentSong;
    }

}
