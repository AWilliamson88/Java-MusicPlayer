package musicplayer;

import java.net.URI;
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.junit.Test;

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
        URI source = URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/A%20New%20Beginning.mp3");
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
        Song s1 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/A%20New%20Beginning.mp3"));
        Song s2 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/Xeno%20Titan.mp3"));
        Song s3 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/Epic.mp3"));
        
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
        LinkedList<Song> SortedSongList = new LinkedList<>();
        LinkedList<Song> UnsortedSongList = new LinkedList<Song>();
        MusicController instance = new MusicController();
        Song s1 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/Xeno%20Titan.mp3"));
        Song s2 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/Epic.mp3"));
        Song s3 = new Song(URI.create("file:/F:/Documents/Tafe/Dip_Of_Programming/Java%203/AT3/Music/A%20New%20Beginning.mp3"));
        
        UnsortedSongList.add(s1);
        UnsortedSongList.add(s2);
        UnsortedSongList.add(s3);
        
        instance.sortList(UnsortedSongList);
        SortedSongList = instance.getList();
        
        assertEquals(s3, SortedSongList.getFirst());
    }
}
