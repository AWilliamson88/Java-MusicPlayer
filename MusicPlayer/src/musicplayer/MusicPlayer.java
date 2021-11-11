package musicplayer;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        HBox.setMargin(btnAdd, new Insets(0, 0, 0, 756));
        // Add btn Event
        btnAdd.setOnAction(e -> addSong(stage));

        // Top Search and add btn HBox
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(searchLbl, searchField, btnAdd);
        topHBox.setAlignment(Pos.BASELINE_LEFT);
        topHBox.setPadding(new Insets(10, 10, 10, 10));

        // Table column
        TableColumn<Song, String> titleColumn = new TableColumn<>("Song Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(220);

        // Table column
        TableColumn<Song, String> hashColumn = new TableColumn<>("Hash of Title");
        hashColumn.setCellValueFactory(new PropertyValueFactory<>("hash"));
        hashColumn.setPrefWidth(860);

        // Table View
        tableView.setPlaceholder(new Label("No songs to display"));
        tableView.setItems(songData);
        tableView.getColumns().addAll(titleColumn, hashColumn);

        // Music Player Buttons.
        VBox btnsLeft = new VBox(5);
        Button btnPlayPause = new Button("Play / Pause");
        btnPlayPause.setPrefHeight(30);
        btnPlayPause.setPrefWidth(100);
        Button btnPrevious = new Button("Previous");
        btnPrevious.setPrefHeight(30);
        btnPrevious.setPrefWidth(100);
        btnsLeft.getChildren().addAll(btnPlayPause, btnPrevious);

        // Right buttons VBox
        VBox btnsRight = new VBox(5);
        Button btnStop = new Button("Stop");
        btnStop.setPrefHeight(30);
        btnStop.setPrefWidth(100);
        Button btnNext = new Button("Next");
        btnNext.setPrefHeight(30);
        btnNext.setPrefWidth(100);
        btnsRight.getChildren().addAll(btnStop, btnNext);

        // Button display HBox
        HBox buttonDisplay = new HBox(5);
        buttonDisplay.getChildren().addAll(btnsLeft, btnsRight);
        buttonDisplay.setAlignment(Pos.CENTER);

        // Help Button
        HBox helpHBox = new HBox();
        Button btnHelp = new Button("Help");
        btnHelp.setPrefWidth(75);
        helpHBox.setAlignment(Pos.BASELINE_RIGHT);
        helpHBox.getChildren().add(btnHelp);
        btnHelp.setOnAction(e -> {
            File file = new File("../Docs/AwesomeMusicPlayerHelpFile.pdf");
            HostServices hostServices = getHostServices();
            hostServices.showDocument(file.getAbsolutePath());
        });

        // Main UI display Vbox
        VBox vBox = new VBox();
        VBox.setMargin(buttonDisplay, new Insets(10, 0, 2, 0));
        vBox.setPadding(new Insets(10, 20, 0, 10));
        vBox.getChildren().addAll(topHBox, tableView, buttonDisplay, helpHBox);

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
        stage.setScene(new Scene(root, 1100, 570));
        stage.show();
    }

    // Save the song list to a file when the application closes.
    @Override
    public void stop() {
        saveSongs();
    }

    /// This method saves the songs to a csv file.
    /// Called by the overridden stop method.
    private void saveSongs() {

        String fileName = "../Docs/SongList.csv";
        try ( CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName))) {

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

    /// This method loads the song list if there is one into the music player.
    private void loadSongs() {
        try {
            File temp;
            // declare instantiate reader.
            // Set a FileReader to read from the file.
            String fileName = "../Docs/SongList.csv";
            try ( CSVReader csvReader = new CSVReader(new FileReader(fileName))) {

                String[] nextLine;

                while ((nextLine = csvReader.readNext()) != null) {

                    Song song = new Song(nextLine[0], nextLine[1]);
                    temp = new File(song.getPath());
                    if (temp.exists()) {
                        mc.add(song);
                    }
                }
                mc.sortList(mc.getList());
                updateList();
            }

        } catch (FileNotFoundException e) {
//            System.out.println("The File was not found.");
//            System.out.println(
//                    "When the program closes the song list will be saved to a new file.");
        } catch (IOException e) {
//            System.out.println("There was an error reading the file.");
//            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, e);
        } catch (CsvValidationException ex) {
//            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }
    }

    /// This method add's one or multiple songs into the song list.
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
            mc.sortList(mc.getList());
            updateList();
        }
    }

    /// This method updates the observable list with all the songs from the
    /// song list in the Music Controler.
    private void updateList() {
        songData.clear();
        for (Song s : mc.songList) {
            songData.add(s);
        }
//        System.out.println(tableView.getItems().size());
    }

    /// This method highlights the currently playing song.
    private void highlightSong() {
        int index = songData.indexOf(mc.getCurrentSong());
        tableView.getSelectionModel().select(index);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
