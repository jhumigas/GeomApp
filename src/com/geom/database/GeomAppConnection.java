package com.geom.database;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class sets the connection to the database.
 * Pay attention to the database driver. Here a MySQL database was used
 * @author Dasha
 *
 */
public class GeomAppConnection {
	/**
	 * url to the database
	 * user and password should be setted according to provided informations
	 */
	private String url ="jdbc:mysql://localhost:3306/FiguresGeometriques";
    private String user="root";
    private String password="root";
	private static java.sql.Connection conn;
	
	/**
	 * Creates the connection to the database
	 */
	private GeomAppConnection(){
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
