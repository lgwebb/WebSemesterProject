package edu.cis232.WebsiteBuild;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class TableViewFavorites extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	private TableView<Favorites> table = new TableView<>();
	private ObservableList<Favorites> data = FXCollections.observableArrayList();
	final HBox hb = new HBox();

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(450);
		stage.setHeight(500);

		final Label label = new Label("Favoritess");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn descriptionCol = new TableColumn("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Favorites, String>("description"));
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionCol.setOnEditCommit(new EventHandler<CellEditEvent<Favorites, String>>() {
			@Override
			public void handle(CellEditEvent<Favorites, String> t) {
				Favorites cell = ((Favorites) t.getTableView().getItems().get(t.getTablePosition().getRow()));
				String prodNum = cell.getProdNum();
				cell.setDescription(t.getNewValue());

				updateDescription(t.getNewValue(), prodNum);
			}
		});

		TableColumn prodNumCol = new TableColumn("ProdNum");
		prodNumCol.setCellValueFactory(new PropertyValueFactory<Favorites, String>("prodNum"));
		prodNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
		prodNumCol.setOnEditCommit(new EventHandler<CellEditEvent<Favorites, String>>() {
			@Override
			public void handle(CellEditEvent<Favorites, String> t) {
				String oldProd = t.getOldValue();
				Favorites cell = ((Favorites) t.getTableView().getItems().get(t.getTablePosition().getRow()));
				cell.setProdNum(t.getNewValue());

				updateProdNum(cell.getProdNum(), oldProd);
			}
		});

		TableColumn priceCol = new TableColumn("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Favorites, Double>("Price"));
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		priceCol.setOnEditCommit(new EventHandler<CellEditEvent<Favorites, Double>>() {
			@Override
			public void handle(CellEditEvent<Favorites, Double> t) {
				Favorites cell = ((Favorites) t.getTableView().getItems().get(t.getTablePosition().getRow()));
				cell.setPrice(t.getNewValue());

				updatePrice(cell.getPrice(), cell.getProdNum());
			}
		});

		try {
			buildData();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		table.setItems(data);
		table.getColumns().addAll(descriptionCol, prodNumCol, priceCol);

		final TextField addDescription = new TextField();
		addDescription.setPromptText("Description");
		addDescription.setMaxWidth(descriptionCol.getPrefWidth());
		final TextField addProdNum = new TextField();
		addProdNum.setPromptText("Product Number");
		addProdNum.setMaxWidth(prodNumCol.getPrefWidth());
		final TextField addPrice = new TextField();
		addPrice.setPromptText("Price");
		addPrice.setMaxWidth(priceCol.getPrefWidth());

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Favorites(addDescription.getText(), addProdNum.getText(),
						Double.parseDouble(addPrice.getText())));
				insertFavorites(addDescription.getText(), addProdNum.getText(), Double.parseDouble(addPrice.getText()));
				addDescription.clear();
				addProdNum.clear();
				addPrice.clear();
			}
		});

		hb.getChildren().addAll(addDescription, addProdNum, addPrice, addButton);
		hb.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		vbox.setMinWidth(400);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public void insertFavorites(String desc, String pN, Double price) {
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/Favorites";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			String sql = "INSERT INTO Favorites (Description, ProdNum, Price) VALUES (?, ?, ?)";
			PreparedStatement pState = conn.prepareStatement(sql);
			pState.setString(1, desc);
			pState.setString(2, pN);
			pState.setDouble(3, price);

			pState.executeUpdate();

			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void updatePrice(Double price, String pN) {
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/Favorites";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			String sql = "UPDATE Favorites SET Price = ? WHERE ProdNum = ?";
			PreparedStatement pState = conn.prepareStatement(sql);
			pState.setDouble(1, price);
			pState.setString(2, pN);

			pState.executeUpdate();

			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void updateProdNum(String prodNum, String oldProdNum) {
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/Favorites";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			String sql = "UPDATE Favorites SET ProdNum = ? WHERE ProdNum = ?";
			PreparedStatement pState = conn.prepareStatement(sql);
			pState.setString(1, prodNum);
			pState.setString(2, oldProdNum);

			pState.executeUpdate();

			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void updateDescription(String desc, String prodNum) {
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/Favorites";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			String sql = "UPDATE Favorites SET Description = ? WHERE ProdNum = ?";
			PreparedStatement pState = conn.prepareStatement(sql);
			pState.setString(1, desc);
			pState.setString(2, prodNum);

			pState.executeUpdate();

			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void buildData() throws SQLException {
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/Favorites";

		Connection conn = DriverManager.getConnection(DB_URL);

		Statement stmt = conn.createStatement();

		ResultSet results = stmt.executeQuery("SELECT * FROM Favorites");

		while (results.next()) {
			data.add(new Favorites(results.getString("Description"), results.getString("ProdNum"),
					results.getDouble("Price")));
		}

		conn.close();
	}

	public static class Favorites {
		private final SimpleStringProperty description;
		private final SimpleStringProperty prodNum;
		private final SimpleDoubleProperty price;

		private Favorites(String desc, String prodNum, double price) {
			this.description = new SimpleStringProperty(desc);
			this.prodNum = new SimpleStringProperty(prodNum);
			this.price = new SimpleDoubleProperty(price);
		}

		public String getDescription() {
			return description.get();
		}

		public void setDescription(String desc) {
			this.description.set(desc);
		}

		public String getProdNum() {
			return prodNum.get();
		}

		public void setProdNum(String prodNum) {
			this.prodNum.set(prodNum);
		}

		public Double getPrice() {
			return price.get();
		}

		public void setPrice(Double price) {
			this.price.set(price);
		}
	}

}