package com.geom.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import com.geom.model.Figure;

/**
 * This class intends to create the layers to interact with database.
 * @author Dasha
 *
 */
public class DAOFactory {
	private static final Logger log = LoggerFactory.getLogger(DAOFactory.class);
	/**
	 * Tables of the GeomApp database. Table names cannot be passed as SQL parameters,
	 * so any table name used to build a query must belong to this list.
	 */
	public static final Set<String> TABLES = Set.of("Figure", "Length", "Point");

	private static Connection conn(){
		return GeomAppConnection.getInstance();
	}

	/**
	 * Instantiate FigureDAO
	 * @return a DAO for the 'Figure' table
	 */
	public static DAO<Figure> getFigureDAO(){
		return new FigureDAO(conn());
	}
	/**
	 * Instantiate LengthDAO
	 * @return a DAO for the 'Length' table
	 */
	public static DAO<Double> getLengthDAO(){
		return new LengthDAO(conn());
	}
	/**
	 * Instantiate Point2DDAO
	 * @return a DAO for the 'Point' table
	 */
	public static DAO<Point2D> getPoint2DDAO(){
		return new Point2DDAO(conn());
	}

	/**
	 * @param tableName one of {@link #TABLES}
	 * @return the primary keys of the table, in ascending order
	 */
	public static ArrayList<Integer> getKeys(String tableName){
		return getKeys(conn(), tableName);
	}

	static ArrayList<Integer> getKeys(Connection conn, String tableName){
		checkTable(tableName);
		ArrayList<Integer> keys = new ArrayList<Integer>();
		try (PreparedStatement statement = conn.prepareStatement("SELECT id FROM " + tableName + " ORDER BY id");
		     ResultSet resultSet = statement.executeQuery()) {
			while(resultSet.next()){
				keys.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			log.error("Could not read the keys of table {}", tableName, e);
		}
		return keys;
	}

	static void checkTable(String tableName){
		if(!TABLES.contains(tableName))
			throw new IllegalArgumentException("Unknown table: " + tableName);
	}
}
