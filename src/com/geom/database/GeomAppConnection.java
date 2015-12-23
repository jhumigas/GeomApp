package com.geom.database;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GeomAppConnection {
	private String url ="jdbc:mysql://localhost:3306/FiguresGeometriques";
    private String user="root";
    private String password="root";
	private static java.sql.Connection conn;
	
	private GeomAppConnection(){
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static java.sql.Connection getInstance(){//singleton
		if(conn==null)
			new GeomAppConnection();
		return conn;	
	}
}
