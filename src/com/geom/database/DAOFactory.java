package com.geom.database;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.geom.model.Figure;

/**
 * This class intends to create the layers to interact with database.
 * @author Dasha
 *
 */
public class DAOFactory {
	protected static final Connection conn = GeomAppConnection.getInstance();
	
	/**
	 * Instantiate FigureDAO
	 * @return
	 */
	public static DAO<Figure> getFigureDAO(){
		return new FigureDAO(conn);
	}
	/**
	 * Instantiate LengthDAO
	 * @return
	 */
	public static DAO<Double> getLengthDAO(){
		return new LengthDAO(conn);
	}
	/**
	 * Instantiate Point2DDAO
	 * @return
	 */
	public static DAO<Point2D> getPoint2DDAO(){
		return new Point2DDAO(conn);
	}
	public static ArrayList<Integer> getKeys(String TableName){
		ArrayList<Integer> keys= new ArrayList<Integer>();
			try {
	           ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM "+TableName+"");
	           //ResultSetMetaData metaData = resultSet.getMetaData();
	           
	           while(resultSet.next()){         
	                 keys.add(Integer.parseInt(resultSet.getObject(1).toString()));
	           }
	             resultSet.close();    
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return keys;
			
		}
}
