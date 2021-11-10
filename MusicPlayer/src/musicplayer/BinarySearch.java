package musicplayer;

import javafx.collections.ObservableList;

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
public class BinarySearch {

    /// This method searches for the song in the song list.
    public int search(ObservableList<Song> songData, String songToFind) {
        Song[] songArray = songData.toArray(new Song[songData.size()]);
        int min = 0;
        int max = songData.size() - 1;

        while (min <= max) {

            int mid = (min + max) / 2;
            if (songArray[mid].getTitle().compareTo(songToFind) < 0) {
                min = mid + 1;
            } else if (songArray[mid].getTitle().compareTo(songToFind) > 0) {
                max = mid - 1;
            } else {
                return mid;
            }

        }
        return -1;
    }

}
