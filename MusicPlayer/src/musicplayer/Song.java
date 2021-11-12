package musicplayer;

import java.net.URI;
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
    URI filePath;
    private static SongHash hasher = new SongHash();

    /// Constructor
    public Song(URI source) {
//        System.out.println(source);
        String sourceString = source.toString();
        String songTitle = sourceString.substring(
                        sourceString.lastIndexOf('/') + 1, sourceString.lastIndexOf('.'));
        songTitle = songTitle.replace("%20", " ");
        title = new SimpleStringProperty(songTitle);
        hash = new SimpleStringProperty(hasher.getHash(songTitle));
        filePath = source;
    }

    /// Constructor
    public Song(String songTitle, URI source) {
//        System.out.println(source);
        title = new SimpleStringProperty(songTitle);
        hash = new SimpleStringProperty(hasher.getHash(songTitle));
        filePath = source;
    }

    public String getPath() {
//        System.out.println(filePath);
        return filePath.toString();
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
