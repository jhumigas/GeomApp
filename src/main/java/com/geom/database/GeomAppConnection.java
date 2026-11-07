package com.geom.database;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class sets the connection to the SQLite database.
 * <p>The database file is, in order of precedence:</p>
 * <ol>
 * <li>the {@value #DB_PATH_PROPERTY} system property, e.g. {@code java -Dgeomapp.db=/tmp/geom.db -jar ...}</li>
 * <li>the {@value #DB_PATH_ENV} environment variable</li>
 * <li>{@value #DEFAULT_DB_PATH}, relative to the working directory</li>
 * </ol>
 * @author Dasha
 *
 */
public class GeomAppConnection {
	private static final Logger log = LoggerFactory.getLogger(GeomAppConnection.class);

	/** System property holding the path of the database file. */
	public static final String DB_PATH_PROPERTY = "geomapp.db";
	/** Environment variable holding the path of the database file. */
	public static final String DB_PATH_ENV = "GEOMAPP_DB";
	/** Database file used when nothing else is configured. */
	public static final String DEFAULT_DB_PATH = "GeomAppDatabase.db";

	private static java.sql.Connection conn;
	
	/**
	 * Creates the connection to the database
	 * @throws IllegalStateException if the database cannot be opened
	 */
	private GeomAppConnection() {
		Path path = Paths.get(dbPath()).toAbsolutePath();
		log.info("Opening SQLite database {}", path);
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot open the database " + path, e);
		}
		testDatabase(conn);
	}

	/**
	 * @return the configured path of the database file
	 */
	public static String dbPath(){
		return resolveDbPath(System.getProperty(DB_PATH_PROPERTY), System.getenv(DB_PATH_ENV));
	}

	static String resolveDbPath(String property, String env){
		if(property != null && !property.isBlank())
			return property;
		if(env != null && !env.isBlank())
			return env;
		return DEFAULT_DB_PATH;
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
			log.info("No GeomApp tables found, initializing the database");
			try {
				statement.executeUpdate(sql);
				log.info("Created the tables and added one little square");
			} catch (SQLException e1) {
				log.error("Failed to initialize the database", e1);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log.error("Could not check the database schema", e);
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
