package com.geom.database;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class sets the connection to the SQLite database.
 * @author Dasha
 *
 */
public class GeomAppConnection {
	/**
	 * JDBC url of the SQLite database file, resolved relative to the working directory.
	 */
	private String url = "jdbc:sqlite:GeomAppDatabase.db";
	private static java.sql.Connection conn;
	
	/**
	 * Creates the connection to the database
	 */
	private GeomAppConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			testDatabase(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test if the Figure table is present in the database
	 * If it not present it will create the whole database.
	 * @param conn A SQL connection to the database
	 * @return true
	 */
	static boolean testDatabase(java.sql.Connection conn){
		try (Statement statement = conn.createStatement()) {
			// Try to find the Figure table
			try {
				statement.executeQuery("SELECT * FROM Figure").close();
				return true;
			} catch (SQLException e) {
				// A missing Figure table is expected on first launch: not an error.
			}
			String sql = "BEGIN TRANSACTION;" +
					"CREATE TABLE Point (" +
					"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
					" x REAL NOT NULL, " +
					" y REAL NOT NULL, " +
					" id_figure INTEGER, " +
					" num_point INTEGER " +
					"); " +
					"INSERT INTO Point VALUES (1,0.0,0.0,1,0); " +
					"INSERT INTO Point VALUES (2,10.0,0.0,1,1); " +
					"INSERT INTO Point VALUES (3,10.0,10.0,1,2); " +
					"INSERT INTO Point VALUES (4,0.0,10.0,1,3); " +
					"CREATE TABLE Length ( " +
					" id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					" value REAL, " +
					" id_figure INTEGER, " +
					" num_length INTEGER " +
					"); " +
					"INSERT INTO Length VALUES (1,10.0,1,0); " +
					"INSERT INTO Length VALUES (2,10.0,1,1); " +
					"INSERT INTO Length VALUES (3,10.0,1,2); " +
					"INSERT INTO Length VALUES (4,10.0,1,3); " +
					"CREATE TABLE Figure ( " +
					" id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
					" type TEXT NOT NULL, " +
					" perimeter REAL, " +
					" area REAL " +
					"); " +
					"INSERT INTO Figure VALUES (1,'Square',40.0,100.0); " +
					"COMMIT; ";
			System.out.println("No GeomApp tables found, initializing the database...");
			try {
				statement.executeUpdate(sql);
				System.out.println("Created tables and added one little square in it.");
			} catch (SQLException e1) {
				System.err.println("Failed to initialize the database:");
				e1.printStackTrace();
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Sets the only connection needed
	 * The instantiation is restricted to one object because
	 * only one connection to the database is needed.
	 */
	public static java.sql.Connection getInstance(){//singleton
		if(conn==null)
			new GeomAppConnection();
		return conn;	
	}
}
