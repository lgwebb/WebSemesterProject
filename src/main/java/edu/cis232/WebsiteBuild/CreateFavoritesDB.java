package edu.cis232.WebsiteBuild;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateFavoritesDB {

	public static void main(String[] args) {
		// Create and load the database
		final String DB_URL = "jdbc:hsqldb:file:FavoritesDB/favorites";

		try {
			// Create a connection to the database.
			Connection conn = DriverManager.getConnection(DB_URL);

			// If the DB already exists, drop the tables.
			dropTables(conn);

			// Build the Coffee table.
			buildFavoritesTable(conn);

			// Close the connection.
			conn.close();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

	}

	public static void dropTables(Connection conn) {
		System.out.println("Checking for existing tables.");

		try {
			Statement stmt = conn.createStatement();

			try {

				stmt.execute("DROP TABLE Favorites");
				System.out.println("Favorites table dropped.");
			} catch (SQLException ex) {

			}

		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/*
	 * Creating a Favorites table and adding some characters the database.
	 */

	public static void buildFavoritesTable(Connection conn) {
		try {

			Statement stmt = conn.createStatement();

			// Create the table.

			// Primary Key = FavoritesURL
			stmt.execute(
					"CREATE TABLE Employee (" + "FavoritesID CHAR(10) NOT NULL PRIMARY KEY, " + "URL CHAR(100) " + ")");

			// Insert row #1.
			stmt.execute("INSERT INTO Employee VALUES ( " + "'URL-001', " + "'oracle.com' )");

			// Insert row #2.
			stmt.execute("INSERT INTO Employee VALUES ( " + "'URL-002', " + "'amazon.com' )");

			// Insert row #3.
			stmt.execute("INSERT INTO Employee VALUES ( " + "'URL-003', " + "'carrollcc.edu' )");

			System.out.println("Favorites table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}

}
