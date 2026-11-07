package com.geom.database;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the specific layer to access the table 'Point'
 * That specific table contains the points that form a geometric shape
 * A 'Point' belongs to a Figure (geometric shapes). It is defined by :
 * <ul>
 * <li>id : the specific key of the length </li>
 * <li>x: the abscissa of a point</li>
 * <li>y: the ordinate of a point </li>
 * <li>id_figure : the ID of the figure to which the Point belongs to</li>
 * <li> num_point: a number to order the points (1st length, 2nd length,...) because a given figure is traveled according to a certain direction
 * </ul> 
 * @author Dasha
 *
 */
public class Point2DDAO extends DAO<Point2D>{

	private static final String INSERT_POINT = "INSERT INTO Point(x,y,id_figure,num_point) VALUES (?,?,?,?)";

	/**
	 * Sets the database connection
	 * @param conn is the database connection
	 * @see com.geom.database.GeomAppConnection
	 */
	public Point2DDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Point2D point) {
		return false;
	}
	/**
	 * 
	 * @param point2d the pair of coordinate to save to the database
	 * @param id_figure the key of the figure to which the point belongs to
	 * @param num the number of the point
	 * @return true if the point was saved
	 */
	@Override
	public boolean save(Point2D point2d, int id_figure, int num){
		try {
			insert(List.of(point2d), id_figure, num);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Saves an array of points 
	 * @param arrayList array of Point2D of a figure
	 * @param id_figure the ID of the figure forms by the ArrayList of points
	 * @return true if all the points were saved
	 */
	public boolean save(ArrayList<Point2D> arrayList,int id_figure){
		try {
			insert(arrayList, id_figure);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** Inserts the points in one batch, numbered from 0. */
	void insert(List<Point2D> points, int id_figure) throws SQLException {
		insert(points, id_figure, 0);
	}

	private void insert(List<Point2D> points, int id_figure, int firstNum) throws SQLException {
		try (PreparedStatement statement = this.connect.prepareStatement(INSERT_POINT)) {
			for(int i = 0; i < points.size(); i++){
				statement.setDouble(1, points.get(i).getX());
				statement.setDouble(2, points.get(i).getY());
				statement.setInt(3, id_figure);
				statement.setInt(4, firstNum + i);
				statement.addBatch();
			}
			statement.executeBatch();
		}
	}

	@Override
	public boolean delete(Point2D point) {
		return false;
	}

	@Override
	public boolean update(Point2D point) {
		return false;
	}

	/**
	 * Gets a specific point given its id
	 * @return the point, or (0,0) if the key is unknown
	 */
	@Override
	public Point2D.Double find(int id) {
		try (PreparedStatement statement = this.connect.prepareStatement("SELECT x, y FROM Point WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				if(result.next())
					return new Point2D.Double(result.getDouble("x"), result.getDouble("y"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Point2D.Double(0, 0);
	}
	/**
	 * Prints the Database 'Point' Table in the console
	 */
	@Override
	public void show() {
		printTable("Point");
	}

	@Override
	public boolean save(Point2D obj) {
		return false;
	}

}
