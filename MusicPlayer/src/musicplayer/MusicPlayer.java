package musicplayer;

import com.sun.javafx.embed.swing.FXDnD;
import java.awt.PageAttributes;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Java 3 AT 3 - Project
 * Question 3 – Implement your solution
 * - Must contain dynamic data structures 
 *    (e.g. doubly linked list or a binary tree)
 * - Must contain hashing techniques
 * - Must contain sorting algorithm
 * - Must contain searching technique
 * - Must contain 3rd party library
 * - Must have a GUI
 * - Must adhere to coding standards
 * - Must have help files
 * 
 * @author Andrew Williamson / P113357
 */
public class MusicPlayer extends Application{

    final TableView<Song> tableView = new TableView<>();
    
    // Create a music controller.
    MusicController mc = new MusicController();
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Awsome Music Player");

        Group root = new Group();

        Label title = new Label("Awesome Music Player");
        
        // Music Player Buttons.
        Button btnPlayPause = new Button("Play / Pause");
        Button btnPrevious = new Button("Previous");
        VBox btnsLeft = new VBox();
        btnsLeft.getChildren().addAll(btnPlayPause, btnPrevious);
        
        VBox btnsMid = new VBox();
        Button btnStop = new Button("Stop");
        Button btnNext = new Button("Next");
        btnsMid.getChildren().addAll(btnStop, btnNext);
        
        Button btnAdd = new Button("Add a Song");

        HBox buttonDisplay = new HBox();
        buttonDisplay.getChildren().addAll(btnsLeft, btnsMid, btnAdd);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 20, 0, 10));
        vBox.getChildren().addAll(title, tableView, buttonDisplay);
        
        
        btnAdd.setOnAction(e -> addSong(stage));
        btnPlayPause.setOnAction(e -> mc.play());
        btnStop.setOnAction(e -> mc.stop());
        btnNext.setOnAction(e -> mc.next());
        btnPrevious.setOnAction(e -> mc.previous());
        // Test Media class.
        // Create a media object and assign it the music file.
        
        
        // Assign the media bject to the media player.
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
        // Create the media view for the media player.
//        MediaView mediaView = new MediaView(mediaPlayer);
        
//        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(mediaView);
//        borderPane.setBottom(addToolBar());
        
//        borderPane.setStyle("-fx-Background-color: Black");
        
//        vBox.getChildren().add(borderPane);
        root.getChildren().add(vBox);
        stage.setScene(new Scene(root, 500, 800));
        stage.show();
    }
    
    private void addSong(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Add Song To Playlist");
        fc.setInitialDirectory(new File("../../Music"));
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("mp3 files only", "*.mp3"));
        
        List<File> selectedFiles = fc.showOpenMultipleDialog(stage);
        
        if(selectedFiles != null) {
            for(File f : selectedFiles){
                mc.add(f.toURI().toString());
            }
            
        }
    }
    
    private HBox addToolBar() {
        
        HBox toolBar = new HBox();
        toolBar.setPadding(new Insets(20));
        toolBar.setAlignment(Pos.CENTER);
        toolBar.alignmentProperty().isBound();
        toolBar.setSpacing(5);
        toolBar.setStyle("-fx-background-color: Black");
        
        return toolBar;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
