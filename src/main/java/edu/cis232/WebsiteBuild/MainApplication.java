package edu.cis232.WebsiteBuild;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	public static void main(String[] args) {
		new MainApplication().play();
		launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// Load the FXML file.
		Parent parent = FXMLLoader.load(getClass().getResource("WebBrowser.fxml"));
																		
		// Build the scene graph.
		Scene scene = new Scene(parent);

		// Set the stage.
		stage.setTitle("Web Browser - Lucas Webb"); //REQ#1
		stage.setScene(scene);

		// Show the stage.
		stage.show(); //REQ#9
		
	}
	
	public void play(){
		Scanner keyboard = new Scanner(System.in);
		Play p1 = new PlayInput();
		p1.displayOutput(); //REQ#10
		System.out.println("Would you like to open the web browser?");
		String answer = keyboard.nextLine();
		if (answer.equalsIgnoreCase("y")){
			System.out.println("Opening...");
		}
	}

}
