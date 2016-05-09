package edu.cis232.WebsiteBuild;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainController {

    @FXML
    private WebView webView;
    
    @FXML
    private TextField textfieldHttp;

    @FXML
    private Button buttonHttp;
    
    @FXML
    private MenuItem shuffle;
    
    @FXML
    private MenuItem listFav;
    
    @FXML
    ImageView imageFav;
    
    Image favoritesImage = new Image(getClass().getResource("favorites.jpg").toString());
    
    @FXML
    void addFav(MouseEvent event) {
    	
    }
    
    @FXML
    void listFavTable(ActionEvent event) {
    	
    }
    
    public void initialize(){
    	imageFav.setImage(favoritesImage);
    	//webView.getEngine().load("http://google.com");
    	final WebEngine we = webView.getEngine();
    	we.load("http://google.com");
    	we.setJavaScriptEnabled(true);
    	EventHandler<ActionEvent> enter = new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				we.load(textfieldHttp.getText().startsWith("http://") ? textfieldHttp.getText() :
					"http://" + textfieldHttp.getText());
				
			}
    		
    	};
    	textfieldHttp.setOnAction(enter);
    	buttonHttp.setOnAction(enter);
    	we.locationProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				textfieldHttp.setText(newValue);
				
			}
    		
    	});
    	
    	//EventHandler<ActionEvent> toEnter = new EventHandler<ActionEvent>(){
    		
    		//@Override
    		//public void handle(ActionEvent event){
    			
    		//}
    	//}
    	
    }
    
    
    
    
}
