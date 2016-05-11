package edu.cis232.WebsiteBuild;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainController {

	String currentURL = "";

	@FXML
	private WebView webView;

	@FXML
	private TextField textfieldHttp;

	@FXML
	private Button buttonHttp;

	@FXML
	private Menu favMenu;

	@FXML
	private MenuItem listFav;

	@FXML
	ImageView imageFav;

	Image favoritesImage = new Image(getClass().getResource("favorites.jpg").toString());

	@FXML
	void addFav(MouseEvent event) throws Exception {

		try {
			final String fav = currentURL;
			Favorite.add(currentURL);
			System.out.println("You have added " + fav + " to your favorites bar");
			MenuItem menuItem = new MenuItem(currentURL);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e) {

					final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/favorites";
					try {
						Connection conn = DriverManager.getConnection(DB_URL);
						Statement statement = conn.createStatement();
						statement.executeUpdate("insert into Favorites values ('" + currentURL + "')");
						// REQ#7
						System.out.println(currentURL + "added!");
					} catch (SQLException e1) {
						System.err.println("Action error!");
					}

				}
			});

			favMenu.getItems().add(menuItem);
		} catch (InvalidFavAdded er) { // REQ#11
			er.printStackTrace();
			System.out.print(er.getMessage()); // REQ#12
		}
	}

	@FXML
	void showFav(ActionEvent event) {
		final WebEngine we = webView.getEngine();
		long time1 = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder(); // REQ#2
		for (int i = 0; i < 1000000; i++) {
			builder.append(' ');
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1 + "ms");
		Random rn = new Random();
		int favNum = rn.nextInt(6) + 1;
		if (favNum == 1) {
			we.load("http://google.com");
		} else if (favNum == 2) {
			we.load("http://oracle.com");
		} else if (favNum == 3) {
			we.load("http://www.carrollcc.edu/Home/");
		} else if (favNum == 4) {
			we.load("http://amazon.com");
		} else if (favNum == 5) {
			we.load("http://pcmag.com");
		} else if (favNum == 6) {
			we.load("http://newegg.com");
		} else {
			we.load("http://dell.com");
		}
	}

	public void initialize() {
		imageFav.setImage(favoritesImage);
		final WebEngine we = webView.getEngine();
		we.load("http://w3schools.com");
		we.setJavaScriptEnabled(true);
		EventHandler<ActionEvent> enter = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				we.load(textfieldHttp.getText().startsWith("http://") ? textfieldHttp.getText()
						: "http://" + textfieldHttp.getText());

			}

		};
		textfieldHttp.setOnAction(enter);
		buttonHttp.setOnAction(enter);
		we.locationProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				textfieldHttp.setText(newValue);
				currentURL = newValue;

			}

		});
	}
}
