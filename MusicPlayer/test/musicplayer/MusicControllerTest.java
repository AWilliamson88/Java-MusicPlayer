package musicplayer;

import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import musicplayer.MusicController;

/**
 *
 * @author P113357
 */
public class MusicControllerTest {

    private MusicController m;
    private Song s;

    public MusicControllerTest() {
    }

    /**
     * Test of add method, of class MusicController.
     */
    @Test
    public void testAdd_AddedSongBySourceString_Exists() {
        String source = "D:/Java 3/AT3/Music/Epic.mp3";
        MusicController instance = new MusicController();
        
        instance.add(source);

        assertTrue("The added song was in the song list", instance.getList().size() > 0);
    }

    /**
     * Test of add method, of class MusicController.
     */
    @Test
    public void testAdd_TheAddedSong_Exists() {
        Song song = null;
        MusicController instance = new MusicController();
        
        instance.add(song);
        
        assertTrue(
                "Song added to the music controller song list.", 
                instance.getList().size() > 0);
    }

    /**
     * Test of songList method, of class MusicController.
     */
    @Test
    public void testSongList() {
        MusicController instance = new MusicController();
        Song s1 = new Song("D:\\Java 3\\AT3\\Music\\Epic.mp3");
        Song s2 = new Song("D:\\Java 3\\AT3\\Music\\Going Higher.mp3");
        Song s3 = new Song("D:\\Java 3\\AT3\\Music\\Memories.mp3");
        
        instance.add(s1);
        instance.add(s2);
        instance.add(s3);
        boolean result = instance.songList().contains(s1);
        boolean result2 = instance.songList().contains(s2);
        boolean result3 = instance.songList().contains(s3);
        
        assertEquals(true, result);
        assertEquals(true, result2);
        assertEquals(true, result3);
    }

    /**
     * Test of sort method, of class MusicController.
     */
    @Test
    public void testSort_TheUnsortedSongList_IsSorted() {
        LinkedList<Song> SortedSongList = new LinkedList<Song>();
        LinkedList<Song> UnsortedSongList = new LinkedList<Song>();
        String songToFind = "Going Higher";
        MusicController instance = new MusicController();
        Song s1 = new Song("D:\\Java 3\\AT3\\Music\\Memories.mp3");
        Song s2 = new Song("D:\\Java 3\\AT3\\Music\\Going Higher.mp3");
        Song s3 = new Song("D:\\Java 3\\AT3\\Music\\Epic.mp3");
        
        UnsortedSongList.add(s1);
        UnsortedSongList.add(s2);
        UnsortedSongList.add(s3);
        
        instance.sortList(UnsortedSongList);
        SortedSongList = instance.getList();
        
        assertEquals(s3, SortedSongList.getFirst());
    }
}
