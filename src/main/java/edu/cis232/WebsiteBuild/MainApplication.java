package edu.cis232.WebsiteBuild;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainApplication extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// Load the FXML file.
		Parent parent = FXMLLoader.load(getClass().getResource("WebBrowser.fxml"));
																		
		// Build the scene graph.
		Scene scene = new Scene(parent);

		//Menu File
		//Menu menuFile = new Menu("File");
		//MenuItem add = new MenuItem("Shuffle");
		
		//Menu Edit
		//Menu menuEdit = new Menu("Edit");
		
		//Menu View
		//Menu menuView = new Menu("View");
		
		
		
		// Display our window, using the scene graph.
		// Set the stage.
		stage.setTitle("Web Browser");
		stage.setScene(scene);

		// Show the stage.
		stage.show();
	}
	
	
	//menuFile.getItems().addAll(add);

}
