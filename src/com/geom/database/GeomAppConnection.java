package com.geom.database;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class sets the connection to the database.
 * Pay attention to the database driver. Here a SQLite database was used
 * @author Dasha
 *
 */
public class GeomAppConnection {
	/**
	 * <ul>
	 * <li>url to the database</li>
	 * <li>user and password should be setted according to provided informations</li>
	 * </ul>
	 */
	//private String url ="jdbc:mysql://localhost:8889/FiguresGeometriques";
	private String url = "jdbc:sqlite:GeomAppDatabase.db";
    private String user="root";
    private String password="root";
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
		// Uncomment this if you want to try a mySQL Database
		/*try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * Test if the Figure table is present in the database
	 * If it not present it will create the whole database.
	 * @param conn A SQL connection to the database
	 * @return true
	 */
	private static boolean testDatabase(java.sql.Connection conn){
		Statement statement = null;

		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Try to find the Figure table
		// If it not
		try {
			statement.executeQuery("SELECT * FROM Figure");
		} catch (SQLException e) {
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
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("Created tables and added one little square in it.");
		}
		return true;
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
