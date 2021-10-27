package musicplayer;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.ObservableList;

/**
 *
 * @author P113357
 */
public class BinarySearch {
    
    public int search(ObservableList<Song> songData, String songToFind){
        Song[] songArray = songData.toArray(new Song[songData.size()]);
        int min = 0;
        int max = songData.size() - 1;
        
        while (min <= max) {
            
            int mid = (min + max) / 2;
            if(songArray[mid].getTitle().compareTo(songToFind) < 0) {
                min = mid + 1;
            }
            else if (songArray[mid].getTitle().compareTo(songToFind) > 0) {
                max = mid - 1;
            } else {
                return mid;
            }
            
        }
        return -1;
    }
    
}
