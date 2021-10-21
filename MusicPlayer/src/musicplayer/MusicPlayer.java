package musicplayer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Awsome Music Player");
        
        Group root = new Group();
        
        Label title = new Label("Awesome Music Player");
        
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 20, 0, 10));
        vBox.getChildren().addAll(title, tableView);
        
        root.getChildren().add(vBox);
        stage.setScene(new Scene(root, 400, 500));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
