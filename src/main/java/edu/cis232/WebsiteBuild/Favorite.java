package edu.cis232.WebsiteBuild;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Favorite {

	public static void add(String URL) throws Exception{
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/favorites";
		
		Connection conn = DriverManager.getConnection(DB_URL);
		
		Statement statement = conn.createStatement();
		
		int count = statement.executeUpdate("insert into Favorites values ('"+URL+"')"); //('DB_URL')
		
		conn.close();

	}

}
