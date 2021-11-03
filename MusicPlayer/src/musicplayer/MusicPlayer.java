package musicplayer;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.javafx.embed.swing.FXDnD;
import java.awt.Font;
import java.awt.PageAttributes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Java 3 AT 3 - Project Question 3 – Implement your solution - Must contain
 * dynamic data structures (e.g. doubly linked list or a binary tree) - Must
 * contain hashing techniques - Must contain sorting algorithm - Must contain
 * searching technique - Must contain 3rd party library - Must have a GUI - Must
 * adhere to coding standards - Must have help files
 *
 * @author Andrew Williamson / P113357
 */
public class MusicPlayer extends Application {

    final TableView<Song> tableView = new TableView<>();

    final ObservableList<Song> songData
            = FXCollections.observableArrayList();

    // Create a music controller.
    MusicController mc = new MusicController();

    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Awsome Music Player");
        
        loadSongs();

        Group root = new Group();

        // Search
        Text searchLbl = new Text("Search: ");
        searchLbl.setStyle("-fx-font: 20 arial;");

        TextField searchField = new TextField();
        searchField.setOnAction(e -> {
            mc.search(songData, searchField.getText());
            highlightSong();
            searchField.clear();
        });

        // Add a Song
        Button btnAdd = new Button("Add a Song");
        btnAdd.setPadding(new Insets(5, 10, 5, 10));
        HBox.setMargin(btnAdd, new Insets(0, 0, 0, 70));
        // Add btn Event
        btnAdd.setOnAction(e -> addSong(stage));

        // Top Search and add btn HBox
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(searchLbl, searchField, btnAdd);
        topHBox.setAlignment(Pos.BASELINE_LEFT);
        topHBox.setPadding(new Insets(10, 10, 10, 10));

        // Table column
        TableColumn<Song, String> titleColumn = new TableColumn("Song Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // Table View
        tableView.setPlaceholder(new Label("No songs to display"));
        tableView.setItems(songData);
        tableView.getColumns().add(titleColumn);
        titleColumn.setPrefWidth(400);

        // Music Player Buttons.
        Button btnPlayPause = new Button("Play / Pause");
        Button btnPrevious = new Button("Previous");
        VBox btnsLeft = new VBox();
        btnsLeft.getChildren().addAll(btnPlayPause, btnPrevious);

        // Right buttons VBox
        VBox btnsRight = new VBox();
        Button btnStop = new Button("Stop");
        Button btnNext = new Button("Next");
        btnsRight.getChildren().addAll(btnStop, btnNext);

        // Button display HBox
        HBox buttonDisplay = new HBox();
        buttonDisplay.getChildren().addAll(btnsLeft, btnsRight);

        // Main UI display Vbox
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 20, 0, 10));
        vBox.getChildren().addAll(topHBox, tableView, buttonDisplay);

        // Music player button events
        btnPlayPause.setOnAction(e -> {
            mc.play();
            highlightSong();
        });

        btnStop.setOnAction(e -> {
            mc.stop();
            highlightSong();
        });

        btnNext.setOnAction(e -> {
            mc.next();
            highlightSong();
        });

        btnPrevious.setOnAction(e -> {
            mc.previous();
            highlightSong();
        });

        root.getChildren().add(vBox);
        stage.setScene(new Scene(root, 422, 600));
        stage.show();
    }

    @Override
    public void stop() {
        saveSongs();
    }
    
    private void saveSongs() {
        
        String fileName = "../Docs/SongList.csv";
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName))) {

            for (Song s : songData) {
                csvWriter.writeNext(s.getArray());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("The file was not found.");
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("There was an IO exception.");
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSongs() {
        try {
            // declare instantiate reader.
            // Set a FileReader to read from the file.
            String fileName = "../Docs/SongList.csv";
            try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {

                String[] nextLine;

                while ((nextLine = csvReader.readNext()) != null) {
                    Song song = new Song(nextLine[0], nextLine[1]);
                    mc.add(song);
                }
                updateList();
            }

        } catch (FileNotFoundException e) {
            System.out.println("The File was not found.");
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            System.out.println("There was an error reading the file.");
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, e);
        } catch (CsvValidationException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Program failed at somewhere.");
            System.out.println(e.getMessage());
        }
    }

    private void addSong(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Add Song To Playlist");
        fc.setInitialDirectory(new File("../../Music"));
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("mp3 files only", "*.mp3"));

        List<File> selectedFiles = fc.showOpenMultipleDialog(stage);

        if (selectedFiles != null) {
            for (File f : selectedFiles) {
                mc.add(f.toURI().toString());
            }
            updateList();
        }
    }

    private void updateList() {
        songData.clear();
        for (Song s : mc.songList) {
            songData.add(s);
        }
//        System.out.println(tableView.getItems().size());
    }

    private void highlightSong() {
        int index = songData.indexOf(mc.getCurrentSong());
        tableView.getSelectionModel().select(index);

    }

//    private HBox addToolBar() {
//        
//        HBox toolBar = new HBox();
//        toolBar.setPadding(new Insets(20));
//        toolBar.setAlignment(Pos.CENTER);
//        toolBar.alignmentProperty().isBound();
//        toolBar.setSpacing(5);
//        toolBar.setStyle("-fx-background-color: Black");
//        
//        return toolBar;
//    }
    public static void main(String[] args) {
        launch(args);
    }

    

}
