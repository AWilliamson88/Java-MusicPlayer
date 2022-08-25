package musicplayer;

import java.net.URI;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Java 3 AT 3 - Project.
 * Question 3 – Implement your solution.
 * Must contain dynamic data structures.
 * (e.g. doubly linked list or a binary tree).
 * Must contain hashing techniques.
 * Must contain sorting algorithm.
 * Must contain searching technique.
 * Must contain 3rd party library.
 * Must have a GUI.
 * Must adhere to coding standards.
 * Must have help files.
 *
 * @author Andrew Williamson / P113357
 */
public class MusicController {

    public static LinkedList<Song> songList = new LinkedList<>();

    private Song currentSong;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void add(URI source) {
        songList.add(new Song(source));
    }

    public void add(Song song) {
        songList.add(song);
    }

    public LinkedList<Song> songList() {
        return songList;
    }

    /// This method plays the song if it's not playing or pauses it if 
    /// it is playing.
    public void play() {

        try {

            if (songList.size() > 0) {

                if (mediaPlayer == null) {
                    currentSong = songList.getFirst();
                    URI songURI = new URI(currentSong.getPath());
                    media = new Media(songURI.toString());
                    mediaPlayer = new MediaPlayer(media);
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /// This method stops the music player from playing.
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /// This method skips the current song and plays the next one in the list.
    /// If the song is the last one in the list then the first song is played.
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

    /// This method skips back to the previous song in the list.
    /// If the song is the first one in the list then the last song is played.
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

    /// This method searches the song list for the song title in the search box
    /// and plays it if it is found.
    public void search(ObservableList<Song> songData, String songToFind) {
        BinarySearch bs = new BinarySearch();
        int index = bs.search(songData, songToFind);

        if (index >= 0) {
            currentSong = songList.get(index);
            media = new Media(currentSong.getPath());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText("Song not found.");
            alert.setContentText("Please enter a different tittle and try again.");
            alert.show();
        }
    }

    /// This method creates the merge sorter object and uses it to sort 
    /// the song list.
    /// Called every time a song is added to the list.
    public void sortList(LinkedList<Song> songs) {
        MergeSorter ms = new MergeSorter();
        setSongList(ms.sort(songs));
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public LinkedList<Song> getList() {
        return songList;
    }

    private void setSongList(LinkedList<Song> newSongList) {
        songList = newSongList;
    }

}
