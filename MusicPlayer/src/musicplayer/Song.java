package musicplayer;

import javafx.beans.property.SimpleStringProperty;

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
public class Song {

    String id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty hash;
    String filePath;
    private static SongHash hasher = new SongHash();

    /// Constructor
    public Song(String source) {
        String songTitle
                = source.substring(
                        source.lastIndexOf('/') + 1, source.lastIndexOf('.'));
        songTitle = songTitle.replace("%20", " ");
        title = new SimpleStringProperty(songTitle);
        hash = new SimpleStringProperty(hasher.getHash(songTitle));
        filePath = source;
    }

    /// Constructor
    public Song(String songTitle, String songPath) {
        title = new SimpleStringProperty(songTitle);
        hash = new SimpleStringProperty(hasher.getHash(songTitle));
        filePath = songPath;
    }

    public String getPath() {
        return filePath;
    }

    public String getTitle() {
        return title.get();
    }

    public String[] getArray() {
        String[] s = new String[]{getTitle(), getPath()};
        return s;
    }

    public String getHash() {
        return hash.get();
    }

}
